package com.example.esapi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.esapi.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FragmentLive : Fragment() {

    //var sharedpreferences: SharedPreferences? = null

    private var _binding : ActivityMainBinding? = null

    private  val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: (LayoutInflater), container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        _binding = ActivityMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    val logging = HttpLoggingInterceptor()
    val authorizationInterceptor = AuthorizationInterceptor()
    val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(authorizationInterceptor)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://dog.ceo/%22")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiServiceInterface = retrofit.create(ApiServiceInterface::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.insertIm.setOnClickListener{

        }
        coroutine()

        viewModel.dogApi()

    }

    fun coroutine() {

        lifecycleScope.launch {
            try {
                val details = apiService.getData()

                setDetails(details)


            } catch (e: Exception) {

                Log.d("MainActivity", "Error ${e.message}")
            }
        }
    }

    fun setDetails(data: RetrofitData) {

    }


}
