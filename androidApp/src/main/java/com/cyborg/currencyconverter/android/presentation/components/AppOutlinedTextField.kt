@file:OptIn(ExperimentalMaterial3Api::class)

package com.cyborg.currencyconverter.android.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType.Companion.Number
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.cyborg.currencyconverter.android.presentation.screen.home.HomeScreenViewModel

@Composable
fun AppOutlinedTextField(
  viewModel: HomeScreenViewModel,
) {
  val state = remember { mutableStateOf(TextFieldValue(text = "1")) }
  OutlinedTextField(
    value = state.value,
    onValueChange = { value ->
      state.value = value
      viewModel.updateBaseCurrencyValue(value.text)
    },
    label = { Text("Value") },
    modifier = Modifier
      .padding(8.dp)
      .fillMaxWidth(),
    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = Number),
  )
}
