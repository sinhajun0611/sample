plugins {
    id("java")
    id("com.gradleup.shadow") version "8.3.0"
}

group = "io.github.sinhajun"
version = "0.0.1"
val main = "${group}.plugin"

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
}

tasks {
    processResources {
        filesMatching("plugin.yml") {
            expand(
                "main" to main,
                "name" to rootProject.name,
                "version" to version
            )
        }
    }

    shadowJar {
        archiveVersion.set("")
        archiveClassifier.set("")

        doLast {
            copy {
                val pluginFile = rootProject.file(".server/plugins/")
                if (pluginFile.exists()) {
                    from(archiveFile)
                    into(pluginFile)
                } else {
                    println("open server, command : ./run")
                }
            }
        }
    }

    test {
        useJUnitPlatform()
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}