@file:OptIn(ExperimentalMaterial3Api::class)

package com.cyborg.currencyconverter.android.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingView() {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = CenterHorizontally
  ) {
    CircularProgressIndicator(modifier = Modifier.wrapContentWidth(CenterHorizontally))
  }
}

@Composable
fun ErrorView(message: String = "Oops! Something went wrong,\n Please refresh after some time!") {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = CenterHorizontally
  ) {
    ErrorText(text = message)
  }
}

@Composable
fun ErrorText(text: String, modifier: Modifier = Modifier) {
  Text(
    text = text,
    modifier = modifier.textModifier(horizontalPadding = 48.dp),
    style = MaterialTheme.typography.body2,
    textAlign = TextAlign.Center, color = MaterialTheme.colors.error.copy(alpha = 0.9F)
  )
}

fun Modifier.textModifier(horizontalPadding: Dp = 0.dp, verticalPadding: Dp = 0.dp) = this
  .fillMaxWidth()
  .padding(horizontal = horizontalPadding, vertical = verticalPadding)
