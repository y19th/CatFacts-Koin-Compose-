package com.example.catfacts.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.catfacts.R
import com.example.catfacts.domain.events.FactsEvents
import com.example.catfacts.domain.models.Facts
import com.example.catfacts.domain.viewmodels.FactsViewModel
import com.example.catfacts.presentation.screens.destinations.FactsScreenDestination
import com.example.catfacts.ui.theme.BaseTheme
import com.example.catfacts.ui.theme.Typography
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel


@RootNavGraph(start = true)
@[Composable Destination]
fun FactsScreen(
    navigator: DestinationsNavigator
) {

    val viewModel = getViewModel<FactsViewModel>()
    val state = viewModel.state.collectAsState()

    BaseTheme (
        isNetworkError = state.value.isNetworkError,
        onReload = {
            navigator.navigate(FactsScreenDestination())
        }
    ){
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.all_facts),
                        style = Typography.bodyMedium
                    )

                    Text(
                        text = "total ${state.value.listModel.total}",
                        style = Typography.bodyMedium
                    )
                }
            }
            items(state.value.listModel.factsList) {
                FactItem(item = it)
            }
            item {
                if (!state.value.isLastPage) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                viewModel.onEvent(FactsEvents.OnShowMore)
                            },
                            modifier = Modifier.padding(16.dp),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.show_more),
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
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