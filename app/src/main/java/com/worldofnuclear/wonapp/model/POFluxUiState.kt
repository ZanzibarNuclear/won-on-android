package com.worldofnuclear.wonapp.model

import androidx.compose.runtime.mutableStateListOf

data class POFluxUiState(
    val fluxes: List<FluxPost> = mutableStateListOf()
)
