package com.example.recipefood.ui.screen.favorites

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.recipefood.R
import com.example.recipefood.data.source.local.toRecipe
import com.example.recipefood.ui.navigation.Navigate

@Composable
fun FavoriteRecipesScreen(
    navController: NavController
) {

    Favorites(
        viewModel = hiltViewModel(),
        navController
    )
}

@SuppressLint("SuspiciousIndentation")
@Composable
 fun Favorites(
    viewModel: FavoritesViewModel,
    navController: NavController
) {
    val viewState by viewModel.state.collectAsState()

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp)
                .fillMaxSize()
        ) {
            NavbarFav(navController = navController)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(viewState.recipes.count()) { index ->
                    val item = viewState.recipes[index]
                    println("FavoritesScreen items: " + item )
                    item?.let {
                        InspirationItem(item,
                            onDeleteClick = {
                                viewModel.deleteFavRecipeSelected(item.toRecipe())
                            })
                    }
                }
            }

    }
}

@Composable
fun NavbarFav(
    navController: NavController
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        /*
        Image(
            painterResource(R.drawable.ic_back_arrow),
            contentDescription = "back_button",
            modifier = Modifier
                .size(24.dp)
                .clickable { navController.navigateUp() },
        )
        */
        Icon(
            modifier = Modifier
                .size(27.dp)
                .clickable { navController.navigateUp() },
            imageVector = Icons.Filled.KeyboardArrowLeft,
            contentDescription = "Home Icon",
            tint = MaterialTheme.colorScheme.onBackground // Optional: Change the icon color
        )
        Text(
            text = stringResource(R.string.recipe_favorite),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp,
        )
        Spacer(modifier = Modifier.width(50.dp))
        Icon(
            modifier = Modifier
                .size(27.dp).alpha(0f),
            imageVector = Icons.Filled.Star,
            contentDescription = "Home Icon",
            tint = MaterialTheme.colorScheme.onBackground // Optional: Change the icon color
        )
        Icon(
            modifier = Modifier
                .size(27.dp).alpha(0f),
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "Home Icon",
            tint = MaterialTheme.colorScheme.onBackground // Optional: Change the icon color
        )

    }
}