plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

group = "com.github.panpanini.dranks"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

//kotlin {
//    /* Targets configuration omitted.
//    *  To find out how to configure the targets, please follow the link:
//    *  https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#setting-up-targets */
//
//    sourceSets {
//        val commonMain by getting {
//            dependencies {
//                // Kotlinx serialization
//                implementation(KotlinxSerialization.common)
//            }
//        }
//        val commonTest by getting {
//            dependencies {
//                implementation(kotlin("test-common"))
//                implementation(kotlin("test-annotations-common"))
//            }
//        }
//        jvm()
//        js {
//            browser()
//        }
//
//        val jvmMain by getting {
//            dependencies {
//                implementation(KotlinxSerialization.jvm)
//            }
//        }
//
//        val jsMain by getting {
//            dependencies {
//                implementation(KotlinxSerialization.js)
//            }
//        }
//    }
//}

dependencies {
    implementation(KotlinxSerialization.jvm)
}