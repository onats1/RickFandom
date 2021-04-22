// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val compose_version by extra("1.0.0-beta05")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha14")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

subprojects {
   // project.plugins.configure(project)
}

fun PluginContainer.configure(project: Project) {
    whenPluginAdded {
        if (this is BasePlugin) {
            project.extensions
                .getByType<com.android.build.gradle.BaseExtension>()
                .apply {
                    applyAndroidCommons()
                }
        }
    }
}

fun com.android.build.gradle.BaseExtension.applyAndroidCommons() {
    compileSdkVersion(DefaultConfig.COMPILE_SDK_VERSION)
    buildToolsVersion(Versions.BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(DefaultConfig.MIN_SDK_VERSION)
        targetSdkVersion(DefaultConfig.TARGET_SDK_VERSION)
        applicationId(DefaultConfig.APPLICATION_ID)
        versionCode(DefaultConfig.VERSION_CODE)
        versionName(DefaultConfig.VERSION_NAME)
        testInstrumentationRunner(DefaultConfig.TEST_INSTRUMENTATION_RUNNER)
        vectorDrawables {
            useSupportLibrary = true
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}