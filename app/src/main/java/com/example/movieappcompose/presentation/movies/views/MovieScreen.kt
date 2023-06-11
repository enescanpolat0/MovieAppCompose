package com.example.movieappcompose.presentation.movies.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieappcompose.presentation.Screen
import com.example.movieappcompose.presentation.movies.MoviesEvent
import com.example.movieappcompose.presentation.movies.MoviesViewModel

@Composable
fun MovieScreen(navController: NavController,viewModel: MoviesViewModel= hiltViewModel()) {

    val state = viewModel.state.value
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)){
        Column {
            MovieSearchBar(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
                hint = "Batman",
                onSearch = {
                    viewModel.onEvent(MoviesEvent.search(it))
                }
                )

            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(state.movies){ movie->
                    MovieListRow(movie=movie, onItemClick = {
                        navController.navigate(Screen.MovieDetailScreen.route+"/${movie.imdbID}")
                        //moviedetail screen /
                    })
                }
            }


        }
    }

}

//bu search barı her uygulmada kullanabilirsin custom olarak
@Composable
fun MovieSearchBar(modifier: Modifier,
                   hint : String ="",
                   onSearch : (String)-> Unit = {}
                   ) {

    var text by remember{
        mutableStateOf("")
    }

    var ishintDisplayed by remember{
        mutableStateOf(hint !="")
    }


    Box(modifier = modifier) {

        TextField(value = text, onValueChange = {
            text = it
        }, keyboardActions = KeyboardActions(onDone = {onSearch(text)})
        , maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
            modifier = Modifier.fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(color = Color.White)
                .padding(horizontal = 20.dp)
                .onFocusChanged {
                    ishintDisplayed = it.isFocused != true && text.isEmpty()
                }
        )

        if (ishintDisplayed){
            Text(text = hint,color = Color.LightGray,modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
        }
    }

}

