import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.androidLibrary)

    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.googleServices)
}

android  {

    namespace = "com.project.composables"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

}
kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
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
                implementation(project(":base:theme"))
                implementation(project(":base:utils"))

                implementation(libs.kotlin.stdlib)
                // Add KMP dependencies here
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(libs.material3.window.size.class1.multiplatform)
                implementation(compose.ui)
                implementation(compose.components.resources)
                api(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)

                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.navigation.compose)
                implementation(libs.kotlinx.serialization.json)


                //SUPABASE
                implementation(project.dependencies.platform("io.github.jan-tennert.supabase:bom:3.1.2"))
                implementation(libs.postgrest.kt)
                implementation(libs.auth.kt)
                implementation(libs.realtime.kt)


            }
        }


        val jvmMain by getting {
            dependencies {
                implementation(libs.ktor.client.cio)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.ktor.client.cio)
                implementation(libs.koin.android)
                implementation(libs.koin.androidx.compose)
            }
        }


        val wasmJsMain by getting {
            dependencies {
                implementation(libs.ktor.client.wasmJs)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }

}