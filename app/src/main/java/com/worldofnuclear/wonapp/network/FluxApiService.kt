package com.worldofnuclear.wonapp.network

import com.worldofnuclear.wonapp.model.FluxPost
import retrofit2.http.GET

interface FluxApiService {
    @GET("fluxes")
    suspend fun getFluxes(): List<FluxPost>
}