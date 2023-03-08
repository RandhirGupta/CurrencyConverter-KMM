package com.cyborg.currencyconverter.android.presentation.navigation

sealed class Screens(val route: String) {
  object Home : Screens("homeScreen")
}
