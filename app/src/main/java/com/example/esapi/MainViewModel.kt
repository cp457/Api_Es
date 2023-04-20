package com.example.esapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://dog.ceo/%22")
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val apiService: ApiServiceInterface = retrofit.create(ApiServiceInterface::class.java)
    private var dog = MutableLiveData<RetrofitData>()
    val dog2: LiveData<RetrofitData>
        get() = dog

    private var error = MutableLiveData<String>()
    val error2: LiveData<String>
        get() = error

    fun dogApi() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                dog.value = apiService.getData()
            } catch (e: Exception) {
                error.value = e.localizedMessage
            }
        }
    }
}







