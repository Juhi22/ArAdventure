plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "hu.dj.aradventure"
    //testNamespace = "hu.dj.aradventure"
    compileSdk = 34

    defaultConfig {
        applicationId = "hu.dj.aradventure"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
    //implementation("com.google.ar.sceneform:assets:1.17.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //implementation("com.google.ar:core:1.41.0")
    implementation("com.gorisse.thomas.sceneform:sceneform:1.23.0")
    //api(project(":sceneformux"))
    //implementation(files("../libs/libsceneform_runtime_schemas.jar"))
/*    implementation("com.google.ar.sceneform:core:1.17.1")
    implementation("com.google.ar.sceneform.ux:sceneform-ux:1.17.1")
    implementation("com.google.ar.sceneform:animation:1.17.1")
    implementation("com.google.ar.sceneform:assets:1.17.1")*/
    //implementation("com.google.ar.sceneform:filament-android:1.17.1")
/*    implementation("com.google.android.filament:filament-android:1.6.0")
    implementation("com.google.android.filament:filament-utils-android:1.6.0")*/
    //implementation("com.google.android.filament:filament-java:1.6.0")
    //implementation("com.google.android.filament:gltfio-android:1.6.0")
}