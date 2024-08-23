// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.hiltCompiler) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    alias(libs.plugins.androidx.navigation.safeargs) apply false
}



buildscript{
    dependencies {
    }
}