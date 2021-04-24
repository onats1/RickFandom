
object DefaultConfig {
    const val APPLICATION_ID = "com.onats.rickfandom"
    const val COMPILE_SDK_VERSION = 30
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 30
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"

    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}

object Dependencies {


    const val KTX_CORE = "androidx.core:core-ktx:${Versions.KTX_VERSION}"
    const val KOTLIN_STANDARD_LIBRARY = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT_VERSION}"
    const val MATERIAL_COMPONENTS = "com.google.android.material:material:${Versions.MATERIAL_VERSION}"

    //Jetpack Compose
    const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE_VERSION}"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE_VERSION}"
    const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_VERSION}"
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE_VERSION}"

    //Jetpack Compose Navigation
    const val COMPOSE_NAV = "androidx.navigation:navigation-compose:1.0.0-alpha10"


    //Lifecycle components
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_RUNTIME_VERSION}"

    //Retrofit
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT_VERSION}"

    //Moshi
    const val MOSHI = "com.squareup.moshi:moshi:${Versions.MOSHI_VERSION}"
    const val MOSHI_CONVERTER = "com.squareup.retrofit2:converter-moshi:${Versions.MOSHI_CONVERTER_VERSION}"
    const val MOSHI_COMPILER = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.MOSHI_VERSION}"

    //Coroutines
    const val KOTLIN_COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_VERSION}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES_VERSION}"
}

object TestDependencies {
    const val JUNIT = "junit:junit:${Versions.JUNIT_VERSION}"
    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_JUNIT_VERSION}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_VERSION}"
    const val COMPOSE_UI = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE_VERSION}"
}

object Plugins {
    const val ANDROID_GRADLE_PLUGIN = "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_VERSION}"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN_GRADLE_VERSION}"
}

object Versions {
    const val BUILD_TOOLS_VERSION = "30.0.3"
    const val COMPOSE_NAV_VERSION = "1.0.0-alpha10"
    const val KTX_VERSION = "1.3.2"
    const val KOTLIN = "1.3.21"
    const val APPCOMPAT_VERSION = "1.2.0"
    const val MATERIAL_VERSION = "1.3.0"
    const val JUNIT_VERSION = "4.13.2"
    const val ANDROIDX_JUNIT_VERSION = "1.1.2"
    const val ESPRESSO_VERSION = "3.3.0"
    const val ANDROID_GRADLE_VERSION = "7.0.0-alpha14"
    const val KOTLIN_GRADLE_VERSION = "1.4.32"
    const val COMPOSE_VERSION = "1.0.0-beta05"
    const val LIFECYCLE_RUNTIME_VERSION = "2.3.1"
    const val ACTIVITY_COMPOSE_VERSION = "1.3.0-alpha07"
    const val RETROFIT_VERSION = "2.9.0"
    const val MOSHI_VERSION = "1.11.0"
    const val MOSHI_CONVERTER_VERSION = "2.9.0"
    const val COROUTINES_VERSION = "1.4.0"
}