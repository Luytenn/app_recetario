@file:Suppress("UNUSED_EXPRESSION")

package com.example.recipefood.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.recipefood.domain.model.RecipeList.Recipe
import com.example.recipefood.ui.navigation.Navigate

@Composable
fun RecipeItem(
    recipe: Recipe, navontroller: NavController,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .height(260.dp) // Adjust height to accommodate the description
            .clickable {
                onClick()
            println("Recipe Item clicked")
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(Color(0xFF90000000)
                    )
                    // Height for the image section
            ) {
                Image(
                    painter = rememberAsyncImagePainter(recipe.image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                )
                Text(
                    text = recipe.title,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(all = 16.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }

            // Description below the image
            Text(
                text = recipe.title , // Replace with the actual description
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                color = Color.Black,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                maxLines = 5, // Adjust to control overflow
                overflow = TextOverflow.Ellipsis
            )
        }
    }
    /*
    Column(modifier = Modifier.clip(RoundedCornerShape(20.dp))) {
        Image(
            painter = rememberAsyncImagePainter(recipe.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds,
        )
        Box(modifier = Modifier.fillMaxWidth()
            .height(50.dp)) {
            Text(
                text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(all = 16.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                fontSize = 11.sp,
                textAlign = TextAlign.Center,
            )
        }
    }*/
}