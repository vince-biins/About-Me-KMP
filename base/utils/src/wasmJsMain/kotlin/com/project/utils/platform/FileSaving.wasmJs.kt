package com.project.utils.platform

import kotlinx.browser.document

import org.khronos.webgl.Uint8Array
import org.khronos.webgl.set
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.url.URL
import org.w3c.files.Blob
import kotlin.js.JsArray

actual fun saveFile(fileName: String, data: ByteArray) {

    val uint8Array = Uint8Array(data.size)
    for (i in data.indices) {
        uint8Array.set(index = i,value = data[i] )
    }
    val jsArray = JsArray<JsAny?>()
    jsArray[0] = uint8Array as JsAny

    val blob = Blob(jsArray)

    val url = URL.createObjectURL(blob)
    val anchor = document.createElement("a") as HTMLAnchorElement
    anchor.href = url
    anchor.download = fileName
    anchor.click()

    URL.revokeObjectURL(url)
}