package com.example.cornavirusbrinformation.gateway

import android.widget.Toast
import com.example.cornavirusbrinformation.infrastructure.NetworkClient
import com.example.cornavirusbrinformation.model.Country
import com.example.cornavirusbrinformation.network.RetrofitEventListener

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCountryRestClient {
    companion object {
        val instance = ApiCountryRestClient()
    }

    private var apiCountry : ApiCountry? = null

    fun getCountryInformation(retrofitEventListener: RetrofitEventListener) {
        val retrofit = NetworkClient.retrofitClient
        apiCountry = retrofit.create<ApiCountry>(ApiCountry::class.java)

        val apiCountryCall = apiCountry!!.getCountry()

        apiCountryCall.enqueue(object : Callback<Country> {

            override fun onResponse(call: Call<Country>?, response: Response<Country>?) {
                /*This is the success callback. Though the response type is JSON, with Retrofit we get the response in the form of WResponse POJO class
                 */
                if (response?.body() != null) {
                    retrofitEventListener.onSuccess(call, response?.body())
                }
            }
            override fun onFailure(call: Call<Country>?, t: Throwable?) {
                /*
                Error callback
                */

                retrofitEventListener.onError(call, t)
            }

        })
    }

}