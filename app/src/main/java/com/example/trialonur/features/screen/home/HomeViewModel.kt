package com.example.trialonur.features.screen.home

import androidx.lifecycle.viewModelScope
import com.example.trialonur.data.DataState
import com.example.trialonur.domain.SatelliteRepository
import com.example.trialonur.features.BaseViewModel
import com.example.trialonur.features.viewstate.IViewEvent
import com.example.trialonur.features.viewstate.ObjectViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val satelliteRepository: SatelliteRepository) :
    BaseViewModel<ObjectViewState, IViewEvent>() {

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            satelliteRepository.getSatelliteList().collect() {
                when (it) {
                    is DataState.Success -> {
                        delay(2000)
                        setState { currentState.copy(data = it.data, isLoading = false) }
                    }
                    is DataState.Error -> {
                        setState { currentState.copy(isLoading = false) }
                        val error = it.apiError?.message
                    }
                    is DataState.Loading -> {
                        setState { currentState.copy(isLoading = true) }

                    }
                }
            }
        }
    }

    override fun createInitialState() = ObjectViewState()

    override fun onTriggerEvent(event: IViewEvent) {

    }

}