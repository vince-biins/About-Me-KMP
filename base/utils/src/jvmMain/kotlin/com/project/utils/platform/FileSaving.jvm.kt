package com.project.utils.platform

import okio.FileSystem
import okio.Path.Companion.toPath
import okio.buffer

actual fun saveFile(fileName: String, data: ByteArray) {
    val downloadsPath = when {
        System.getProperty("os.name")
            .contains("Windows") -> "${System.getProperty("user.home")}\\Downloads"

        else -> "${System.getProperty("user.home")}/Downloads"
    }

    val filePath = "$downloadsPath/$fileName".toPath()
    try {
        FileSystem.SYSTEM.sink(filePath).buffer().use { sink ->
            sink.write(data)
        }
        println("File saved successfully in Downloads: $filePath")
    } catch (e: Exception) {
        println("Error saving file: ${e.message}")
    }
}