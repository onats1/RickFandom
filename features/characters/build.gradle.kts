plugins {
    androidLibrary
    kotlinAndroid
}

android {

    compileSdkVersion(DefaultConfig.COMPILE_SDK_VERSION)
    buildToolsVersion(Versions.BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(DefaultConfig.MIN_SDK_VERSION)
        targetSdkVersion(DefaultConfig.TARGET_SDK_VERSION)
        testInstrumentationRunner(DefaultConfig.TEST_INSTRUMENTATION_RUNNER)
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Dependencies.KTX_CORE)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL_COMPONENTS)
    testImplementation(TestDependencies.JUNIT)
    androidTestImplementation(TestDependencies.ANDROIDX_JUNIT)
    androidTestImplementation(TestDependencies.ESPRESSO)
}