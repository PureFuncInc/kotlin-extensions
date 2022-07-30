# kotlin-extensions

> Let's create useful extensions together. 🗿

# Sample Code

* [Aes](docs/aes.md)
* [Arrow-kt](docs/arrow-kt.md)
* [Emoji](docs/emoji.md)
* [File](docs/file.md)
* [Hash](docs/hash.md)
* [Json](docs/json.md)
* [Regex](docs/regex.md)
* [String](docs/string.md)
* [Time](docs/time.md)

# Dependency

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
