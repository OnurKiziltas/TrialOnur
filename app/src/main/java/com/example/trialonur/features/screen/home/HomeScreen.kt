package com.example.trialonur.features.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.trialonur.data.model.SatelliteList
import com.example.trialonur.features.component.HomeScreenCard
import com.example.trialonur.features.component.ItemShimmer
import com.example.trialonur.features.component.SearchBar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateSecondScreen: (SatelliteList?) -> Unit
) {
    val viewState = viewModel.uiState.collectAsState().value

    Content(
        isLoading = viewState.isLoading,
        data = viewState.data?.results,
        detailClick = {
            onNavigateSecondScreen.invoke(it)
        }
    )

}

@Composable
fun Content(
    isLoading: Boolean = false,
    data: List<SatelliteList>?,
    detailClick: (SatelliteList?) -> Unit
) {
    SearchBar(modifier = Modifier.padding(start = 30.dp, end = 30.dp, top = 15.dp),
        text = "", onTextChange = {}, onClickSearch = {})

    LazyColumn(
        Modifier.padding(top = 80.dp),
        contentPadding = PaddingValues(vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        if (isLoading) {
            items(count = 5) {
                ItemShimmer(modifier = Modifier)
            }
        } else {
            items(items = data ?: listOf()) { item ->
                HomeScreenCard(
                    title = item.name,
                    overview = item.active,
                    detailClick = { detailClick(item) }
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun BodyPreview() {
    HomeScreen(viewModel = hiltViewModel(), onNavigateSecondScreen = {})
}