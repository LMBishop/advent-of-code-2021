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
        val filePrefix = if (project.hasProperty("visualisation")) "visualisation." else ""
        val fileSuffix = if (project.hasProperty("visualisation")) "VisualisationKt" else "Kt"
        manifest {
            attributes["Main-Class"] =
                "com.leonardobishop.adventofcode.${filePrefix}Day${ if (ext.has("dayCode")) ext["dayCode"] else "00" }$fileSuffix"
        }
        configurations["compileClasspath"].forEach { file: File ->
            from(zipTree(file.absoluteFile))
        }
    }
}
