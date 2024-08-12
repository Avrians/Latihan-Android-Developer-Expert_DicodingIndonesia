package com.avrians.myreactivesearch.network

import com.avrians.myreactivesearch.model.PlaceResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("mapbox.places/{query}.json")
    suspend fun getCountry(
        @Path("query") query: String,
        @Path("access_token") accessToken: String,
        @Path("autocomplete") autoComplete: Boolean = true,
    ) : PlaceResponse
}