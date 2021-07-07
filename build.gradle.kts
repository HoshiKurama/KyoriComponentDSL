import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val baseShade = "com.hoshikurama.github.componentDSL.shaded"


plugins {
    kotlin("jvm") version "1.5.20"
    `java-library`
    `maven-publish`
}

group = "com.hoshikurama.github"
version = "1.1.0"

repositories {
    mavenCentral()
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
}

dependencies {
    implementation("net.kyori:adventure-api:4.8.1")
    implementation("net.kyori:adventure-extra-kotlin:4.8.1")
    implementation("net.kyori:adventure-text-serializer-legacy:4.8.1")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "16"
}

// Publishing Instructions
val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
    from("LICENSE.md") {
        into("META-INF")
    }
}

publishing {
    publications {
        create<MavenPublication>("KyoriComponentDSL") {
            from(components["java"])
            artifact(sourcesJar)
        }
    }
}