import org.spongepowered.gradle.vanilla.repository.MinecraftPlatform

plugins {
    java
    id("org.spongepowered.gradle.vanilla") version "0.2.1-SNAPSHOT"
}

minecraft {
    version(property("mcVersion").toString())
    platform(MinecraftPlatform.SERVER)

    runs {
        server("generate") {
            mainClass("io.papermc.generator.Main")
            accessWideners(file("wideners.at"))
            args(file("generated").toString(),
                project(":paper-api").sourceSets["main"].java.srcDirs.first().toString(),
                file("generatedServerTest").toString())
        }
    }
}

dependencies {
    implementation("com.squareup:javapoet:1.13.0")
    implementation(project(":paper-api"))
    implementation("io.github.classgraph:classgraph:4.8.47")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

tasks.test {
    useJUnitPlatform()
    systemProperty("paper.generator.rewriter.container", file("generated").toString()) // todo move to the sourceset
}

group = "io.papermc.paper"
version = "1.0-SNAPSHOT"
