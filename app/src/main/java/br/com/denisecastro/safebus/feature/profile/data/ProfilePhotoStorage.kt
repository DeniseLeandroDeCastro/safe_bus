package br.com.denisecastro.safebus.feature.profile.data

import android.content.Context
import android.net.Uri
import java.io.File

class ProfilePhotoStorage(
    private val context: Context
) {

    fun savePhoto(uri: Uri, userId: String): String {
        val directory = File(context.filesDir, "profile_photos")

        if (!directory.exists()) {
            check(directory.mkdirs()) {
                "Não foi possível criar a pasta de fotos."
            }
        }

        val file = File.createTempFile(
            "profile_${userId}_",
            ".img",
            directory
        )

        try {
            context.contentResolver.openInputStream(uri).use { input ->
                requireNotNull(input) {
                    "Não foi possível abrir a imagem."
                }

                file.outputStream().use { output ->
                    input.copyTo(output)
                }
            }

            directory.listFiles()
                ?.filter {
                    it.name.startsWith("profile_${userId}_") &&
                            it != file
                }
                ?.forEach { it.delete() }

            return Uri.fromFile(file).toString()
        } catch (exception: Exception) {
            file.delete()
            throw exception
        }
    }

    fun getPhoto(userId: String): String? {
        val directory = File(context.filesDir, "profile_photos")

        val file = directory.listFiles()
            ?.filter {
                it.isFile &&
                        it.name.startsWith("profile_${userId}_")
            }
            ?.maxByOrNull { it.lastModified() }

        return file?.let {
            Uri.fromFile(it).toString()
        }
    }
}
