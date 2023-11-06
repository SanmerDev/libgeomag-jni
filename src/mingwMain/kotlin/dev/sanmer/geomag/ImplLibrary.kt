package dev.sanmer.geomag

import dev.sanmer.geomag.Library.Companion.getLibrary

internal actual object ImplLibrary: Library {
    actual override val name = "geomag-jni.dll"

    actual override fun load() {
        val library = getLibrary()
        System.load(library)
    }
}