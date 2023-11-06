# libgeomag-jni
Kotlin bindings for Rust library [libgeomag](https://github.com/SanmerDev/libgeomag)

## demo
```kotlin
import dev.sanmer.geomag.Geomag

fun main() {
    val t = Geomag.toDecimalYears(2023, 11, 1, 0, 0, 0)
    val m = Geomag.wmm(102.0, 24.0, 1.9, t)
    
    println("X = ${m.x}")
    println("Y = ${m.y}")
    println("Z = ${m.z}")
}
```