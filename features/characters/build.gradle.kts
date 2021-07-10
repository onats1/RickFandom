plugins {
    androidLibrary
    kotlinAndroid
    kotlinKapt
}

android {

    compileSdk = DefaultConfig.COMPILE_SDK_VERSION
    buildToolsVersion = Versions.BUILD_TOOLS_VERSION

    defaultConfig {
        minSdk = DefaultConfig.MIN_SDK_VERSION
        targetSdk = DefaultConfig.TARGET_SDK_VERSION
        testInstrumentationRunner = DefaultConfig.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_VERSION
        kotlinCompilerVersion = Versions.KOTLIN_GRADLE_VERSION
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    composeDependencies()
    daggerDependencies()
    kotlinDependencies()
    networkDependencies()

    implementation(project(CoreModules.CORE_CHARACTERS_MODULE))
    implementation(project(CoreModules.CORE_COMMON_MODULE))
    implementation(project(CoreAndroidModules.CORE_ANDROID_CHARACTERS_MODULE))
    implementation(project(CoreAndroidModules.CORE_ANDROID_COMMON_MODULE))
    implementation(project(DataModules.CHARACTERS_REMOTE_MODULE))
    implementation(project(DomainModules.CHARACTERS_DOMAIN_MODULE))

    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL_COMPONENTS)

    testImplementation(TestDependencies.JUNIT)
    androidTestImplementation(TestDependencies.ANDROIDX_JUNIT)
    androidTestImplementation(TestDependencies.ESPRESSO)
}