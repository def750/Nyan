plugins {
    kotlin("jvm") version "2.0.0-Beta2"
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

tasks.processResources {
    filesMatching("**/plugin.yml") {
        expand(mapOf("version" to project.version))
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

// If target file is used by another process, wait for it to be released
tasks.getByName<Jar>("jar").apply {
    doLast {
        val file = File(build_dir + "\\Nyan.jar")
        while (!file.renameTo(file)) {
            Thread.sleep(1000)
        }
    }
}
