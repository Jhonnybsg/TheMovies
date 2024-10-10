package com.example.myapplication.presentation

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.myapplication.R
import com.example.myapplication.common.BASE_IMAGE_URL

@Composable
fun MovieDetailUI(
    movie: Int
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.scrollable(
            state = scrollState, orientation = Orientation.Vertical
        )
    ) {
//        AsyncImage(
//            model = ImageRequest.Builder(LocalContext.current)
//                .data(BASE_IMAGE_URL + movie.backdrop_path)
//                .diskCachePolicy(CachePolicy.ENABLED)
//                .build(),
//            contentDescription = "Movie Backdrop",
//            contentScale = ContentScale.FillWidth,
//            placeholder = painterResource(R.drawable.ic_launcher_background),
//            modifier = Modifier
//                .height(200.dp)
//        )
    }

}