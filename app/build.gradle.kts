plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.testvkproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.testvkproject"
        minSdk = 26
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    //Glide
    implementation("com.github.bumptech.glide:glide:4.15.0")
    implementation("com.google.android.datatransport:transport-runtime:3.2.0")
    implementation("com.google.firebase:firebase-inappmessaging-display-ktx:20.4.0")
    implementation("com.google.firebase:firebase-functions-ktx:20.4.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.0")

    // LiveData(lifecycle)
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")

    // ViewModel(lifecycle)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.fragment:fragment-ktx:1.6.1")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.0")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    api("com.squareup.okhttp3:logging-interceptor:3.12.6")
    api("com.squareup.okhttp3:okhttp-urlconnection:3.12.6")

    //dagger
    implementation("com.google.dagger:dagger:2.51")
    kapt("com.google.dagger:dagger-compiler:2.51")

    //paging
    implementation("androidx.paging:paging-runtime-ktx:3.1.0")

    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.0")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}