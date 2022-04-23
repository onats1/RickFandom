plugins {
    androidLibrary
    kotlinAndroid
    kotlinKapt
    daggerAndroidPlugin
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

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", BASE_URL)
        }
        release {
            buildConfigField("String", "BASE_URL", BASE_URL)
        }
    }

    buildFeatures {
        compose = true
    }

    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/*.kotlin_module")
        exclude("META-INF/LGPL2.1")
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

    api(project(CoreModules.CORE_CHARACTERS_MODULE))
    api(project(CoreModules.CORE_COMMON_MODULE))
    api(project(CoreAndroidModules.CORE_ANDROID_CHARACTERS_MODULE))
    api(project(CoreAndroidModules.CORE_ANDROID_COMMON_MODULE))
    implementation(project(DataModules.CHARACTERS_REMOTE_MODULE))
    implementation(project(DomainModules.CHARACTERS_DOMAIN_MODULE))
    implementation(project(UIModules.CORE_UI_COMPONENTS))
    implementation(project(UIModules.CHARACTERS_UI_COMPONENTS))
    implementation(Dependencies.TIMBER)

    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL_COMPONENTS)

    junitTestDependencies()
    daggerTestDependencies()
    truthTestDependencies()
    androidTestImplementation(TestDependencies.ESPRESSO)
    androidTestImplementation(TestDependencies.COMPOSE_UI)
    androidTestImplementation(TestDependencies.DAGGER_HILT_ANDROID_TEST)
    debugImplementation(TestDependencies.COMPOSE_UI_MANIFEST)
}