package com.example.trialonur.features.screen.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.trialonur.data.DataState
import com.example.trialonur.data.model.States
import com.example.trialonur.data.repository.FlightRepository
import com.example.trialonur.domain.mapper.FlightStatesResultMapper
import com.example.trialonur.features.BaseViewModel
import com.example.trialonur.features.viewstate.IViewEvent
import com.example.trialonur.features.viewstate.ObjectViewState
import com.example.trialonur.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val flightRepository: FlightRepository) :
    BaseViewModel<ObjectViewState, IViewEvent>() {

    val country = arrayListOf<String>()
    val states = mutableListOf<States>()

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            flightRepository.getFlights(Constants.lamin,Constants.lomin,Constants.lamax,Constants.lomax).collect() {
                when (it) {
                    is DataState.Success -> {
                        it.data.states?.let {
                            FlightStatesResultMapper.responseToFlightStatesResult(it).forEach{
                                states.add(it)
                            }
                        }
                        setState { currentState.copy(data = states, isLoading = false) }
                        it.data.states?.forEach {
                                if(country.contains(it.get(2)).not()){
                                    country.add(it.get(2))
                            }
                        }
                    }
                    is DataState.Error -> {
                        setState { currentState.copy(isLoading = false) }
                        Log.d("ApiError",it.apiError?.message.toString())
                    }
                    is DataState.Loading -> {
                        setState { currentState.copy(isLoading = true) }

                    }
                }
            }
        }
    }

    fun selectCountry(value: String) {
        setState { currentState.copy(data = states.filter { it.origin_country == value }) }
    }

    override fun createInitialState() = ObjectViewState()

    override fun onTriggerEvent(event: IViewEvent) {

    }

}