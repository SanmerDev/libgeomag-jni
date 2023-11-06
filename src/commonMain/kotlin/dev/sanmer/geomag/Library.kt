package dev.sanmer.geomag

import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files

internal interface Library {
    val name: String
    fun load()

    companion object {
        internal fun <T: Library> T.getLibrary(): String {
            if (System.getenv("LIBGEOMAG_JNI_PATH") != null) {
                return System.getenv("LIBGEOMAG_JNI_PATH")
            }

            val libraryURL = checkNotNull(javaClass.getResource("/${name}"))
            if (libraryURL.protocol != "jar") {
                throw LinkageError("Load $name")
            }

            val libsDir = Files.createTempDirectory("geomag-jni").toFile()
            libsDir.deleteOnExit()

            val libraryFile = File(libsDir, name)
            libraryFile.deleteOnExit()

            val inputStream = libraryURL.openStream()
            val outputStream = FileOutputStream(libraryFile)

            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }

            return libraryFile.absolutePath
        }
    }
}