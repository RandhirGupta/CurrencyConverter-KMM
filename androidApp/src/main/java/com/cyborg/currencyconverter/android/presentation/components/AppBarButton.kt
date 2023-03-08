package com.cyborg.currencyconverter.android.presentation.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class AppBarButton(
  val icon: ImageVector,
  val contentDescription: String,
  val onClick: () -> Unit,
)

@Composable
fun AppBarButton(
  appBarButton: AppBarButton,
) {
  FilledIconButton(
    onClick = appBarButton.onClick,
    colors = IconButtonDefaults.filledIconButtonColors(
      containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp),
      contentColor = MaterialTheme.colorScheme.secondary
    ),
    modifier = Modifier.clip(CircleShape)
  ) {
    Icon(
      imageVector = appBarButton.icon,
      contentDescription = appBarButton.contentDescription
    )
  }
}
