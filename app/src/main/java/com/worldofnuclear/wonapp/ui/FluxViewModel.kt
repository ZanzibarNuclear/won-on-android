package com.worldofnuclear.wonapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.worldofnuclear.wonapp.WonApplication
import com.worldofnuclear.wonapp.data.FluxRepository
import com.worldofnuclear.wonapp.model.FluxPost
import com.worldofnuclear.wonapp.model.FluxPostResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface FluxUiState {
    data class Success(val fluxes: FluxPostResponse) : FluxUiState
    object Error : FluxUiState
    object Loading : FluxUiState
}

class FluxViewModel(private val fluxRepository: FluxRepository) : ViewModel() {
    var fluxUiState: FluxUiState by mutableStateOf(FluxUiState.Loading)
        private set

    init {
        fetchFluxPosts()
        // TODO - stash loaded fluxes in Room (local database) and point UI to that??
    }

    fun fetchFluxPosts() {
        viewModelScope.launch {
            fluxUiState = FluxUiState.Loading
            fluxUiState = try {
                FluxUiState.Success(fluxRepository.getFluxes())
            } catch (e: IOException) {
                FluxUiState.Error
            } catch (e: HttpException) {
                FluxUiState.Error
            }
        }
    }

    // TODO: get user logged in somehow...

    fun boostPost(post: FluxPost) {
        viewModelScope.launch {
            val boostedFlux = fluxRepository.boostFlux(post.id)
            // TODO: put updated flux post where user can see it
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as WonApplication)
                val fluxRepository = application.container.fluxRepository
                FluxViewModel(fluxRepository = fluxRepository)
            }
        }
    }
}