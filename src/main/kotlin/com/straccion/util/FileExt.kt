package com.straccion.util

import io.ktor.http.content.*
import io.ktor.utils.io.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.io.readByteArray
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.imageio.ImageIO


fun PartData.FileItem.saveFile(folderPath: String): String{
    val fileName = "${UUID.randomUUID()}.${File(originalFileName as String).extension}"
    val fileBytes = streamProvider().readBytes()

    val folder = File(folderPath)
    folder.mkdirs()
    File("$folder/$fileName").writeBytes(fileBytes)
    return fileName
}

//suspend fun PartData.FileItem.saveFile(folderPath: String): String {
//    val fileBytes = provider().readRemaining().readByteArray()
//    val folder = File(folderPath).apply { mkdirs() }
//
//    return try {
//
//        //Genera error y se va por el cath
//        val image = withContext(Dispatchers.IO) {
//            ImageIO.read(fileBytes.inputStream())
//        } ?: throw IllegalArgumentException("Invalid image")
//
//        val uniqueFileName = "${UUID.randomUUID()}.webp"
//        val outputFile = File("$folder/$uniqueFileName")
//
//        withContext(Dispatchers.IO) {
//            ImageIO.write(image, "webp", outputFile)
//        }.takeIf { it }
//            ?: throw IllegalStateException("WebP conversion failed")
//
//        uniqueFileName
//    } catch (e: Exception) {
//        // Fallback: save original file
//        val originalExtension = withContext(Dispatchers.IO) {
//            originalFileName?.let { Paths.get(it) }?.let { Files.probeContentType(it) }
//        }
//            ?.substringAfter("/")
//            ?: "jpg"
//
//        val fallbackFileName = "${UUID.randomUUID()}.$originalExtension"
//        File("$folder/$fallbackFileName").writeBytes(fileBytes)
//
//        fallbackFileName
//    }
//}