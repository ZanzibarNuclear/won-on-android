package com.worldofnuclear.wonapp.data

import com.worldofnuclear.wonapp.model.FluxPost
import com.worldofnuclear.wonapp.network.FluxApiService

interface FluxRepository {
    suspend fun getFluxes(): List<FluxPost>
}

class DefaultFluxRepository (
    private val fluxApiService: FluxApiService
) : FluxRepository {
    override suspend fun getFluxes(): List<FluxPost> = fluxApiService.getFluxes()
}
