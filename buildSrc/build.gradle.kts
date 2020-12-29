repositories {
    jcenter()
    google()
}

plugins {
    `kotlin-dsl`
}

dependencies {
    implementation("com.android.tools.build:gradle:4.2.0-alpha15")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.21")

    implementation(gradleApi())
    implementation(localGroovy())
}