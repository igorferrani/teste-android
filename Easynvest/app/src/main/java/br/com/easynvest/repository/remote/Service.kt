package br.com.easynvest.repository.remote

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service {
    companion object {
        private const val BASE_URL = "https://api-simulator-calc.easynvest.com.br/"
        val service: SimulationApi

        init {
            val gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            service = retrofit.create(SimulationApi::class.java)
        }
    }
}
