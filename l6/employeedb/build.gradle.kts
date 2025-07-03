val annotationProcessor: Unit
    get() {
        TODO()
    }

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "ru.mirea.sladkinaaa.employeedb"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.mirea.sladkinaaa.employeedb"
        minSdk = 26
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
}

dependencies {
    implementation(libs.androidx.appcompat.v161)
    implementation(libs.material.v190)
    implementation(libs.androidx.activity.v172)
    implementation(libs.androidx.constraintlayout.v214)

    // Room
    implementation(libs.androidx.room.runtime.v272)
    annotationProcessor(libs.androidx.room.compiler.v272)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)



}

