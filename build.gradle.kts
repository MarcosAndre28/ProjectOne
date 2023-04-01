// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Classpath.androidTools)
        classpath(Classpath.jetbrainsKotlin)
        classpath(Classpath.gradle)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    }
}
tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}