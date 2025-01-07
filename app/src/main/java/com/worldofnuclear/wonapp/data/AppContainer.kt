package com.worldofnuclear.wonapp.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.worldofnuclear.wonapp.network.FluxApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val fluxRepository: FluxRepository
}

class DefaultAppContainer : AppContainer {
    private val BASE_URL = "https://api.worldofnuclear.com/api/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: FluxApiService by lazy {
        retrofit.create(FluxApiService::class.java)
    }

    override val fluxRepository: FluxRepository by lazy {
        DefaultFluxRepository(retrofitService)
    }
}