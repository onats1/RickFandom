plugins {
    androidLibrary
    kotlinAndroid
    kotlinKapt
    daggerAndroidPlugin
    id("com.apollographql.apollo3").version("3.3.0")
}

apollo {
    packageName.set("com.onats.episodes")
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
    implementation(project(UIModules.CORE_UI_COMPONENTS))
    implementation(project(CoreModules.CORE_EPISODES_MODULE))
    implementation(project(DataModules.EPISODES_REMOTE_MODULE))
    implementation(project(DomainModules.EPISODES_DOMAIN_MODULE))
    implementation(Dependencies.GRAPH_QL)
    implementation(Dependencies.TIMBER)
    implementation(Dependencies.KTX_CORE)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL_COMPONENTS)
    testImplementation(TestDependencies.JUNIT)
    androidTestImplementation(TestDependencies.ANDROIDX_JUNIT)
    androidTestImplementation(TestDependencies.ESPRESSO)
}