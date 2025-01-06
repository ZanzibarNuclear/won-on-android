package com.worldofnuclear.wonapp.ui

import androidx.lifecycle.ViewModel
import com.worldofnuclear.wonapp.model.FluxPost
import com.worldofnuclear.wonapp.model.FluxUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FluxViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FluxUiState())
    val uiState: StateFlow<FluxUiState> = _uiState.asStateFlow()

    fun loadFluxPosts(fluxPosts: List<FluxPost>) {
        _uiState.update { currentState ->
            currentState.copy(
                fluxes = fluxPosts
            )
        }
    }
}