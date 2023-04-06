package com.example.trialonur.features.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trialonur.R


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (text: String) -> Unit,
    onClickSearch: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        SatelliteTextField(
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                SatelliteText(
                    text = "Search Satellite",
                    color = Color.Gray,
                )
            },
        )
        Box(
            Modifier
                .background(
                    color = MaterialTheme.colors.onPrimary,
                    shape = RoundedCornerShape(14.dp)
                )
                .size(46.dp)
                .clickable { onClickSearch() }
                .align(Alignment.CenterStart),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = null
            )
        }
    }


}

@Preview
@Composable
fun PreviewMinimaSearchBar() {
    SearchBar(text = "", onTextChange = {}, onClickSearch = {})
}