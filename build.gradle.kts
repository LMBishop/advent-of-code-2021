plugins {
    base
    kotlin("jvm") version "1.5.10" apply false
}

buildscript {
    plugins {
        application
    }
}

allprojects {
    group = "com.leonardobishop.adventofcode2021"
    version = "1.0"
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.gradle.application")

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib"))
    }

    tasks.jar {
        manifest {
            attributes["Main-Class"] = "com.leonardobishop.adventofcode.Day${ if (ext.has("dayCode")) ext["dayCode"] else "00" }Kt"
        }
        configurations["compileClasspath"].forEach { file: File ->
            from(zipTree(file.absoluteFile))
        }
    }
}
