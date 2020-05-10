package com.example.cornavirusbrinformation.gateway

import com.example.cornavirusbrinformation.model.Country
import retrofit2.Call
import retrofit2.http.GET

interface ApiCountry {
    @GET("/countries/BR")
    fun getCountry() : Call<Country>
}