package com.example.chillmax.presentation.details.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.chillmax.R
import com.example.chillmax.domain.models.responses.CastDetailsApiResponse
import com.example.chillmax.presentation.ui.theme.SMALL_PADDING
import com.example.chillmax.util.Constants.IMAGE_BASE_URL

@ExperimentalCoilApi
@Composable
fun CastDetails(
    casts: CastDetailsApiResponse?,
    scrollState: LazyListState
){
    Column(
        modifier = Modifier.padding(SMALL_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                modifier= Modifier.weight(8f),
                text = stringResource(R.string.casts),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                modifier= Modifier.weight(2f),
                text = stringResource(R.string.view_all),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Medium,
                color = Color.White

            )
            IconButton(
                modifier= Modifier.weight(2f),
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = stringResource(R.string.arrow_forward),
                    tint = Color.White
                )
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            state = scrollState
        ){
            items(casts!!.casts){ cast->
                CastItem(
                    size = 100.dp,
                    imageUrl = "${IMAGE_BASE_URL}/${cast.profilePath}",
                    castName = cast.name
                )
            }
        }
    }
}

