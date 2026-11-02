package br.com.denisecastro.safebus.feature.dangeralert.history.details.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.GpsFixed
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.dangeralert.data.model.AlertHistoryItem
import br.com.denisecastro.safebus.ui.components.cards.StandardCard
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.location.Geocoder
import android.os.Build
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDetailsScreen(
    alert: AlertHistoryItem,
    onBackClick: () -> Unit,
    onMapClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val alertTitle = when (alert.type) {
        "DANGER" -> R.string.danger_alert_title
        "HARASSMENT" -> R.string.alert_review_harassment_title
        else -> R.string.alert_history_unknown_type
    }

    val alertImage = when (alert.type) {
        "DANGER" -> R.drawable.alert_danger
        "HARASSMENT" -> R.drawable.alert_harassment
        else -> null
    }

    val statusText = when (alert.status) {
        "REGISTERED" -> R.string.alert_history_registered
        else -> R.string.alert_history_unknown_status
    }

    val date = alert.createdAt?.toDate()

    val dateText = date?.let {
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(it)
    } ?: stringResource(R.string.alert_history_unknown_date)

    val timeText = date?.let {
        SimpleDateFormat("HH:mm", Locale.getDefault()).format(it)
    } ?: stringResource(R.string.alert_history_unknown_date)

    val context = LocalContext.current

    var addressText by remember(alert.id) {
        mutableStateOf<String?>(null)
    }

    LaunchedEffect(alert.id, alert.latitude, alert.longitude) {
        addressText = null

        addressText = withContext(Dispatchers.IO) {
            try {
                val geocoder = Geocoder(
                    context,
                    Locale("pt", "BR")
                )

                @Suppress("DEPRECATION")
                val address = geocoder.getFromLocation(
                    alert.latitude,
                    alert.longitude,
                    1
                )?.firstOrNull()

                address?.let {
                    listOfNotNull(
                        it.thoroughfare,
                        it.subThoroughfare,
                        it.subLocality,
                        it.locality,
                        it.adminArea
                    )
                        .filter { part -> part.isNotBlank() }
                        .distinct()
                        .joinToString(", ")
                        .ifBlank { null }
                }
            } catch (e: Exception) {
                null
            }
        }
    }

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.alert_details_title))
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = stringResource(
                                R.string.alert_history_back
                            )
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            if (alertImage != null) {
                Image(
                    painter = painterResource(alertImage),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.large),
                    contentScale = ContentScale.FillWidth
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = stringResource(alertTitle),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    minLines = 2,
                    maxLines = 2
                )

                Text(
                    text = stringResource(statusText),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.CalendarToday,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = dateText,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AccessTime,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = timeText,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            StandardCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                AlertDetailInfoRow(
                    icon = Icons.Outlined.LocationOn,
                    title = stringResource(R.string.alert_details_location),
                    value = listOfNotNull(
                        addressText,
                        stringResource(
                            R.string.alert_history_coordinates,
                            alert.latitude,
                            alert.longitude
                        )
                    ).joinToString("\n")
                )

                HorizontalDivider()

                AlertDetailInfoRow(
                    icon = Icons.Outlined.GpsFixed,
                    title = stringResource(R.string.alert_details_accuracy),
                    value = alert.accuracyMeters?.let {
                        stringResource(R.string.alert_details_accuracy_value, it)
                    } ?: stringResource(R.string.alert_details_unavailable)
                )

                HorizontalDivider()

                AlertDetailInfoRow(
                    icon = null,
                    title = stringResource(R.string.alert_details_identifier),
                    value = alert.id
                )
            }

            Button(
                onClick = onMapClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Outlined.Map,
                    contentDescription = null
                )

                Text(
                    text = stringResource(R.string.alert_details_view_map),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun AlertDetailInfoRow(
    icon: ImageVector?,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private val previewAlert = AlertHistoryItem(
    id = "preview-alert",
    type = "DANGER",
    latitude = -5.91,
    longitude = -35.26,
    accuracyMeters = 15.0,
    status = "REGISTERED",
    createdAt = Timestamp(Date())
)

@Preview(
    name = "Detalhes - Claro",
    showSystemUi = true
)
@Composable
private fun AlertDetailsLightPreview() {
    SafeBusTheme(darkTheme = false) {
        AlertDetailsScreen(
            alert = previewAlert,
            onBackClick = {},
            onMapClick = {}
        )
    }
}

@Preview(
    name = "Detalhes - Escuro",
    showSystemUi = true
)
@Composable
private fun AlertDetailsDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        AlertDetailsScreen(
            alert = previewAlert.copy(type = "HARASSMENT"),
            onBackClick = {},
            onMapClick = {}
        )
    }
}
