package com.example.catfacts.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.catfacts.R
import com.example.catfacts.domain.models.Facts
import com.example.catfacts.domain.viewmodels.FactsViewModel
import com.example.catfacts.ui.theme.Typography
import org.koin.androidx.compose.getViewModel

@Composable
fun FactsScreen() {

    val viewModel = getViewModel<FactsViewModel>()
    val state = viewModel.state.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                text = stringResource(id = R.string.random_fact),
                style = Typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        items(state.value.factsList) {
            FactItem(item = it)
        }
        item {
            Text(
                text = stringResource(id = R.string.all_facts),
                style = Typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        items(10) {
            FactItem(item = Facts(fact = "fact $it"))
        }
    }
}

@Composable
fun FactItem(item: Facts) {
    Column(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = item.fact,
            style = Typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = stringResource(id = R.string.fact_lenght, item.length),
            style = Typography.bodySmall
        )
    }
}