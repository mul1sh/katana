import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
    repositories {
        google()
    }
}

plugins {
    id("com.android.application") version "3.5.3"
    kotlin("android") version "1.3.70"
    kotlin("android.extensions") version "1.3.70"
    id("com.github.ben-manes.versions") version "0.28.0"
}

android {
    compileSdkVersion(29)

    defaultConfig {
        minSdkVersion(19)
        targetSdkVersion(29)

        applicationId = "org.rewedigital.katana.android.example"
        versionCode = 1
        versionName = "1.13.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation("org.rewedigital.katana:katana-android:1.13.1")
    implementation("org.rewedigital.katana:katana-androidx-fragment:1.13.1")
    implementation("org.rewedigital.katana:katana-androidx-viewmodel-savedstate:1.13.1")
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.70")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3")
    implementation("org.jetbrains.anko:anko-coroutines:0.10.8")
    implementation("com.squareup.retrofit2:retrofit:2.6.2")
    implementation("com.squareup.retrofit2:converter-moshi:2.6.2")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("com.squareup.moshi:moshi:1.8.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.8.0") {
        exclude(group = "org.jetbrains.kotlin")
    }
    implementation("com.squareup.leakcanary:leakcanary-android:1.6.3")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test:rules:1.2.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
}

repositories {
    mavenLocal()
    google()
    jcenter()
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates") {
    fun isNonStable(version: String): Boolean {
        val stableKeyword =
            listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
        val regex = "^[0-9,.v-]+$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return isStable.not()
    }

    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}

