package com.cyborg.currencyconverter.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cyborg.currencyconverter.android.presentation.navigation.Screens.Home
import com.cyborg.currencyconverter.android.presentation.screen.home.HomeScreen

@Composable
fun MainNavHost(
  navController: NavHostController,
  modifier: Modifier = Modifier,
) {
  NavHost(
    navController = navController,
    startDestination = Home.route,
    modifier = modifier
  ) { composable(Home.route) { HomeScreen() } }
}
