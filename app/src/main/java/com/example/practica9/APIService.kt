package com.example.practica9

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun  getPeliculas(@Url url:String): Response<FilmResponse>
}