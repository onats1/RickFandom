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

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    kotlinDependencies()
    networkDependencies()
    implementation(project(CoreModules.CORE_COMMON_MODULE))
    implementation(project(CoreModules.CORE_CHARACTERS_MODULE))
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL_COMPONENTS)

    testImplementation (TestDependencies.MOCKITO)
    truthTestDependencies()
    junitTestDependencies()
    androidTestImplementation(TestDependencies.ESPRESSO)
}