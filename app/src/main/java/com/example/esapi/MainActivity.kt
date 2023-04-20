package com.example.esapi

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.esapi.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Boolean.getBoolean
import java.nio.file.Paths.get

 class MainActivity : AppCompatActivity() {


     lateinit var _binding1: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding1 = ActivityMainBinding.inflate(layoutInflater)

        loadData()

        _binding1.button1.setOnClickListener {
            saveData()
        }
        setContentView(_binding1.root)
    }

    private fun saveData() {
        val inputCamp = _binding1.editText1.text.toString()
        _binding1.insertIm2.text = inputCamp

        val sharedPreferences : SharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply {
            putString("KEY_STRING", inputCamp)
            putBoolean("Bool_Key", _binding1.switch1.isChecked)
        }.apply()

        Toast.makeText(this,"Data Saved", Toast.LENGTH_SHORT).show()

    }

    private fun loadData(){
        val sharedPreferences : SharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val savedString : String? = sharedPreferences.getString("KEY_STRING", null)
        val savedBool = sharedPreferences.getBoolean("Bool_Key", false)

      _binding1.insertIm2.text = savedString
      _binding1.switch1.isChecked = savedBool
    }
}





