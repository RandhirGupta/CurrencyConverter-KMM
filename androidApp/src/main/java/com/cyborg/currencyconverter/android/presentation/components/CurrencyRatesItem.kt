@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package com.cyborg.currencyconverter.android.presentation.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cyborg.currencyconverter.domain.models.CurrencyRate

fun LazyGridScope.currenciesRatesList(
  currenciesRates: List<CurrencyRate>,
) {
  items(currenciesRates) {
    CurrencyRatesItem(
      currencyRate = it,
      modifier = Modifier
        .fillMaxWidth()
        .animateItemPlacement(tween(durationMillis = 500))
    )
  }
}

@Composable
fun CurrencyRatesItem(
  modifier: Modifier = Modifier,
  currencyRate: CurrencyRate,
) {
  ElevatedCard(
    modifier = modifier,
    shape = RoundedCornerShape(12.dp),
  ) {

    Text(
      text = currencyRate.name,
      modifier = Modifier.textModifier(horizontalPadding = 4.dp, verticalPadding = 4.dp),
      maxLines = 2,
      overflow = TextOverflow.Ellipsis,
      style = MaterialTheme.typography.titleLarge,
      textAlign = TextAlign.Center
    )

    Spacer(modifier = Modifier.height(2.dp))

    Text(
      text = currencyRate.value.toString(),
      modifier = Modifier.textModifier(horizontalPadding = 4.dp, verticalPadding = 4.dp),
      maxLines = 4,
      overflow = TextOverflow.Ellipsis,
      style = MaterialTheme.typography.bodyMedium,
      color = contentColorFor(MaterialTheme.colorScheme.surface).copy(alpha = 0.5f),
      textAlign = TextAlign.Center
    )
  }
}
