@file:OptIn(ExperimentalMaterial3Api::class)

package com.cyborg.currencyconverter.android.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun AppBar(
  modifier: Modifier = Modifier,
  title: String,
  isBackEnabled: Boolean = false,
  scrollBehavior: TopAppBarScrollBehavior,
  textStyle: TextStyle = MaterialTheme.typography.displaySmall,
  buttons: List<AppBarButton> = emptyList(),
  onBackPress: () -> Unit = {},
) {
  LargeTopAppBar(
    title = { Text(text = title, style = textStyle) },
    modifier = modifier,
    navigationIcon = { SetNavigationIcon(isBackEnabled, onBackPress) },
    actions = { buttons.forEach { AppBarButton(appBarButton = it) } },
    scrollBehavior = scrollBehavior
  )
}

@Composable
private fun SetNavigationIcon(isBackEnabled: Boolean, onBackPress: () -> Unit) {
  if (isBackEnabled)
    Icon(
      imageVector = Icons.Rounded.ArrowBack,
      contentDescription = "Back",
      modifier = Modifier
        .clip(CircleShape)
        .clickable { onBackPress() }
        .padding(8.dp)
    )
}
