package com.worldofnuclear.wonapp.model

import androidx.compose.runtime.mutableStateListOf

data class FluxUiState(
    val fluxes: List<FluxPost> = mutableStateListOf()
)
