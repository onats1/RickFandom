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
        testInstrumentationRunner = "com.onats.characters.CharacterTestRunner"
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

    kotlinDependencies()
    composeDependencies()
    implementation(project(UIModules.CORE_UI_COMPONENTS))
    implementation(project(CoreModules.CORE_CHARACTERS_MODULE))
    implementation(project(CoreModules.CORE_COMMON_MODULE))
    implementation(Dependencies.ACCOMPANIST_COIL)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.COIL)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL_COMPONENTS)

    junitTestDependencies()
    androidTestImplementation(TestDependencies.ESPRESSO)
}