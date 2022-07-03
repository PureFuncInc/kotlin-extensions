import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("maven-publish")
    kotlin("jvm") version "1.6.21"
    kotlin("kapt") version "1.6.21"
}

group = "net.purefunc"
java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

val arrowKtVersion = "1.0.1"
val jacksonVersion = "2.13.3"
val slf4jVersion = "1.7.36"
val junitVersion = "5.8.2"
dependencies {
    implementation(kotlin("stdlib"))

    // Arrow-kt
    implementation("io.arrow-kt:arrow-fx-coroutines:$arrowKtVersion")
    kapt("io.arrow-kt:arrow-meta:1.0.1")

    // Jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

    // Slf4j
    implementation("org.slf4j:slf4j-api:$slf4jVersion")

    // JUnit
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
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
