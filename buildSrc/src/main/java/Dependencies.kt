import org.gradle.api.JavaVersion

object Versions{
    const val kotlin = "1.7.0"
    const val appcompat = "1.5.1"
    const val material = "1.7.0"
    const val constraintLayout = "2.1.4"
    const val junit = "4.13.2"
    const val androidxJunit = "1.1.4"
    const val espresso = "3.5.0"
    const val androidTools = "7.3.1"
    const val jetbrainsKotlin = "1.7.10"
    const val navigationFragment = "2.5.3"
    const val navigationUi = "2.5.3"
}

object Configs{
    const val namespace = "com.example.kotlindls"
    const val compileSdk = 33
    const val applicationId = "com.example.kotlindls"
    const val minSdk = 24
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object Plugins{
    const val application = "com.android.application"
    const val android = "android"
}

object BuildTypes {
    const val getByName = "release"
    const val getDefaultProguardFile = "proguard-android-optimize.txt"
    const val rules = "proguard-rules.pro"
}

object CompileOptions{
    val sourceCompatibility = JavaVersion.VERSION_11
    val targetCompatibility = JavaVersion.VERSION_11
}

object KotlinOptions{
    const val jvmTarget = "11"
}

object Dependencies {
    const val androidx = "androidx.core:core-ktx:${Versions.kotlin}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val junit = "junit:junit:${Versions.junit}"
    const val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationFragment}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigationUi}"
}

object Classpath{
    const val androidTools = "com.android.tools.build:gradle:${Versions.androidTools}"
    const val jetbrainsKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.jetbrainsKotlin}"
}
