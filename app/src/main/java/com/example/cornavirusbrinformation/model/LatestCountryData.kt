package com.example.cornavirusbrinformation.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LatestCountryData (

    @SerializedName("confirmed")
    @Expose
    var confirmed : Long? = null,

    @SerializedName("recovered")
    @Expose
    var recovered : Long? = null,

    @SerializedName("critical")
    @Expose
    var critical : Long? = null,

    @SerializedName("deaths")
    @Expose
    var deaths : Long? = null

)