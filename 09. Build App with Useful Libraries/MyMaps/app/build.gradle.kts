plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.avrians.mymaps"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.avrians.mymaps"
        minSdk = 21
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation ("com.mapbox.mapboxsdk:mapbox-android-sdk:9.2.0")
    implementation ("com.mapbox.mapboxsdk:mapbox-android-plugin-annotation-v8:0.7.0")
    implementation ("com.mapbox.mapboxsdk:mapbox-android-navigation-ui:0.42.6")
    implementation ("com.mapbox.mapboxsdk:mapbox-android-telemetry:6.1.0")
}