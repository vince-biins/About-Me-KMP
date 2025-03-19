plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)}

kotlin {

    androidLibrary {
        namespace = "com.project.home"
        compileSdk = 35
        minSdk = 24

    }


    val xcfName = "featureHomeKit"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                // Add KMP dependencies here
//                implementation(compose.runtime)
//                implementation(compose.foundation)
//                implementation(compose.material3)
//                implementation(compose.ui)
//                implementation(compose.components.resources)
//
//                api(libs.koin.core)
//                implementation(libs.koin.compose)
//                implementation(libs.koin.compose.viewmodel)
//                implementation(libs.navigation.compose)
//                implementation(libs.androidx.lifecycle.viewmodel)
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