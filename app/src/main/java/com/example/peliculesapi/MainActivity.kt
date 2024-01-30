package com.example.peliculesapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.peliculesapi.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSig.setOnClickListener {
            getTitles("/titles?page=2")
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://moviesdatabase.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
    }

    private fun getClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()

        return client
    }

    private fun getTitles(query: String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getTitle("$query")
            val peli : PeliResponse? = call.body()
            runOnUiThread {
                if(call.isSuccessful){
                    val imagen: String? = peli?.resultados?.get(5)?.urlImage.toString()
                    val titulo: String? = peli?.resultados?.get(5)?.title.toString()
                    showImage(imagen, titulo)


                }else{
                    showError()
                }
            }


        }
    }
    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error.", Toast.LENGTH_SHORT).show()
    }

    private fun showImage(image: String?, titulo: String?){
        Picasso.get().load(image).into(binding.imageView)
        binding.tvTitulo.text = titulo

    }
}