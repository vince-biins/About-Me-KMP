package com.project.utils.platform

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask
import platform.Foundation.dataWithBytes
import platform.Foundation.writeToURL

@OptIn(ExperimentalForeignApi::class)
actual fun saveFile(fileName: String, data: ByteArray) {

    val fileManager = NSFileManager.defaultManager()
    val documentsURL =
        fileManager.URLsForDirectory(NSDocumentDirectory, NSUserDomainMask).firstOrNull()

    if (documentsURL != null) {
        if(documentsURL is NSURL) {
            val fileURL = documentsURL.URLByAppendingPathComponent(fileName)

            val dataToSave = data.usePinned { pinned ->
                NSData.dataWithBytes(pinned.addressOf(0), data.size.toULong())
            }

            if (fileURL != null) {
                if (dataToSave.writeToURL(url = fileURL, atomically = true)) {
                    println("File saved to: ${fileURL.path}")
                } else {
                    println("Error saving file.")
                }
            } else {
                println("Failed to prepare data or file URL.")
            }
        }
    } else {
        println("Failed to retrieve Documents directory URL.")
    }
}