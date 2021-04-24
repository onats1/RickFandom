
plugins {
    androidLibrary
    kotlinAndroid
    kotlinKapt
    kotlinParcelize
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Dependencies.KTX_CORE)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL_COMPONENTS)

    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.MOSHI)
    implementation(Dependencies.MOSHI_CONVERTER)
    kapt(Dependencies.MOSHI_COMPILER)

    testImplementation(TestDependencies.JUNIT)
    androidTestImplementation(TestDependencies.ANDROIDX_JUNIT)
    androidTestImplementation(TestDependencies.ESPRESSO)
}