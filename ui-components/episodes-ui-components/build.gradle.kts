plugins {
    androidLibrary
    kotlinAndroid
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

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    kotlinDependencies()
    composeDependencies()
    implementation(project(UIModules.CORE_UI_COMPONENTS))
    implementation(project(CoreModules.CORE_EPISODES_MODULE))
    implementation(project(CoreModules.CORE_COMMON_MODULE))
    implementation(Dependencies.ACCOMPANIST_COIL)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.COIL)
    implementation(Dependencies.MATERIAL_COMPONENTS)
    androidTestImplementation(TestDependencies.COMPOSE_UI)
    debugImplementation(TestDependencies.COMPOSE_UI_MANIFEST)
    junitTestDependencies()
    androidTestImplementation(TestDependencies.ESPRESSO)
}