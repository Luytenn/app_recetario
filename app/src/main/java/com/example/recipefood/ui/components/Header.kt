package com.example.recipefood.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipefood.R

@Composable
fun Header(
    onClick:() -> Unit
) {
    Column(
        modifier = Modifier.padding
            (bottom = 20.dp, top = 20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(R.string.recipe_title),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 18.sp,
            )
            Icon(
                modifier = Modifier
                    .size(27.dp)
                    .clickable {
                        onClick()
                    },
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Home Icon",
                tint = MaterialTheme.colorScheme.onBackground // Optional: Change the icon color
            )
            /*
            Image(
                painterResource(R.drawable.profile_image),
                contentDescription = "profile_image",
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(80.dp)),
                contentScale = ContentScale.Crop
            )*/
        }
        var text by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(10.dp)),
            placeholder = { Text(text = "Search by recipes", color = MaterialTheme.colorScheme.secondary) },
            maxLines = 1,
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondary,
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colorScheme.primary,
                backgroundColor = MaterialTheme.colorScheme.onSecondary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }

}