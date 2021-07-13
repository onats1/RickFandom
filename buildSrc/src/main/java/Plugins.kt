import org.gradle.api.Plugin
import org.gradle.api.Project


class AndroidModulePlugins: Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply("kotlin-android")
        target.plugins.apply("kotlin-android-extensions")
        target.plugins.apply("com.android.library")

        val androidExtension = target.extensions.getByName("android")

    }
}