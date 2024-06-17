// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        /*classpath ("com.google.gms:google-services:4.4.0")*/
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.49")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.9.22")


    }

}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.androidKotlin) apply false
    alias(libs.plugins.kotlin.jvm) apply false


    alias(libs.plugins.google.hilt) apply false
    alias(libs.plugins.google.ksp) apply false
    alias(libs.plugins.parcelize) apply false
}