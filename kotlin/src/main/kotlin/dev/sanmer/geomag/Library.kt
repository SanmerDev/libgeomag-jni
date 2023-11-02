package dev.sanmer.geomag

import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files


object Library {
    private const val LIBRARY_NAME = "libgeomag-jni.dylib"

    fun load() {
        val libraryFile = getLibraryFile()
        System.load(libraryFile)
    }

    private fun getLibraryFile(): String {
        if (System.getenv("GEOMAG_JNI_LIBRARY_PATH") != null) {
            return System.getenv("GEOMAG_JNI_LIBRARY_PATH")
        }

        val libraryURL = checkNotNull(javaClass.getResource("/$LIBRARY_NAME"))
        if (libraryURL.protocol != "jar") {
            throw LinkageError("Load $LIBRARY_NAME")
        }

        val libsDir = Files.createTempDirectory("geomag-jni").toFile()
        libsDir.deleteOnExit()

        val libraryFile = File(libsDir, LIBRARY_NAME)
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