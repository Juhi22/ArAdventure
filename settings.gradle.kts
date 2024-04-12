pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://maven.google.com") }
    }
}

rootProject.name = "ArAdventure"
include(":app")

/*// Add these lines:
include(":sceneform")
project(":sceneform").projectDir=File("sceneformsrc/sceneform")

include(":sceneformux")
project(":sceneformux").projectDir=File("sceneformux/ux")*/
