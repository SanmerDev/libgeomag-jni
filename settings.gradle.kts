@file:Suppress("UnstableApiUsage")

rootProject.name = "libgeomag-jni"

include("kotlin")
include("java")
include("jni")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
