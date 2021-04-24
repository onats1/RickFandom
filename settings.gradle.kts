dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
    }
}
rootProject.name = "RickFandom"
include(
    ":app",
    ":features:characters",
    ":features:episodes",
    ":features:location",
    ":core",
    ":core-android"
)

