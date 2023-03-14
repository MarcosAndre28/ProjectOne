import Plugins.kapt

plugins {
    id (Plugins.application)
    id (Plugins.kapt)
    kotlin (Plugins.android)
}

android {
    namespace = Configs.namespace
    compileSdk = Configs.compileSdk

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
        versionCode = Configs.versionCode
        versionName = Configs.versionName
        testInstrumentationRunner = Configs.testInstrumentationRunner
    }

    buildTypes {
        getByName(BuildTypes.getByName) {
            isMinifyEnabled = true
            proguardFiles (getDefaultProguardFile(BuildTypes.getDefaultProguardFile), BuildTypes.rules)
        }
    }
    compileOptions {
        sourceCompatibility = CompileOptions.sourceCompatibility
        targetCompatibility = CompileOptions.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = KotlinOptions.jvmTarget
    }
    viewBinding {
        enable = true
    }
}

dependencies {

   // Core
    implementation (Dependencies.androidx)

    // AppCompat
    implementation (Dependencies.appcompat)

    // Material
    implementation (Dependencies.material)

    // Layout
    implementation (Dependencies.constraintLayout)

    // Navigation
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUi)

    // Screen
    implementation(Dependencies.spd)

    // Test
    testImplementation (Dependencies.junit)
    androidTestImplementation (Dependencies.androidxJunit)
    androidTestImplementation (Dependencies.espresso)

    // Retrofit
    implementation(Dependencies.gson)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.converterGson)
    implementation(Dependencies.okhttp3)

    // Glide
    implementation(Dependencies.glide)
    kapt(Dependencies.glideCompiler)

    // Room
    implementation(Dependencies.room)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.roomRuntime)

    // Coroutines
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutines)

    // LifeCycle
    implementation(Dependencies.lifecycleViewmodel)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.lifecycle)

    // Timber logging
    implementation(Dependencies.timber)

    // Kotlin flow
    implementation(Dependencies.flow)
    // Card View
    implementation(Dependencies.cardView)

    // Recycler View
    implementation(Dependencies.recyclerView)

    // SVG
    implementation(Dependencies.androidsvg)

}