package br.com.denisecastro.safebus.feature.profile.components.avatar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme
import coil3.compose.SubcomposeAsyncImage

@Composable
fun ProfileAvatar(
    photoUrl: String?,
    modifier: Modifier = Modifier,
    size: Dp = 88.dp
) {
    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImage(
            model = photoUrl?.takeIf { it.isNotBlank() },
            contentDescription = null,
            modifier = Modifier
                .size(size)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            loading = {
                DefaultProfileAvatar(size)
            },
            error = {
                DefaultProfileAvatar(size)
            }
        )
    }
}

@Composable
private fun DefaultProfileAvatar(size: Dp) {
    Icon(
        imageVector = Icons.Outlined.AccountCircle,
        contentDescription = null,
        modifier = Modifier.size(size),
        tint = MaterialTheme.colorScheme.primary
    )
}

@Preview(name = "Avatar - Claro", showBackground = true)
@Composable
private fun ProfileAvatarLightPreview() {
    SafeBusTheme(darkTheme = false) {
        ProfileAvatar(photoUrl = null)
    }
}

@Preview(name = "Avatar - Escuro", showBackground = true)
@Composable
private fun ProfileAvatarDarkPreview() {
    SafeBusTheme(darkTheme = true) {
        ProfileAvatar(photoUrl = null)
    }
}