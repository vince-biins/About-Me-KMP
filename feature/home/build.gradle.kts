import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.androidLibrary)
}
android  {

    namespace = "com.project.composables"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

}
kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }


    val xcfNameHome = "featureHomeKit"
    jvm()
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }
    iosX64 {
        binaries.framework {
            baseName = xcfNameHome
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfNameHome
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfNameHome
        }
    }

    sourceSets {
        commonMain {
            dependencies {

                implementation(project(":base:composables"))

                implementation(libs.kotlin.stdlib)
                // Add KMP dependencies here
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)

                api(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                implementation(libs.navigation.compose)
                implementation(libs.androidx.lifecycle.viewmodel)
            }
        }



        androidMain {
            dependencies {

                implementation(libs.koin.android)
                implementation(libs.koin.androidx.compose)
            }
        }

    

        iosMain {
            dependencies {

            }
        }
    }

}