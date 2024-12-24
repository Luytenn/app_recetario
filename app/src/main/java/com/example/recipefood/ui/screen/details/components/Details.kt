package com.example.recipefood.ui.screen.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.recipefood.R
import com.example.recipefood.domain.model.RecipeDetails.RecipeDetails


@Composable
fun DetailsHeader(recipeDetails: RecipeDetails) {
    Image(
        painter = rememberAsyncImagePainter(recipeDetails.image),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(20.dp)),
        contentScale = ContentScale.FillBounds,
    )
    Text(
        text = recipeDetails.title,
        modifier = Modifier.padding(top = 16.dp, bottom = 6.dp),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
    )
    Text(
        text = stringResource(id = R.string.instructions)  ,
        modifier = Modifier.padding(top = 16.dp, bottom = 6.dp),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = MaterialTheme.colorScheme.primary,
        textAlign = TextAlign.Left,
        fontSize = 18.sp,
    )
    val openDialog = remember { mutableStateOf(false) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                println("onDimissRequest clicked")
            openDialog.value = false },
            backgroundColor = MaterialTheme.colorScheme.background,
            title = { }, text = {
                Column {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = stringResource(id = R.string.instructions),
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                        Icon(
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {  openDialog.value = false },
                            imageVector =Icons.Outlined.Close,
                            contentDescription = "Home Icon",
                            tint = MaterialTheme.colorScheme.secondary // Optional: Change the icon color
                        )
                    }

                    Text(
                        recipeDetails.instructions ?: "",
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 15.sp,
                        modifier = Modifier
                    )
                }

        }, buttons = {})
    }
    Box(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .clickable {
                openDialog.value = true
            },
    ) {
        var hasVisualOverflow by remember { mutableStateOf(false) }
        Text(
            text = recipeDetails.instructions ?: "",
            maxLines = 3,
            onTextLayout = { hasVisualOverflow = it.hasVisualOverflow },
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 15.sp,
        )

        if (hasVisualOverflow) {
            Row(
                modifier = Modifier.align(Alignment.BottomEnd), verticalAlignment = Alignment.Bottom
            ) {
                Spacer(modifier = Modifier)
                Text(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .padding(start = 8.dp),
                    text = stringResource(R.string.recipe_details_instructions_show_more),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 15.sp,
                )
            }
        }
    }
}