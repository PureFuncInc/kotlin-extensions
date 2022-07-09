# kotlin-extensions

> Let's create useful extensions together. 🗿

# Sample Code

* [Emoji](docs/emoji.md)
* [Json](docs/json.md)
* [String](docs/string.md)
* [Time](docs/time.md)

# Dependency

* Maven

```xml

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
