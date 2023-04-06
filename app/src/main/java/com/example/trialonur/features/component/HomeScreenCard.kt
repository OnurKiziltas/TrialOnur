package com.example.trialonur.features.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreenCard(
    title: String, overview: Boolean,
    detailClick: () -> Unit,
) {

    Row(modifier = Modifier.fillMaxWidth()
        .clickable { detailClick() },
        horizontalArrangement = Arrangement.Center,
        ) {
        Column(
            modifier = Modifier
                .padding(end = 15.dp, bottom = 15.dp)
                .align(Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (overview) {
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .clip(CircleShape)
                        .background(Color.Green),
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .clip(CircleShape)
                        .background(Color.Red),
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(bottom = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            if (overview) {
                Text(text = title,fontSize = 15.sp)
                Text(
                    text = "Active", fontSize = 12.sp,
                    maxLines = 3
                )
            } else {
                Text(text = title,fontSize = 15.sp, color = Color.Gray)
                Text(
                    text = "Passive",fontSize = 12.sp,color = Color.Gray,
                    maxLines = 3
                )
            }

        }
    }
    Divider(color = Color.Black, thickness = 1.dp)

}


@Preview(showBackground = true)
@Composable
private fun BodyPreview() {
    HomeScreenCard("title",
        false,
        detailClick = {})
}