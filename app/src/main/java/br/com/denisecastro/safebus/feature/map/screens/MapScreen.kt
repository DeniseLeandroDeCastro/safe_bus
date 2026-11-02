
package br.com.denisecastro.safebus.feature.map.screens

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.location.Geocoder
import android.location.Location
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Overlay
import java.util.Locale

@Composable
fun MapScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current

    if (isPreview) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Map,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
        return
    }

    val locationClient = remember(context) {
        LocationServices.getFusedLocationProviderClient(context)
    }

    var currentLocation by remember {
        mutableStateOf<Location?>(null)
    }

    var locationAddress by remember {
        mutableStateOf<String?>(null)
    }

    var hasLocationPermission by remember {
        mutableStateOf(false)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        hasLocationPermission =
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
                    permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
    }

    LaunchedEffect(Unit) {
        val finePermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val coarsePermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (finePermission || coarsePermission) {
            hasLocationPermission = true
        } else {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    LaunchedEffect(hasLocationPermission) {
        if (hasLocationPermission) {
            val cancellationTokenSource = CancellationTokenSource()

            try {
                val fineGranted = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED

                locationClient.getCurrentLocation(
                    if (fineGranted) {
                        Priority.PRIORITY_HIGH_ACCURACY
                    } else {
                        Priority.PRIORITY_BALANCED_POWER_ACCURACY
                    },
                    cancellationTokenSource.token
                ).addOnSuccessListener { location ->
                    currentLocation = location
                }
            } catch (_: SecurityException) {
                // Permissão não disponível.
            }
        }
    }

    val mapView = remember(context) {
        Configuration.getInstance().userAgentValue = context.packageName

        MapView(context).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            controller.setZoom(15.0)
        }
    }

    // Obtém o endereço aproximado a partir das coordenadas.
    LaunchedEffect(currentLocation) {
        val location = currentLocation ?: return@LaunchedEffect

        locationAddress = null

        locationAddress = withContext(Dispatchers.IO) {
            try {
                @Suppress("DEPRECATION")
                val addresses = Geocoder(
                    context,
                    Locale("pt", "BR")
                ).getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                )

                addresses?.firstOrNull()?.getAddressLine(0)
            } catch (_: Exception) {
                null
            }
        }
    }

    // Centraliza o mapa e atualiza o círculo azul.
    LaunchedEffect(currentLocation, mapView) {
        currentLocation?.let { location ->
            val point = GeoPoint(
                location.latitude,
                location.longitude
            )

            mapView.controller.setZoom(16.5)
            mapView.controller.animateTo(point)

            mapView.overlays.removeAll {
                it is UserLocationOverlay
            }

            mapView.overlays.add(
                UserLocationOverlay(point)
            )

            mapView.invalidate()
        }
    }

    DisposableEffect(mapView) {
        mapView.onResume()

        onDispose {
            mapView.onPause()
            mapView.onDetach()
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize()
        )

        // Card com endereço e precisão.
        if (currentLocation != null) {
            Card(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.map_location_title),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = locationAddress
                            ?: stringResource(R.string.map_location_unavailable),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    currentLocation
                        ?.takeIf { it.hasAccuracy() }
                        ?.let { location ->
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = stringResource(
                                    R.string.map_location_accuracy,
                                    location.accuracy.toInt()
                                ),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                }
            }

            // Botão para voltar à localização do usuário.
            FloatingActionButton(
                onClick = {
                    currentLocation?.let { location ->
                        val point = GeoPoint(
                            location.latitude,
                            location.longitude
                        )

                        mapView.controller.setZoom(16.5)
                        mapView.controller.animateTo(point)
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(
                    imageVector = Icons.Outlined.MyLocation,
                    contentDescription = stringResource(
                        R.string.map_my_location
                    )
                )
            }
        }
    }
}

private class UserLocationOverlay(
    private val location: GeoPoint
) : Overlay() {

    private val accuracyPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.argb(45, 33, 150, 243)
        style = Paint.Style.FILL
    }

    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    private val locationPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.rgb(33, 150, 243)
        style = Paint.Style.FILL
    }

    override fun draw(
        canvas: Canvas,
        mapView: MapView,
        shadow: Boolean
    ) {
        if (shadow) return

        val point = Point()
        mapView.projection.toPixels(location, point)

        val density = mapView.resources.displayMetrics.density

        canvas.drawCircle(
            point.x.toFloat(),
            point.y.toFloat(),
            28f * density,
            accuracyPaint
        )

        canvas.drawCircle(
            point.x.toFloat(),
            point.y.toFloat(),
            11f * density,
            borderPaint
        )

        canvas.drawCircle(
            point.x.toFloat(),
            point.y.toFloat(),
            8f * density,
            locationPaint
        )
    }
}

@Preview(
    name = "Mapa - Claro",
    showSystemUi = true
)
@Composable
private fun MapScreenLightPreview() {
    SafeBusTheme(darkTheme = false) {
        MapScreen()
    }
}

@Preview(
    name = "Mapa - Escuro",
    showSystemUi = true
)
@Composable
private fun MapScreenDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        MapScreen()
    }
}
