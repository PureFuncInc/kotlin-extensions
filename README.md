# kotlin-extensions 🗿

> Let's create useful extensions together.

## Sample Code 📜

* [Aes](docs/aes.md)
* [Arrow-kt](docs/arrow-kt.md)
* [DDD](docs/ddd.md)
* [File](docs/file.md)
* [Hash](docs/hash.md)
* [Json](docs/json.md)
* [List](docs/list.md)
* [Number](docs/number.md)
* [Regex](docs/regex.md)
* [String](docs/string.md)
* [Time](docs/time.md)

## Dependency [![Release](https://jitpack.io/v/PureFuncInc/kotlin-extensions.svg)](https://jitpack.io/#PureFuncInc/kotlin-extensions)

* Maven

```xml

<project>
    ...

    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>com.github.PureFuncInc</groupId>
            <artifactId>kotlin-extensions</artifactId>
            <version>$version</version>
        </dependency>
    </dependencies>

    ...
</project>
```

* Gradle Groovy

```groovy
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation "com.github.PureFuncInc:kotlin-extensions:$version"
}
```

* Kotlin Gradle DSL

```kotlin
repositories {
    maven {
        url = uri("https://jitpack.io")
    }
}

dependencies {
    implementation("com.github.PureFuncInc:kotlin-extensions:$version")
}
```
