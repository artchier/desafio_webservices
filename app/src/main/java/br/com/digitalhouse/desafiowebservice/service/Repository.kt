package br.com.digitalhouse.desafiowebservice.service

import br.com.digitalhouse.desafiowebservice.model.Res
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Repository {

    @GET("characters/1009610/comics")
    suspend fun getResults(
        @Query("offset") p1: Int,
        @Query("limit") p2: Int,
        @Query("ts") p3: String,
        @Query("apikey") p4: String,
        @Query("hash") p5: String
    ): Res

}

val urlApiMarvel = "http://gateway.marvel.com/v1/public/"

val retrofit = Retrofit.Builder()
    .baseUrl(urlApiMarvel)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val repository: Repository = retrofit.create(Repository::class.java)