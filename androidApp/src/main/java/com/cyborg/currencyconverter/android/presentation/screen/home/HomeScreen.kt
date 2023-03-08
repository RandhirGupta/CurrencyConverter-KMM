@file:OptIn(ExperimentalMaterial3Api::class)

package com.cyborg.currencyconverter.android.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cyborg.currencyconverter.android.presentation.components.*
import com.cyborg.currencyconverter.presentation.home.HomeScreenState.*

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel = hiltViewModel()) {
  val state by viewModel.uiState.collectAsState()
  when (state) {
    is Loading -> LoadingView()
    is Success -> HomeScreenContent(state = state as Success, viewModel = viewModel)
    is Error -> ErrorView()
  }
}

@Composable
private fun HomeScreenContent(
  modifier: Modifier = Modifier,
  state: Success,
  viewModel: HomeScreenViewModel,
) {
  val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

  Column(
    modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.Center
  ) {
    AppOutlinedTextField(viewModel)
    Spacer(modifier = Modifier.height(8.dp))
    CurrencySpinner(currencies = state.exchangeRates.currenciesRates.map { it.name }, selectedCurrency = "USD", onItemSelected = viewModel::updateBaseCurrencyUpdate)
    Spacer(modifier = Modifier.height(8.dp))
    LazyVerticalGrid(
      modifier = modifier,
      contentPadding = PaddingValues(4.dp),
      verticalArrangement = Arrangement.spacedBy(4.dp),
      horizontalArrangement = Arrangement.spacedBy(4.dp),
      columns = GridCells.Fixed(3)
    ) {
      currenciesRatesList(currenciesRates = state.exchangeRates.currenciesRates)
    }
  }
}
