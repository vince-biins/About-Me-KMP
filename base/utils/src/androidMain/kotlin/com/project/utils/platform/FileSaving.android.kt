package com.project.utils.platform


import android.content.ContentValues
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import com.project.theme.AppContext
import okio.FileSystem
import okio.Path.Companion.toOkioPath
import okio.buffer


actual fun saveFile(fileName: String, data: ByteArray) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Download/")
        }

        val resolver = AppContext.get().contentResolver
        val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)

        uri?.let { ur ->
                resolver.openOutputStream(ur)?.use { a ->
                a.write(data)
                Log.d("FileSaver", "File saved to Downloads: ${uri.path}")
            }
        }
    } else {

        val file = AppContext.get().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.resolve(fileName)
        if (file != null) {
            val path = file.toOkioPath()
            FileSystem.SYSTEM.sink(path).buffer().use { it.write(data) }
            Log.d("FileSaver", "File saved to: ${file.absolutePath}")
        } else {
            Log.e("FileSaver", "Failed to resolve downloads directory")
        }
    }
}