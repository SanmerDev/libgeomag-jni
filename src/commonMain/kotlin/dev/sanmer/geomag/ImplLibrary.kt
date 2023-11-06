package dev.sanmer.geomag


internal expect object ImplLibrary: Library {
    override val name: String

    override fun load()
}
