plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hiltCompiler)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.androidx.navigation.safeargs)
}

android {
    namespace = "com.example.lamodacopy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lamodacopy"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }


    buildFeatures {
        viewBinding = true
        dataBinding = true
    }



    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.firebase.bom)
    implementation (libs.firebase.storage.ktx)
    implementation (libs.firebase.firestore.ktx)
    implementation (libs.glide)
    implementation(libs.firebase.inappmessaging.ktx)
    kapt ("com.github.bumptech.glide:compiler:4.12.0")
    implementation(libs.kotlin.stdlib)
    implementation(libs.play.services.maps)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.material)
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v282)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.recyclerview)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Consider the need for MySQL in your project
    // implementation("mysql:mysql-connector-java:8.0.28")

    implementation(libs.joda.time) // Keep it if necessary
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.database.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.ui.auth)
    implementation(libs.androidx.browser)

    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.android)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.play.services.analytics.impl)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

// Apply plugin for Google Services at the top level
apply(plugin = "com.google.gms.google-services")