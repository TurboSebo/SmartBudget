plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("app.cash.sqldelight") version "2.0.1"
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    jvm()

    androidLibrary {
       namespace = "org.us.smartbudget.shared"
       compileSdk = libs.versions.android.compileSdk.get().toInt()
       minSdk = libs.versions.android.minSdk.get().toInt()
       androidResources {
           enable = true
       }
       withHostTest {
           isIncludeAndroidResources = true
       }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
        }
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(compose.materialIconsExtended)
            // SQLDelight - coroutines extensions for common code
            implementation("app.cash.sqldelight:coroutines-extensions:2.0.1")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        // JVM (desktop) driver for SQLDelight - zapis na dysku
        jvmMain.dependencies {
            implementation("app.cash.sqldelight:sqlite-driver:2.0.1")
        }
    }
}

dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
}

sqldelight {
    databases {
        create("SmartBudgetDatabase") {

            packageName.set("org.us.smartbudget.database")
        }
    }
}