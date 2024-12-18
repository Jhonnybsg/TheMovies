package com.example.myapplication.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.myapplication.R
import com.example.myapplication.common.BASE_IMAGE_URL
import com.example.myapplication.data.model.Movie
import com.example.myapplication.ui.theme.PurpleGrey40
import com.example.myapplication.ui.theme.background

@Composable
fun MovieListUi(
    modifier: Modifier = Modifier,
    onClickMovieDetail: (movieId: Int) -> Unit
) {

    val viewModel: MovieViewModel = hiltViewModel()

    val movieState by viewModel.moviesState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchTopRatedMovies()
    }

    when (movieState) {

        is UiState.Success -> {
            MovieList(
                items = (movieState as UiState.Success).data,
                onclick = onClickMovieDetail
            )
        }

        is UiState.Failure -> {
            Text(text = "Something went wrong!")
        }

        is UiState.Loading -> {
            LoadingUi()
        }

    }
}

@Composable
fun MovieList(
    items: List<Movie> = listOf(),
    onclick: (id: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = background)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        items(items) { item ->
            ListItemView(item, onclick)
        }
    }
}

@Composable
fun ListItemView(
    item: Movie,
    onclick: (movieId: Int) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable {
                onclick(item.id)
            }. padding(top = 8.dp, bottom = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
    ) {
        Row(
            modifier = Modifier.background(color = PurpleGrey40)
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
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = item.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                    text = item.overview,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewItemList() {
    MaterialTheme {
        MovieList(
            listOf(
                Movie(
                    adult = false,
                    backdrop_path = "",
                    genre_ids = arrayListOf(),
                    id = 0,
                    original_language = "",
                    original_title = "",
                    overview = "A really great movie!",
                    popularity = 0.0f,
                    poster_path = "",
                    release_date = "",
                    title = "Amazing Movie!",
                    video = false,
                    vote_average = 0.0f,
                    vote_count = 0
                ),
                Movie(
                    adult = false,
                    backdrop_path = "",
                    genre_ids = arrayListOf(),
                    id = 0,
                    original_language = "",
                    original_title = "",
                    overview = "A really great movie!",
                    popularity = 0.0f,
                    poster_path = "",
                    release_date = "",
                    title = "Amazing Movie!",
                    video = false,
                    vote_average = 0.0f,
                    vote_count = 0
                )
            ),
            onclick = {}
        )
    }
}