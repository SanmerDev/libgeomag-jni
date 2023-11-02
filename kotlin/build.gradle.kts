plugins {
    kotlin("jvm") version "1.9.10"
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")
    testImplementation(kotlin("test", version = "1.9.10"))
    testImplementation(project(":jni", configuration = "darwin-x86_64-debug"))
}

tasks.test {
    useJUnitPlatform()
}
