


plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.shifat.myhadis"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.shifat.myhadis"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation("com.google.android.material:material:1.12.0")
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.material3.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    val hilt = "2.50"
    implementation("com.google.dagger:hilt-android:$hilt")
    ksp("com.google.dagger:hilt-compiler:$hilt")
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0") // Check for the latest version
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0") // Check for the latest version
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0") // Check for the latest version
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0") // Check for the latest version
    implementation("androidx.compose.runtime:runtime:1.1.0") // Check for the latest version
    implementation("androidx.compose.ui:ui:1.1.0") // Check for the lates
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    // Jetpack Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.6.0")



}