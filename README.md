# KyoriComponentDSL
This small library extends the functionality of the Kyori Adventure API by giving component creation in a DSL-style manner.

## Dependency Information:
NOTE: Make sure to shade dependency into project!

Gradle (Kotlin DSL)
```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.HoshiKurama:KyoriComponentDSL:1.0.0")
}
```

Gradle (Groovy DSL)
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.HoshiKurama:KyoriComponentDSL:1.0.0'
}
```

Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.HoshiKurama</groupId>
        <artifactId>KyoriComponentDSL</artifactId>
        <version>Tag</version>
    </dependency>
</dependencies>
```
