package com.koducation.androidcourse101.ui.radios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koducation.androidcourse101.data.remote.RadioDataSource
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RadiosFragmentViewModel @Inject constructor(val radioDataSource: RadioDataSource) :
    ViewModel() {

    private val radiosLiveData: MutableLiveData<RadiosFragmentViewState> = MutableLiveData()

    init {
        loadRadiosPage()
    }

    fun getRadiosLiveData(): LiveData<RadiosFragmentViewState> = radiosLiveData

    fun loadRadiosPage() {
        combine(
            radioDataSource.fetchPopularRadios(),
            radioDataSource.fetchLocationRadios(),
            ::combineRadiosPage
        )
            .onEach { radiosLiveData.value = it }
            .launchIn(viewModelScope)
    }
}