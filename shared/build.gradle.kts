import me.randhirgupta.Versions.AndroidSdk
import me.randhirgupta.Versions.Deps
import me.randhirgupta.Versions.Deps.Ktor
import me.randhirgupta.Versions.Deps.SqlDelight

plugins {
  kotlin("multiplatform")
  id("com.android.library")
  id("kotlinx-serialization")
  id("com.squareup.sqldelight")
}

kotlin {
  android {
    compilations.all {
      kotlinOptions {
        jvmTarget = "11"
      }
    }
  }

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach {
    it.binaries.framework {
      baseName = "shared"
    }
  }

  sourceSets {
    val commonMain by getting {
      dependencies {

        with(Ktor) {
          implementation(clientCore)
          implementation(clientJson)
          implementation(clientLogging)
          implementation(json)
          implementation(contentNegotiation)
        }

        with(Deps.Kotlinx) {
          implementation(coroutinesCore)
          implementation(serializationCore)
        }

        with(SqlDelight) {
          implementation(runtime)
          implementation(coroutineExtensions)
        }

        with(Deps.Koin) {
          api(core)
          api(test)
        }
      }
    }

    val commonTest by getting {
      dependencies {
        implementation(Deps.Koin.test)
        implementation(Deps.Kotlinx.coroutinesTest)
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }

    val androidMain by getting {
      dependencies {
        implementation(Ktor.clientAndroid)
        implementation(SqlDelight.androidDriver)
      }
    }

//        val androidUnitTest by getting
    val iosX64Main by getting
    val iosArm64Main by getting
    val iosSimulatorArm64Main by getting
    val iosMain by creating {
      dependsOn(commonMain)
      iosX64Main.dependsOn(this)
      iosArm64Main.dependsOn(this)
      iosSimulatorArm64Main.dependsOn(this)

      dependencies {
        implementation(Ktor.clientIOS)
        implementation(SqlDelight.nativeDriver)
      }
    }
    
    val iosX64Test by getting
    val iosArm64Test by getting
    val iosSimulatorArm64Test by getting
    val iosTest by creating {
      dependsOn(commonTest)
      iosX64Test.dependsOn(this)
      iosArm64Test.dependsOn(this)
      iosSimulatorArm64Test.dependsOn(this)
    }
  }
}

@Suppress("UnstableApiUsage")
android {
  namespace = "com.cyborg.currencyconverter"
  compileSdk = AndroidSdk.compile

  defaultConfig {
    minSdk = AndroidSdk.min
    targetSdk = AndroidSdk.target
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
}

sqldelight {
  database("ExchangeRatesDatabase") {
    packageName = "com.cyborg.currencyconverter.database"
    sourceFolders = listOf("sqldelight")
  }
}
