package com.example.recipefood.ui.screen.details.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DishTypes(dishTypes: List<String>) {
    LazyRow(modifier = Modifier.padding(top = 10.dp)) {
        items(dishTypes.size) { index ->
            Chip(
                modifier = Modifier.padding(end = 6.dp),
                onClick = { },
                border = BorderStroke(
                    ChipDefaults.OutlinedBorderSize, MaterialTheme.colorScheme.tertiary
                ),
                colors = ChipDefaults.chipColors(
                    backgroundColor = MaterialTheme.colorScheme.tertiary, contentColor = MaterialTheme.colorScheme.background
                ),
            ) {
                Text(
                    dishTypes[index].replaceFirstChar { firstChar -> firstChar.uppercase() }
                )
            }

        }
    }
}