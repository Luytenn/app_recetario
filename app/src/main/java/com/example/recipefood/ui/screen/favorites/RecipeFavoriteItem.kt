package com.example.recipefood.ui.screen.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recipefood.R
import com.example.recipefood.data.source.local.RecipeEntity
import com.example.recipefood.ui.components.DeleteButton

@Composable
fun InspirationItem(
    recipe: RecipeEntity,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit,
) {
    ConstraintLayout(
        modifier = Modifier
            .width(250.dp)
            .padding(8.dp)
            .clickable {  }
    ) {
        val (image, time, title, source) = createRefs()
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp)
                .constrainAs(image) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    width = Dimension.fillToConstraints
                },
            color = MaterialTheme.colors.background,
            elevation = 8.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context =  LocalContext.current).crossfade(true).data(recipe.image).build(),
                    contentDescription = "Cuisine image",
                    modifier = Modifier.fillMaxSize() ,
                    contentScale = ContentScale.Crop
                )
                DeleteButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background,
                    deleteClick = {
                        onDeleteClick()
                    },
                    selected = true
                )
            }
        }
        Text(
            text = "${recipe.title}MIN",
            color = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.subtitle2,
            maxLines = 1,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .constrainAs(time) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    linkTo(
                        top = image.bottom,
                        bottom = title.top
                    )
                }
        )
        Text(
            text = recipe.title ?: "",
            color = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.subtitle1,
            maxLines = 1,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(title) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    linkTo(
                        top = time.bottom,
                        bottom = source.top
                    )
                }
        )
        Text(
            text = "by ${recipe.title}",
            style = MaterialTheme.typography.subtitle2,
            color = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
            maxLines = 1,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .constrainAs(source) {
                    linkTo(
                        start = parent.start,
                        end = parent.end
                    )
                    linkTo(
                        top = title.bottom,
                        bottom = parent.bottom
                    )
                }
        )
    }
}