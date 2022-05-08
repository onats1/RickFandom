plugins {
    id("java-library")
    id("kotlin")
    id("com.apollographql.apollo3").version("3.3.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

apollo {
    packageName.set("com.onats.core_episodes")
}

dependencies {
    implementation(project(CoreModules.CORE_COMMON_MODULE))
    implementation(Dependencies.GRAPH_QL)
    implementation(Dependencies.KOTLIN_COROUTINES)
    implementation(Dependencies.KOTLIN_STD_LIB)
}