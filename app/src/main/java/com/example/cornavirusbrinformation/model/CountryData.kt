package com.example.cornavirusbrinformation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountryData (
    @SerializedName("code")
    @Expose
    var code : String? = null,

    @SerializedName("name")
    @Expose
    var name : String? = null,

    @SerializedName("population")
    @Expose
    var population : Long? = null,

    @SerializedName("updated_at")
    @Expose
    var updatedAt : String? = null,

    @SerializedName("latest_data")
    @Expose
    var latestCountryData : LatestCountryData? = null

)