package com.cyborg.currencyconverter.android.presentation.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.cyborg.currencyconverter.android.MyApplicationTheme
import com.cyborg.currencyconverter.android.presentation.navigation.MainNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setMainActivityContent()
  }

  private fun setMainActivityContent() {
    setContent {
      MyApplicationTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = Color.White
        ) {
          val navController = rememberNavController()
          MainNavHost(navController = navController, modifier = Modifier.fillMaxSize())
        }
      }
    }
  }
}
