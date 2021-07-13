plugins {
    id("java-library")
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Dependencies.KOTLIN_COROUTINES)
    implementation(Dependencies.KOTLIN_STD_LIB)
}