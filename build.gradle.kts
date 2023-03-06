buildscript {
  val kotlinVersion: String by project

  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:7.2.2")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
    classpath("org.jetbrains.kotlin:kotlin-serialization:${kotlinVersion}")
    classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
  }
}

group = "com.cyborg"
version = "1.0"

allprojects {
  repositories {
    google()
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven(url = "https://maven.pkg.jetbrains.space/public/p/kotlinx-coroutines/maven")
    maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}
