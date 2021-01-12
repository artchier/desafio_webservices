package br.com.digitalhouse.desafiowebservice.model

import java.util.*
import kotlin.collections.ArrayList

data class Res(val data: Data)

data class Data(
    val offset: Int, var results: ArrayList<Results>
)

data class Results(
    var title: String,
    val description: String,
    val pageCount: Int,
    val dates: ArrayList<ComicDate>,
    val prices: ArrayList<ComicPrice>,
    val thumbnail: Image,
    val images: ArrayList<Image>
)

data class ComicDate(val type: String, val date: String)

data class ComicPrice(val price: Double)

data class Image(val path: String, val extension: String)

