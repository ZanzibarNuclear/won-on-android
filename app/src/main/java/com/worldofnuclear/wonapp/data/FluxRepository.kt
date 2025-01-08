package com.worldofnuclear.wonapp.data

import com.worldofnuclear.wonapp.model.FluxPost
import com.worldofnuclear.wonapp.model.FluxPostResponse
import com.worldofnuclear.wonapp.network.FluxApiService

interface FluxRepository {
    suspend fun getFluxes(): FluxPostResponse
    suspend fun getMoreFluxes(limit: Int, offset: Int, order: String): FluxPostResponse
    suspend fun boostFlux(fluxId: Int): FluxPost
    suspend fun deboostFlux(fluxId: Int): FluxPost
}

class DefaultFluxRepository (
    private val fluxApiService: FluxApiService
) : FluxRepository {
    override suspend fun getFluxes(): FluxPostResponse = fluxApiService.getFluxes()

    override suspend fun getMoreFluxes(limit: Int, offset: Int, order: String): FluxPostResponse =
        fluxApiService.getMoreFluxes(limit, offset, order)

    override suspend fun boostFlux(fluxId: Int): FluxPost = fluxApiService.boostFlux(fluxId)

    override suspend fun deboostFlux(fluxId: Int): FluxPost = fluxApiService.deboostFlux(fluxId)
}
