enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        // declares an additional catalog
    }
}

rootProject.name = "KMMAssignmentFetchAPI"
include(":androidApp")
include(":shared")