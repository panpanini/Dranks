
object Versions {
    const val kotlin = "1.4.0"

    const val coreKtx = "1.3.1"

    const val appcompat = "1.2.0"

    const val material = "1.2.1"

    const val compose = "1.0.0-alpha04"

    const val lifecycle = "2.3.0-alpha07"

    const val junit = "4.13"

    const val mockito = "3.5.10"
    const val mockitoKotlin = "2.2.0"

    const val coreTesting = "1.1.1"

    const val assertJ = "3.16.1"

    const val androidxJunit = "1.1.2"

    const val espresso = "3.3.0"

    const val coroutines = "1.3.9"

    const val retrofit = "2.9.0"

    const val moshi = "1.9.3"

    const val accompanist = "0.2.1"

    const val koin = "2.1.6"

    const val datastorePreferences = "1.0.0-alpha01"
}

object Dependencies {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"

    const val material = "com.google.android.material:material:${Versions.material}"

    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeRuntimeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeTooling = "androidx.ui:ui-tooling:${Versions.compose}"

    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    const val accompanist = "dev.chrisbanes.accompanist:accompanist-coil:${Versions.accompanist}"

    const val koin = "org.koin:koin-core:${Versions.koin}"
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinAndroidxScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinAndroidxViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koinAndroidxFragment = "org.koin:koin-androidx-fragment:${Versions.koin}"

    const val datastorePreferences = "androidx.datastore:datastore-preferences:${Versions.datastorePreferences}"
}

object TestDependencies {
    const val junit = "junit:junit:${Versions.junit}"

    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"

    const val coreTesting = "android.arch.core:core-testing:${Versions.coreTesting}"

    const val assertJ = "org.assertj:assertj-core:${Versions.assertJ}"

    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}

object AndroidTestDependencies {
    const val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"

    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    const val composeTest = "androidx.ui:ui-test:${Versions.compose}"
}