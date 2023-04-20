package com.example.esapi

import android.annotation.SuppressLint
import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


class AuthorizationInterceptor : Interceptor {

        @SuppressLint("SuspiciousIndentation")
        override fun intercept(chain: Interceptor.Chain): Response {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://dog.ceo/api/breeds/list/all")
                    .get()
                    .build()

                    return try {
                        client.newCall(request).execute()
                    } catch (e: Exception) {
                        Log.d("MainActivity","error ${e.message}")
                        throw e
                    }
        }
    }

