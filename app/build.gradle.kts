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
    implementation (Dependencies.androidx)
    implementation (Dependencies.appcompat)
    implementation (Dependencies.material)
    implementation (Dependencies.constraintLayout)
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUi)
    implementation(Dependencies.spd)
    testImplementation (Dependencies.junit)
    androidTestImplementation (Dependencies.androidxJunit)
    androidTestImplementation (Dependencies.espresso)

    implementation(Dependencies.lifecycle)

    implementation(Dependencies.gson)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.converterGson)

    implementation(Dependencies.glide)
    kapt(Dependencies.glideCompiler)

}