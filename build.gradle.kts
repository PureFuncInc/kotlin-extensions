import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("maven-publish")
    kotlin("jvm") version "1.6.21"
    kotlin("kapt") version "1.6.21"
}

group = "net.purefunc"
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

val arrowKtVersion = "1.1.2"
val arrowKtMetaVersion = "1.6.0"
val jacksonVersion = "2.14.0-rc1"
val slf4jVersion = "1.7.36"
val bcryptVersion = "0.9.0"
val bcprovJdkVersion = "1.70"
val junitVersion = "5.9.0"
dependencies {
    implementation(kotlin("stdlib"))

    // Arrow-Kt
    api("io.arrow-kt:arrow-fx-coroutines:$arrowKtVersion")
    kapt("io.arrow-kt:arrow-meta:$arrowKtMetaVersion")

    // Jackson
    api("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")

    // Slf4j
    api("org.slf4j:slf4j-api:$slf4jVersion")
    testImplementation("org.slf4j:slf4j-reload4j:$slf4jVersion")

    // Bcrypt
    api("at.favre.lib:bcrypt:$bcryptVersion")

    // AES/CBC/PKCS7PADDING
    api("org.bouncycastle:bcprov-jdk15on:$bcprovJdkVersion")

    // JUnit
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
