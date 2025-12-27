plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
}

android {
    namespace = "com.arsir.dev.arsir.arsirstore"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.arsir.dev.arsir.arsirstore"
        minSdk = 24
        targetSdk = 35
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
}

dependencies {
    // Data
    implementation(projects.data.splashScreen)
    implementation(projects.data.login)
    implementation(projects.data.product)
    implementation(projects.data.onboarding)

    // Feature
    implementation(projects.feature.splashScreen)
    implementation(projects.feature.login)
    implementation(projects.feature.home)
    implementation(projects.feature.detail)
    implementation(projects.feature.onboarding)

    // Core
    implementation(projects.core.database)

    // Dagger - Hilt
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)
}