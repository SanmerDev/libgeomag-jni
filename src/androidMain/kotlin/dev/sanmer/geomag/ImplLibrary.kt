package dev.sanmer.geomag

internal actual object ImplLibrary: Library {
    actual override val name = "geomag-jni"

    actual override fun load() {
        System.loadLibrary(name)
    }
}