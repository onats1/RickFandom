import org.gradle.api.artifacts.dsl.DependencyHandler

private fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}
private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}
private fun DependencyHandler.compileOnly(depName: String) {
    add("compileOnly", depName)
}
private fun DependencyHandler.api(depName: String) {
    add("api", depName)
}
private fun DependencyHandler.testImplementation(depName: String) {
    add("testImplementation", depName)
}
private fun DependencyHandler.androidTestImplementation(depName: String) {
    add("androidTestImplementation", depName)
}
private fun DependencyHandler.kaptTest(depName: String) {
    add("kaptTest", depName)
}
private fun DependencyHandler.kaptAndroidTest(depName: String) {
    add("kaptAndroidTest", depName)
}

fun DependencyHandler.composeDependencies() {
    implementation(Dependencies.ACTIVITY_COMPOSE)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_RUNTIME)
    implementation(Dependencies.COMPOSE_TOOLING)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_NAVIGATION)
    implementation(Dependencies.COMPOSE_HILT)
    implementation(Dependencies.ACCOMPANIST_SYSTEM_CONTROLLER)
}

fun DependencyHandler.networkDependencies() {
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_MOSHI)
    implementation(Dependencies.MOSHI)
    implementation(Dependencies.MOSHI_ADAPTER)
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_LOGGING)
    kapt(Dependencies.MOSHI_CODEGEN)
}

fun DependencyHandler.kotlinDependencies() {
    implementation(Dependencies.KOTLIN_COROUTINES)
    implementation(Dependencies.KOTLIN_REFLECT)
    implementation(Dependencies.KOTLIN_STD_LIB)
    implementation(Dependencies.KOTLIN_STD_LIB_JDK)
    implementation(Dependencies.KTX_CORE)
}

fun DependencyHandler.daggerDependencies() {
    implementation(Dependencies.DAGGER_HILT)
    kapt(Dependencies.DAGGER_HILT_COMPILER)
}

fun DependencyHandler.daggerTestDependencies() {
    androidTestImplementation(TestDependencies.DAGGER_HILT_ANDROID_TEST)
    kaptAndroidTest(Dependencies.DAGGER_HILT_COMPILER)
}

fun DependencyHandler.junitTestDependencies() {
    testImplementation(TestDependencies.JUNIT)
    testImplementation(TestDependencies.TRUTH)
    testImplementation(TestDependencies.MOCKITO)
    androidTestImplementation(TestDependencies.ANDROIDX_JUNIT)
}

fun DependencyHandler.truthTestDependencies() {
    testImplementation(TestDependencies.TRUTH)
    androidTestImplementation(TestDependencies.TRUTH)
}


















