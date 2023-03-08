package com.cyborg.currencyconverter.android.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CurrencySpinner(
  currencies: List<String>,
  selectedCurrency: String,
  onItemSelected: (String) -> Unit,
) {
  Spinner(
    modifier = Modifier.wrapContentSize(),
    items = currencies,
    selectedItem = selectedCurrency,
    onItemSelected = onItemSelected,
    selectedItemFactory = { modifier: Modifier, item: String ->
      Row(
        modifier = modifier
          .padding(8.dp)
          .wrapContentSize(),
        horizontalArrangement = Arrangement.End,
      )
      { Text(text = item) }
    }, dropdownItemFactory = { item, _ ->
      Text(text = item)
    }
  )
}
