plugins {
    id("java-library")
    id("kotlin")
    id("com.apollographql.apollo3").version("3.2.1")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

apollo {
    packageName.set("com.onats.core_episodes")
}

dependencies {
    implementation("com.apollographql.apollo3:apollo-runtime:3.3.0")
}