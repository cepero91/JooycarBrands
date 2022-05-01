import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

private val localProperties = gradleLocalProperties(rootDir)
fun getLocalProperty(key: String, defaultValue: String = ""): String =
    localProperties.getProperty(key, System.getenv(key) ?: defaultValue)

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.lumos.jooycarbrands"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        /*
        * This local variable can be used in C.I when you need to change
        * the base url from staging to production
        */
        val serverUrl = "SERVER_URL"
        val serverUrlKey = getLocalProperty(serverUrl)
        buildConfigField("String", serverUrl, "\"${serverUrlKey}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions.jvmTarget = "1.8"
    buildFeatures.compose = true

    composeOptions.kotlinCompilerExtensionVersion = "1.1.1"

    packagingOptions {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.10")

    val composeVersion = "1.1.1"
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")

    // -- DI
    val hiltVersion = "2.40.5"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    // -- Navigation
    implementation("androidx.navigation:navigation-compose:2.4.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // -- ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")

    // -- Accompanist
    implementation("com.google.accompanist:accompanist-insets:0.24.7-alpha")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.24.7-alpha")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.24.7-alpha")

    // -- Network
    val squareUpVersion = "2.9.0"
    val gsonVersion = "2.8.7"
    implementation("com.squareup.retrofit2:retrofit:$squareUpVersion")
    implementation("com.squareup.retrofit2:converter-gson:$squareUpVersion")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.9.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    implementation("com.google.code.gson:gson:$gsonVersion")

    // Coil
    implementation("io.coil-kt:coil-compose:1.4.0")
}