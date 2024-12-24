package com.example.recipefood.ui.screen.details.components

import android.text.BoringLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.recipefood.R
import com.example.recipefood.ui.navigation.Navigate


@Composable
fun Navbar(navController: NavController, isRecipeFavSaved: Boolean, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Icon(
            modifier = Modifier
                .size(27.dp)
                .clickable { navController.navigateUp() },
            imageVector = Icons.Filled.KeyboardArrowLeft,
            contentDescription = "Home Icon",
            tint = MaterialTheme.colorScheme.onBackground // Optional: Change the icon color
        )
        Text(
            text = stringResource(R.string.recipe_details_title),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp,
        )
        Spacer(modifier = Modifier.width(50.dp))
        Icon(
            modifier = Modifier
                .size(27.dp)
                .clickable {   onClick()
                     },
            imageVector = if(isRecipeFavSaved) Icons.Outlined.Star else Icons.Filled.StarBorder,
            contentDescription = "Home Icon",
            tint = MaterialTheme.colorScheme.onBackground // Optional: Change the icon color
        )

    }
}