import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val baseShade = "com.hoshikurama.github.componentDSL.shaded"


plugins {
    kotlin("jvm") version "1.5.20"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    application
    `java-library`
    `maven-publish`
}

group = "com.hoshikurama.github"
version = "1.0.0"

application {
    mainClass.set("com.hoshikurama.github.componentDSL.ComponentBuilding")
}

repositories {
    mavenCentral()
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
}

dependencies {
    implementation("net.kyori:adventure-api:4.8.1")
    implementation("net.kyori:adventure-extra-kotlin:4.8.1")
    implementation("net.kyori:adventure-text-serializer-legacy:4.8.1")
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        dependencies {
            include(dependency("net.kyori:adventure-extra-kotlin:4.8.1"))
            include(dependency("net.kyori:adventure-text-serializer-legacy:4.8.1"))
        }

        relocate("net.kyori.adventure.extra.kotlin","$baseShade.net.kyori.adventure.extra.kotlin")
        relocate("net.kyori.adventure.text.serializer.legacy","$baseShade.net.kyori.adventure.text.serializer.legacy")
    }
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