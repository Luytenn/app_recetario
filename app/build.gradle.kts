plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.recipefood"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.recipefood"
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
        kotlinCompilerExtensionVersion = "1.5.1"
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
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Icons extensions
    implementation(libs.material.icons.extended)


    //test fake data
    implementation(libs.javafaker)

    //Room
    api(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    kapt(libs.androidx.room.compiler)

    //Paging
    implementation(libs.pagingCompose)
    // OkHttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)
    testImplementation(libs.okhttp.mockwebserver)
    implementation(libs.gson)

    //coil
    implementation(libs.coil)
    implementation(libs.coil.compose)
    // Retrofit

    implementation(libs.retrofit.converter.gson)
    api(libs.retrofit)

    //api(libs.moshi)
    //api(libs.retrofit.converter.moshi)

    // DataStore
    implementation(libs.datastore)

    // Hilt
    implementation(libs.hilt.compose)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.library)

    //pager
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicator)

    //Navigation
    implementation(libs.lottie)
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.androidx.navigation.compose)

    // Androidx
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.compose.constraint.layout)
    implementation(libs.androidx.splashscreen)
}