import me.randhirgupta.Versions
import me.randhirgupta.Versions.AndroidSdk
import me.randhirgupta.Versions.Deps
import me.randhirgupta.Versions.Deps.AndroidX

plugins {
  id("com.android.application")
  kotlin("android")
}

@Suppress("UnstableApiUsage")
android {
  namespace = "com.cyborg.currencyconverter.android"
  compileSdk = AndroidSdk.compile
  defaultConfig {
    applicationId = "com.cyborg.currencyconverter.android"
    minSdk = AndroidSdk.min
    targetSdk = AndroidSdk.target

    versionCode = 1
    versionName = "1.0"
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Versions.composeCompiler
  }

  packagingOptions {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      isShrinkResources = true
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
  implementation(project(":shared"))

  with(AndroidX) {
    implementation(lifecycleRuntimeCompose)
    implementation(lifecycleRuntimeKtx)
    implementation(activityCompose)
    implementation(material3)
  }

  with(Deps.Compose) {
    implementation(compiler)
    implementation(ui)
    implementation(uiGraphics)
    implementation(foundationLayout)
    implementation(material)
    implementation(navigation)
    implementation(coilCompose)
    implementation(uiTooling)
    implementation(uiToolingPreview)
    implementation(androidXfoundation)
  }

  with(Deps.Koin) {
    implementation(core)
    implementation(android)
    implementation(compose)
    testImplementation(test)
    testImplementation(testJUnit4)
  }

  with(Deps.Test) {
    testImplementation(junit)
    androidTestImplementation(androidXTestJUnit)
    testImplementation(testCore)
    testImplementation(robolectric)
    testImplementation(mockito)
  }
}
