package com.example.trialonur.features.screen.secondscreen

import android.os.CountDownTimer
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.trialonur.data.DataState
import com.example.trialonur.data.model.SatelliteDetail
import com.example.trialonur.data.model.entity.SatelliteEntity
import com.example.trialonur.data.model.SatelliteList
import com.example.trialonur.domain.SatelliteRepository
import com.example.trialonur.features.BaseViewModel
import com.example.trialonur.features.viewstate.DetailViewState
import com.example.trialonur.features.viewstate.IViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondScreenViewModel @Inject constructor
    (savedStateHandle: SavedStateHandle, private val satelliteRepository: SatelliteRepository) :
    BaseViewModel<DetailViewState, IViewEvent>() {

    var index = 0
    var positionIndex = 0
    var name: String? = null

    init {
        savedStateHandle.get<String>("satelliteList")?.let {
            val response = SatelliteList.create(it)
            index = response?.id?.minus(1) ?: 0
            name = response?.name
            getDetail()
            getPosition()
        } ?: kotlin.run {
            println("error")
        }
    }

    fun getNewPosition() {
        val timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                if (positionIndex == 2) {
                    positionIndex = 0
                } else {
                    positionIndex += 1
                }
                getPosition()
            }
        }.start()
    }

    fun getDetail() {
        viewModelScope.launch {
            val localData = satelliteRepository.getSatelliteDao(index)
            val detail = localData?.id?.let {
                SatelliteDetail(
                    it,
                    localData.costPerLaunch,
                    localData.firstFlight,
                    localData.height,
                    localData.mass
                )
            }
            if (localData != null) {
                setState {
                    currentState.copy(
                        data = detail,
                        isLoading = false
                    )
                }
            } else {
                satelliteRepository.getSatelliteDetail().collect() {
                    when (it) {
                        is DataState.Success -> {
                            val entity =
                                SatelliteEntity(
                                    index,
                                    it.data.results.get(index).costPerLaunch,
                                    it.data.results.get(index).mass,
                                    it.data.results.get(index).firstFlight,
                                    it.data.results.get(index).height
                                )
                            setLocalDetail(entity)

                            setState {
                                currentState.copy(
                                    data = it.data.results.get(index),
                                    isLoading = false
                                )
                            }
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
    }

    fun getPosition() {
        viewModelScope.launch {
            satelliteRepository.getPosition().collect() {
                when (it) {
                    is DataState.Success -> {
                        setState {
                            currentState.copy(
                                position = it.data.results.get(index).positions.get(positionIndex),
                                isLoading = false
                            )
                        }
                        getNewPosition()
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

    fun getLocalData(index: Int): SatelliteEntity? {
        var data: SatelliteEntity? = null
        viewModelScope.launch {
            data = satelliteRepository.getSatelliteDao(index)
        }
        if (data != null) {
            return data
        } else {
            return null
        }
    }

    fun setLocalDetail(entity: SatelliteEntity) {
        viewModelScope.launch {
            satelliteRepository.saveSatelliteDao(entity)
        }
    }

    override fun createInitialState() = DetailViewState()

    override fun onTriggerEvent(event: IViewEvent) {}
}