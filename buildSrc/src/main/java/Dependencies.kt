object Versions{
    const val kotlin = "1.7.0"
    const val appcompat = "1.5.1"
    const val material = "1.7.0"
    const val constraintLayout = "2.1.4"
    const val junit = "4.13.2"
    const val androidxJunit = "1.1.4"
    const val espresso = "3.5.0"
}

object Dependencies {
    const val androidx = "androidx.core:core-ktx:${Versions.kotlin}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val junit = "junit:junit:${Versions.junit}"
    const val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}
