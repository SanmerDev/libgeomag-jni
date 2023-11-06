package dev.sanmer.geomag

import dev.sanmer.geomag.Library.Companion.getLibrary

internal actual object ImplLibrary: Library {
    actual override val name = "libgeomag-jni.dll"

    actual override fun load() {
        val library = getLibrary()
        System.load(library)
    }
}