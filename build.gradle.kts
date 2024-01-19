import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    kotlin("jvm") version "2.0.0-Beta2"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "xyz.seventwentyseven.nyan"
version = "1.0.0"
var buildDir = "C:\\Users\\ja\\Desktop\\dokumenty\\Sewer\\plugins"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
    compileOnly("org.jetbrains:annotations:24.1.0")
    implementation("com.github.hazae41:mc-kutils:master-SNAPSHOT")
    implementation(kotlin("stdlib", version = "2.0.0-Beta2"))
    // include stdlib
}


tasks {
// Put output jar in C:\Users\def750\Desktop\server\plugins
    getByName<Jar>("jar").apply {
        archiveFileName.set("Nyan.jar")
        // set destinationDir to the build_dir variable, if it's not empty
        if (buildDir.isNotEmpty()) {
            destinationDirectory.set(file(buildDir))
        } else {
            destinationDirectory.set(destinationDirectory.get())
        }


    }

    build {
        dependsOn(shadowJar)
        // Include stdlib in final jar
    }

    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    shadowJar {
        archiveFileName.set("Nyan.jar")
        // set destinationDir to the build_dir variable, if it's not empty
        if (buildDir.isNotEmpty()) {
            destinationDirectory.set(file(buildDir))
        } else {
            destinationDirectory.set(destinationDirectory.get())
        }
        // Include stdlib in final jar
        dependencies {
            include(dependency("org.jetbrains.kotlin:kotlin-stdlib"))
        }

    }

    compileJava {
        dependsOn(clean)
        options.encoding = "UTF-8"
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
        withSourcesJar()
    }


    build {
        dependsOn(shadowJar)
    }
}



//java {
//    sourceCompatibility = JavaVersion.VERSION_17
//    targetCompatibility = JavaVersion.VERSION_17
//    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
//}