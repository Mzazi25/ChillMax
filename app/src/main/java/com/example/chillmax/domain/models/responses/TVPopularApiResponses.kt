package com.example.chillmax.domain.models.responses

import com.example.chillmax.domain.models.TVPopular
import kotlinx.serialization.Serializable

@Serializable
data class TVPopularApiResponses(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? =null,
    val genres: List<TVPopular> = emptyList()
)
