package com.example.recipefood.ui.components

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun DeleteButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    selected: Boolean = false,
    deleteClick: () -> Unit
) {
    val icon = if (selected) Icons.Outlined.Delete else Icons.Filled.Delete
    Surface(
        color = backgroundColor,
        shape = CircleShape,
        modifier = modifier
            .requiredSize(36.dp, 36.dp)
            .clickable {
                deleteClick()
            }
    ) {
        Icon(
            imageVector = icon,
            tint = androidx.compose.material3.MaterialTheme.colorScheme.onSurface,
            contentDescription = null,
            modifier = Modifier
                .padding(6.dp)
        )
    }
}