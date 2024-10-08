package com.example.myapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.myapplication.R
import com.example.myapplication.common.BASE_IMAGE_URL
import com.example.myapplication.data.model.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val movieState by viewModel.moviesState.collectAsState()
            LaunchedEffect(viewModel) {
                viewModel.fetchTopRatedMovies()
            }
            when (movieState) {
                is UiState.Success -> {
                    MaterialTheme {
                        ItemList(items = (movieState as UiState.Success).data)
                    }
                }

                is UiState.Failure -> {
                    Text(text = "Something went wrong!")
                }

                is UiState.Loading -> {
                    LoadingUi()
                }
            }
        }
    }
}

@Composable
fun LoadingUi() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ItemList(items: List<Movie>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(items) { item ->
            ListItemView(item)
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )

        }
    }
}

@Composable
fun ListItemView(item: Movie) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(BASE_IMAGE_URL + item.poster_path)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = "Movie Poster",
            contentScale = ContentScale.Fit,
            placeholder = painterResource(R.drawable.ic_launcher_background),
            modifier = Modifier
                .height(200.dp)
                .padding(end = 8.dp),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
        ) {
            Text(text = item.title, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = item.overview,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemList() {
    MaterialTheme {
//        ItemList(
//            listOf(
//                ListItem("Preview Title 1", "Preview Description 1"),
//                ListItem("Preview Title 2", "Preview Description 2"),
//            )
//        )
    }
}