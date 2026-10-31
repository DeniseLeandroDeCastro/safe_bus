package br.com.denisecastro.safebus.feature.dangeralert.history.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.R
import br.com.denisecastro.safebus.feature.dangeralert.data.model.AlertHistoryItem
import br.com.denisecastro.safebus.feature.dangeralert.history.components.AlertHistoryCard
import br.com.denisecastro.safebus.feature.dangeralert.history.state.AlertHistoryUiState
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme
import com.google.firebase.Timestamp
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertHistoryScreen(
    uiState: AlertHistoryUiState,
    onBackClick: () -> Unit,
    onRetryClick: () -> Unit,
    onAlertClick: (AlertHistoryItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.alert_history_title)
                    )
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator()
                }

                uiState.hasError -> {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = stringResource(
                                R.string.alert_history_error
                            ),
                            style = MaterialTheme.typography.bodyLarge
                        )

                        TextButton(onClick = onRetryClick) {
                            Text(
                                text = stringResource(
                                    R.string.alert_history_retry
                                )
                            )
                        }
                    }
                }

                uiState.alerts.isEmpty() -> {
                    Text(
                        text = stringResource(
                            R.string.alert_history_empty
                        ),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(24.dp)
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(
                            items = uiState.alerts,
                            key = { it.id }
                        ) { alert ->
                            AlertHistoryCard(
                                alert = alert,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onAlertClick(alert)
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AlertHistoryPreviewContent(
    darkTheme: Boolean
) {
    SafeBusTheme(darkTheme = darkTheme) {
        AlertHistoryScreen(
            uiState = AlertHistoryUiState(
                alerts = listOf(
                    AlertHistoryItem(
                        id = "preview-danger",
                        type = "DANGER",
                        latitude = -5.91,
                        longitude = -35.26,
                        accuracyMeters = 15.0,
                        status = "REGISTERED",
                        createdAt = Timestamp(Date(0))
                    ),
                    AlertHistoryItem(
                        id = "preview-harassment",
                        type = "HARASSMENT",
                        latitude = -5.92,
                        longitude = -35.25,
                        accuracyMeters = 12.0,
                        status = "REGISTERED",
                        createdAt = Timestamp(Date(0))
                    )
                )
            ),
            onBackClick = {},
            onRetryClick = {},
            onAlertClick = {}
        )
    }
}

@Preview(
    name = "Histórico - Tema claro",
    showSystemUi = true
)
@Composable
private fun AlertHistoryLightPreview() {
    AlertHistoryPreviewContent(darkTheme = false)
}

@Preview(
    name = "Histórico - Tema escuro",
    showSystemUi = true
)
@Composable
private fun AlertHistoryDarkPreview() {
    AlertHistoryPreviewContent(darkTheme = true)
}
