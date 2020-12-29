plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("application")
}

group = "com.github.panpanini.dranks"
version = "1.0-SNAPSHOT"

application {
    mainClassName = "MainKt"
}


dependencies {
    implementation(project(":models"))

    testImplementation(group = "junit", name = "junit", version = "4.12")

    implementation("io.ktor:ktor-server-netty:${Versions.ktor}")
    implementation("io.ktor:ktor-client-core:${Versions.ktor}")
    implementation("io.ktor:ktor-client-okhttp:${Versions.ktor}")
    implementation("io.ktor:ktor-locations:${Versions.ktor}")

    // Koin
    implementation("org.koin:koin-ktor:${Versions.koin}")
    implementation("org.koin:koin-logger-slf4j:${Versions.koin}")
    testImplementation("org.koin:koin-test:${Versions.koin}")

    // Logging
    implementation(Dependencies.logback)

    // Kotlinx serialization
    implementation(KotlinxSerialization.jvm)
}

tasks.withType<Jar> {
    manifest {
        attributes("Main-Class" to "MainKt")
    }
    baseName = "server"

    from(configurations.compile.get().map { if (it.isDirectory) it else zipTree(it) })
}