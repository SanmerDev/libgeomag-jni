# libgeomag-jni
Java bindings for Rust library [libgeomag](https://github.com/SanmerDev/libgeomag)

## java
```java
import dev.sanmer.geomag.Geomag;
import dev.sanmer.geomag.MagneticField;

public class Main {
    public static void main(String[] args) {
        double t = Geomag.toDecimalYears(2023, 11, 1, 0, 0, 0);
        MagneticField m = Geomag.wmm(102.0, 24.0, 1.9, t);

        System.out.println("X = " + m.getX());
        System.out.println("Y = " + m.getY());
        System.out.println("Z = " + m.getZ());
    }
}
```

## kotlin
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