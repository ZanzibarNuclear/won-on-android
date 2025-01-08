package com.worldofnuclear.wonapp.network

import com.worldofnuclear.wonapp.model.FluxPost
import com.worldofnuclear.wonapp.model.FluxPostResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FluxApiService {
    @GET("fluxes")
    suspend fun getFluxes(): FluxPostResponse

    @GET("fluxes?limit={limit}&offset={offset}&order={order}")
    suspend fun getMoreFluxes(@Path("limit") limit: Int, @Path("offset") offset: Int,
                              @Path("order") order: String): FluxPostResponse

//    @GET("fluxes?fluxId={fluxId}")
//    suspend fun getFluxReplies(@Path("fluxId") fluxId: Int): FluxPostResponse
//
//    @GET("fluxes/{id}")
//    suspend fun getFluxPost(@Path("id") postId: Int): FluxPost
//
//    // TODO: implement user auth, passing JIT in header
//
//    @POST("fluxes")
//    suspend fun postFlux(post: FluxPost): FluxPost
//
//    @POST("fluxes/{id?}/view")
//    suspend fun viewFlux(fluxId: Int): FluxPost

    @POST("fluxes/{id?}/boost")
    suspend fun boostFlux(@Path("id") fluxId: Int): FluxPost

    @DELETE("fluxes/{id?}/boost")
    suspend fun deboostFlux(@Path("id") fluxId: Int): FluxPost
}