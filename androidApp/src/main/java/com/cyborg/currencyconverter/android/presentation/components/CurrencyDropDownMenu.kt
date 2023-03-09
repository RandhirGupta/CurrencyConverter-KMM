package com.cyborg.currencyconverter.android.presentation.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun CurrencySpinner(
  currencies: List<String>,
  baseCurrency: String,
  onItemSelected: (String) -> Unit,
) {
  val contextForToast = LocalContext.current.applicationContext
  val baseCurrencyIndex = currencies.indexOf(baseCurrency)
  var expanded by remember { mutableStateOf(false) }
  var selectedItem by remember { mutableStateOf(currencies[baseCurrencyIndex]) }
  var textFieldSize by remember { mutableStateOf(Size.Zero) }
  val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

  Column(
    Modifier
      .padding(8.dp)
      .fillMaxWidth()
      .border(1.dp, Black)
  ) {
    CompositionLocalProvider(
      LocalTextInputService provides null
    ) {
      TextField(
        value = selectedItem,
        onValueChange = { selectedItem = it },
        modifier = Modifier
          .fillMaxWidth()
          .onGloballyPositioned { coordinates -> textFieldSize = coordinates.size.toSize() },
        trailingIcon = { Icon(icon, "contentDescription", Modifier.clickable { expanded = !expanded }, tint = Blue) }
      )
    }

    DropdownMenu(
      expanded = expanded,
      onDismissRequest = { expanded = false },
      modifier = Modifier
        .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        .height(350.dp)
        .background(White)
    ) {
      currencies.forEach { selectedOption ->
        DropdownMenuItem(onClick = {
          selectedItem = selectedOption
          onItemSelected(selectedItem)
          expanded = false
          Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
        }) {
          Text(text = selectedOption)
        }
      }
    }
  }
}
