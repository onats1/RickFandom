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

fun DependencyHandler.composeDependencies() {
    implementation(Dependencies.ACTIVITY_COMPOSE)
    implementation(Dependencies.COMPOSE_MATERIAL)
    implementation(Dependencies.COMPOSE_RUNTIME)
    implementation(Dependencies.COMPOSE_TOOLING)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_NAVIGATION)
}

fun DependencyHandler.networkDependencies() {
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_MOSHI)
    implementation(Dependencies.MOSHI)
    implementation(Dependencies.MOSHI_ADAPTER)
    kapt(Dependencies.MOSHI_CODEGEN)
}