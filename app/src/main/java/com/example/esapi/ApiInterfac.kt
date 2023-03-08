package com.example.esapi

import retrofit2.http.GET

interface ApiInterface {

    interface ApiServiceInterface {
        @GET("/api/breeds/list/all")
        suspend fun getData() : RetrofitData
    }
}