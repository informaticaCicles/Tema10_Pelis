package com.example.peliculesapi

import com.google.gson.annotations.SerializedName

data class PeliResponse(
    @SerializedName("entries") val num_entradas: Int,
    @SerializedName("results") val resultados: List<MovieResponse>
)

data class MovieResponse(
    @SerializedName("primaryImage") val urlImage: ImageInfo,
    @SerializedName("titleText") val title: TitleText
)

data class ImageInfo(
    @SerializedName("url") val imageUrl: String
){
    override fun toString(): String {
        return  imageUrl
    }
}

data class TitleText(
    @SerializedName("text") val title: String
){
    override fun toString(): String {
        return title
    }
}