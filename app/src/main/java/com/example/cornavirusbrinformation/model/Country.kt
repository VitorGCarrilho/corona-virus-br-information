package com.example.cornavirusbrinformation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Country (

    @SerializedName("data")
    @Expose
    var countryData : CountryData ? = null
)