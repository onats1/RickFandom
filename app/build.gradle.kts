import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    androidApplication
    kotlinAndroid
    kotlinKapt
    kotlinParcelize
}

android {

    compileSdkVersion(DefaultConfig.COMPILE_SDK_VERSION)
    buildToolsVersion(Versions.BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(DefaultConfig.MIN_SDK_VERSION)
        targetSdkVersion(DefaultConfig.TARGET_SDK_VERSION)
        applicationId(DefaultConfig.APPLICATION_ID)
        versionCode(DefaultConfig.VERSION_CODE)
        versionName(DefaultConfig.VERSION_NAME)
        testInstrumentationRunner(DefaultConfig.TEST_INSTRUMENTATION_RUNNER)
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_VERSION
        kotlinCompilerVersion = Versions.KOTLIN_GRADLE_VERSION
    }

}

dependencies {

    implementation(project(Libraries.CORE))
    implementation(project(Libraries.CORE_ANDROID))
    implementation(project(Libraries.LOCATION_LIB))
    implementation(project(Libraries.EPISODES_LIB))
    implementation(project(Libraries.CHARACTERS_LIB))
    implementation(Dependencies.KTX_CORE)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL_COMPONENTS)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_TOOLING)
    implementation(Dependencies.LIFECYCLE_RUNTIME)
    implementation(Dependencies.ACTIVITY_COMPOSE)
    implementation(Dependencies.COMPOSE_NAV)
    kapt(Dependencies.DAGGER_COMPILER)
    kapt(Dependencies.DAGGER_HILT_COMPILER)
    implementation(Dependencies.DAGGER)
    implementation(Dependencies.DAGGER_HILT)
    implementation(Dependencies.NAVIGATION_RUNTIME)
    testImplementation(TestDependencies.JUNIT)
    androidTestImplementation(TestDependencies.ANDROIDX_JUNIT)
    androidTestImplementation(TestDependencies.ESPRESSO)
    androidTestImplementation(TestDependencies.COMPOSE_UI)
}