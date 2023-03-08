package me.randhirgupta

object Versions {

  const val kotlinCoroutines = "1.6.4"
  const val kotlinxSerialization = "1.4.1"
  const val ktor = "2.2.2"

  const val sqlDelight = "1.5.5"

  const val activityCompose = "1.6.0-rc02"
  const val lifecycleKtx = "2.6.0-alpha02"
  const val lifecycleRuntimeKtx = lifecycleKtx
  const val lifecycleViewmodelKtx = lifecycleKtx

  const val composeMaterial3 = "1.0.0"

  const val junit = "4.12"
  const val androidXTestJUnit = "1.1.3"
  const val testCore = "1.3.0"
  const val mockito = "3.11.2"
  const val robolectric = "4.6.1"

  const val composeCompiler = "1.4.3"
  const val compose = "1.4.0-alpha03"
  const val navCompose = "2.5.2"
  const val toolingPreview = "1.3.3"
  const val foundation = "1.3.1"

  const val koinCore = "3.3.2"
  const val koinAndroid = "3.3.2"
  const val koinAndroidCompose = "3.4.1"
  const val hiltVersion = "2.44"

  object AndroidSdk {
    const val min = 21
    const val compile = 33
    const val target = compile
  }

  object Deps {

    object Kotlinx {
      const val serializationCore = "org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerialization"
      const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutines"
      const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinCoroutines"
    }

    object AndroidX {
      const val lifecycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycleRuntimeKtx}"
      const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
      const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"

      const val material3 = "androidx.compose.material3:material3:$composeMaterial3"
    }

    object Test {
      const val junit = "junit:junit:${Versions.junit}"
      const val androidXTestJUnit = "androidx.test.ext:junit:${Versions.androidXTestJUnit}"
      const val mockito = "org.mockito:mockito-inline:${Versions.mockito}"
      const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
      const val testCore = "androidx.test:core:${Versions.testCore}"
    }

    object Compose {
      const val compiler = "androidx.compose.compiler:compiler:$composeCompiler"
      const val ui = "androidx.compose.ui:ui:$compose"
      const val uiGraphics = "androidx.compose.ui:ui-graphics:$compose"
      const val uiTooling = "androidx.compose.ui:ui-tooling:$compose"
      const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$toolingPreview"
      const val foundationLayout = "androidx.compose.foundation:foundation-layout:$compose"
      const val androidXfoundation = "androidx.compose.foundation:foundation:$foundation"
      const val material = "androidx.compose.material:material:$compose"
      const val navigation = "androidx.navigation:navigation-compose:$navCompose"

      const val coilCompose = "io.coil-kt:coil-compose:2.0.0"

      const val composeImageLoader = "io.github.qdsfdhvh:image-loader:1.2.9"
    }

    object Hilt {
      const val hiltAndroid = "com.google.dagger:hilt-android:$hiltVersion"
      const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
      const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0"
    }

    object Ktor {
      const val serverCore = "io.ktor:ktor-server-core:$ktor"
      const val serverNetty = "io.ktor:ktor-server-netty:$ktor"
      const val serverCors = "io.ktor:ktor-server-cors:$ktor"
      const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:$ktor"
      const val json = "io.ktor:ktor-serialization-kotlinx-json:$ktor"

      const val serverContentNegotiation = "io.ktor:ktor-server-content-negotiation:$ktor"

      const val clientCore = "io.ktor:ktor-client-core:$ktor"
      const val clientJson = "io.ktor:ktor-client-json:$ktor"
      const val clientLogging = "io.ktor:ktor-client-logging:$ktor"
      const val clientSerialization = "io.ktor:ktor-client-serialization:$ktor"
      const val clientAndroid = "io.ktor:ktor-client-android:$ktor"
      const val clientIOS = "io.ktor:ktor-client-darwin:$ktor"
    }

    object SqlDelight {
      const val runtime = "com.squareup.sqldelight:runtime:$sqlDelight"
      const val coroutineExtensions = "com.squareup.sqldelight:coroutines-extensions:$sqlDelight"
      const val androidDriver = "com.squareup.sqldelight:android-driver:$sqlDelight"
      const val nativeDriver = "com.squareup.sqldelight:native-driver:$sqlDelight"
      const val nativeDriverMacos = "com.squareup.sqldelight:native-driver-macosarm64:$sqlDelight"
      const val sqliteDriver = "com.squareup.sqldelight:sqlite-driver:$sqlDelight"
    }
  }
}
