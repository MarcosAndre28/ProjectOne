plugins {
    id (Plugins.application)
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
}

dependencies {
    implementation (Dependencies.androidx)
    implementation (Dependencies.appcompat)
    implementation (Dependencies.material)
    implementation (Dependencies.constraintLayout)
    testImplementation (Dependencies.junit)
    androidTestImplementation (Dependencies.androidxJunit)
    androidTestImplementation (Dependencies.espresso)
}