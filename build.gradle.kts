import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    kotlin("jvm") version "2.0.0-Beta2"
    id("net.minecrell.plugin-yml.paper") version "0.6.0"

}

project.version = "0.0.1"
var build_dir = "C:\\Users\\def750\\Desktop\\server\\plugins"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")
}

paper {
    main = "xyz.seventwentyseven.nyan.Main"
    version = project.version.toString()

    apiVersion = "1.20"
    generateLibrariesJson = true
    foliaSupported = false

}

// Put output jar in C:\Users\def750\Desktop\server\plugins
tasks.getByName<Jar>("jar").apply {
    archiveFileName.set("Nyan.jar")
    // set destinationDir to the build_dir variable, if it's not empty
    if (build_dir.isNotEmpty()) {
        destinationDirectory.set(file(build_dir))
    } else {
        destinationDirectory.set(destinationDirectory.get())
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

// If target file is used by another process, ignore it and force replace it
tasks.getByName<Jar>("jar").apply {
    outputs.upToDateWhen { false }
}