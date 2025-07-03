pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Lesson4"
include(":app")
include(":app:thread")
include(":thread")
include(":thread:data_thread")
include(":thread:data_thread2")
include(":looper")
include(":cryptoloader")
include(":serviceapp")
include(":workmanager")
