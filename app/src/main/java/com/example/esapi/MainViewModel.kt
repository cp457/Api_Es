package com.example.esapi

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class MainViewModel : ViewModel() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://dog.ceo/%22")
        .addConverterFactory(GsonConverterFactory.create()).build()



    private val apiService: ApiServiceInterface = retrofit.create(ApiServiceInterface::class.java)
    private var dog = MutableSharedFlow<RetrofitData>()

    private var error = MutableSharedFlow<String>()


    fun dogApi() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                dog.emit( apiService.getData())
            } catch (e: Exception) {
                error.emit(e.localizedMessage)
            }
        }
    }
}







