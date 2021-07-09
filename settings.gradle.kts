dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
    }
}
rootProject.name = "RickFandom"
include(":app")
include(":features:characters")
include(":features:episodes")
include(":features:location")

include(":features:home")
include(":core:common")
include(":coreandroid:common")
include(":data:characters-remote")
include(":domain:characters-domain")
include(":core:core-character")
include(":coreandroid:core-android-characters")
