import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.androidLibrary)
}
android  {
    namespace = "com.project.utils"
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



    val xcfName = "baseUtilsKit"
    jvm()
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs{
        browser()
    }

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
                implementation(project(":base:theme"))
                implementation(libs.kotlin.stdlib)
                implementation(libs.coil.compose)

                implementation(libs.okio)
            }
        }


        androidMain {
            dependencies {

            }
        }


        iosMain {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }

    }

}