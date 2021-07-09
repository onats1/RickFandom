
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
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT_VERSION}"
    const val MATERIAL_COMPONENTS = "com.google.android.material:material:${Versions.MATERIAL_VERSION}"

    //Jetpack Compose
    const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE_VERSION}"
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE_VERSION}"
    const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_VERSION}"
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE_VERSION}"
    const val COMPOSE_RUNTIME = "androidx.compose.runtime:runtime:${Versions.COMPOSE_VERSION}"
    const val COMPOSE_NAVIGATION = "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATION_VERSION}"

    //Lifecycle components
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE_RUNTIME_VERSION}"

    //Network
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT_MOSHI}"
    const val MOSHI = "com.squareup.moshi:moshi:${Versions.MOSHI}"
    const val MOSHI_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.MOSHI}"
    const val MOSHI_ADAPTER = "com.squareup.moshi:moshi-adapters:${Versions.MOSHI}"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"
}

object TestDependencies {
    const val JUNIT = "junit:junit:${Versions.JUNIT_VERSION}"
    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_JUNIT_VERSION}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_VERSION}"
    const val COMPOSE_UI = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE_VERSION}"
}

object Versions {
    const val BUILD_TOOLS_VERSION = "30.0.3"
    const val KTX_VERSION = "1.3.2"
    const val APPCOMPAT_VERSION = "1.2.0"
    const val MATERIAL_VERSION = "1.3.0"
    const val JUNIT_VERSION = "4.13.2"
    const val ANDROIDX_JUNIT_VERSION = "1.1.2"
    const val ESPRESSO_VERSION = "3.3.0"
    const val ANDROID_GRADLE_VERSION = "7.1.0-alpha02"
    const val KOTLIN_GRADLE_VERSION = "1.5.10"
    const val COMPOSE_VERSION = "1.0.0-beta09"
    const val COMPOSE_NAVIGATION_VERSION = "2.4.0-alpha04"
    const val LIFECYCLE_RUNTIME_VERSION = "2.3.1"
    const val ACTIVITY_COMPOSE_VERSION = "1.3.0-alpha07"

    //Network
    const val RETROFIT = "2.9.0"
    const val RETROFIT_MOSHI = "2.6.0"
    const val MOSHI = "1.12.0"
    const val OKHTTP = "4.9.1"
}