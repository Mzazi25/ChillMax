package com.example.chillmax.presentation.search.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import com.example.chillmax.presentation.destinations.MovieDetailsScreenDestination
import com.example.chillmax.presentation.destinations.TVDetailsScreenDestination
import com.example.chillmax.presentation.search.SearchViewModel
import com.example.chillmax.presentation.ui.theme.SMALL_PADDING
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
@Composable
fun SearchContent(
    navigator: DestinationsNavigator,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val multiSearch = viewModel.searchResult.value.collectAsLazyPagingItems()
    val trailingIconState = viewModel.trailingIconState.value
    Log.d("SearchContent", multiSearch.loadState.toString())
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.padding(SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) { items(multiSearch) { searchResult->
                SearchItem(
                    searchItem = searchResult,
                    modifier = Modifier
                        .height(130.dp)
                        .fillMaxWidth(),
                    onClick = {
                        when (searchResult?.mediaType) {
                            "tv" -> {
                                navigator.navigate(TVDetailsScreenDestination(searchResult.id!!))
                            }
                            "movies" -> {
                                navigator.navigate(MovieDetailsScreenDestination(searchResult.id!!))
                            }
                        }
                    }
                )
            }
            if (multiSearch.loadState.append == LoadState.Loading){
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        multiSearch.apply {
            when(loadState.refresh){
                is LoadState.Loading ->{
                    CircularProgressIndicator(
                        modifier = Modifier,
                        color = Color.Red,
                        strokeWidth = 2.dp
                    )
                }
                is LoadState.Error ->{
                    val error = multiSearch.loadState.refresh as LoadState.Error
                    Text(
                        text = when(error.error){
                            is HttpException ->{
                                "Oops! Something Went Wrong"
                            }
                            is IOException ->{
                                "Couldn't Reach Server! Check Your Internet Connection"
                            }
                            else->{
                                "Unknown Error"
                            }
                        },
                        textAlign = TextAlign.Center,
                        color = Color.Red
                    )
                }else->{

            }
            }
        }
    }
}