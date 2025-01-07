package com.worldofnuclear.wonapp.data

import com.worldofnuclear.wonapp.model.FluxPostResponse
import com.worldofnuclear.wonapp.network.FluxApiService

interface FluxRepository {
    suspend fun getFluxes(): FluxPostResponse
}

class DefaultFluxRepository (
    private val fluxApiService: FluxApiService
) : FluxRepository {
    override suspend fun getFluxes(): FluxPostResponse = fluxApiService.getFluxes()
}
