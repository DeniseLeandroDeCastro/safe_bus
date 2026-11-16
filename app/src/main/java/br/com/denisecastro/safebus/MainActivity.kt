package br.com.denisecastro.safebus

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import br.com.denisecastro.safebus.navigation.SafeBusNavHost
import br.com.denisecastro.safebus.ui.theme.SafeBusTheme
import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {

    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        // A permissão foi concedida ou negada pelo usuário.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            SafeBusTheme {
                SafeBusNavHost()
            }
        }

        requestNotificationPermission()

        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("SafeBusFCM", "FCM token: ${task.result}")
                } else {
                    Log.e("SafeBusFCM", "Failed to get FCM token", task.exception)
                }
            }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permission = Manifest.permission.POST_NOTIFICATIONS

            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                notificationPermissionLauncher.launch(permission)
            }
        }
    }
}
