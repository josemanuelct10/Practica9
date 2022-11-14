package com.example.practica9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listapelisconGET.adapter.FilmAdapter
import com.example.listapelisconGET.databinding.ActivityMainBinding
import com.example.practica9.APIService
import com.example.practica9.FilmResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.listaPeliculas.layoutManager = LinearLayoutManager(this)
        val filmAdapter = FilmAdapter(emptyList())
        binding.listaPeliculas.adapter = filmAdapter

        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<FilmResponse> = getRetrofit().create(APIService::class.java).getPeliculas("movie/popular?api_key=643f9adb342afaf6d078746c9ae515d9")
            val peli = call.body()
            runOnUiThread {
                if (call.isSuccessful) {

                    val resultado = peli?.results ?: emptyList()
                    filmAdapter.filmList = resultado
                    filmAdapter.notifyDataSetChanged()
                    Toast
                        .makeText(this@MainActivity, filmAdapter.filmList.first().title, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast
                        .makeText(this@MainActivity, "Ha ocurrido un error", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory((GsonConverterFactory.create()))
            .build()
    }
}
