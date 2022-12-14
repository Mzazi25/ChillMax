package com.example.chillmax.domain.models.responses

import com.example.chillmax.domain.models.TVAiringToday
import com.example.chillmax.domain.models.TVTopRated
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class TVAiringTodayApiResponses(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val searches: List<TVAiringToday>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)