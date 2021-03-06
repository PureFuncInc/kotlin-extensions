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

val arrowKtVersion = "1.0.1"
val jacksonVersion = "2.13.3"
val slf4jVersion = "1.7.36"
val bcprovJdkVersion = "1.70"
val junitVersion = "5.8.2"
dependencies {
    implementation(kotlin("stdlib"))

    // Arrow-kt
    api("io.arrow-kt:arrow-fx-coroutines:$arrowKtVersion")
    kapt("io.arrow-kt:arrow-meta:$arrowKtVersion")

    // Jackson
    api("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

    // Slf4j
    api("org.slf4j:slf4j-api:$slf4jVersion")

    // AES PKCS7PADDING
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
