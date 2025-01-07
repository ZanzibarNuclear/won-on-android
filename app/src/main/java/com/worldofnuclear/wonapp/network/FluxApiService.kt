package com.worldofnuclear.wonapp.network

import com.worldofnuclear.wonapp.model.FluxPostResponse
import retrofit2.http.GET

interface FluxApiService {
    @GET("fluxes")
    suspend fun getFluxes(): FluxPostResponse
}