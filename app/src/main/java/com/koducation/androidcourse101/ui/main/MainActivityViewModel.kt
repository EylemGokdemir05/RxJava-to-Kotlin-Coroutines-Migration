package com.koducation.androidcourse101.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.koducation.androidcourse101.data.local.FavoriteDataSource
import com.koducation.androidcourse101.data.remote.model.Radio
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    app: Application,
    val favoriteDataSource: FavoriteDataSource
) : AndroidViewModel(app) {

    private val bottomPlayerViewStateLiveData = MutableLiveData<BottomPlayerViewState>()

    private val currentSelectedRadioSubject = ConflatedBroadcastChannel<Radio>()

    init {
        combine(
            currentSelectedRadioSubject.asFlow(),
            favoriteDataSource.getFavoriteList(),
            ::produceBottomPlayerViewState
        )
            .onEach { bottomPlayerViewStateLiveData.value = it }
            .launchIn(viewModelScope)
    }

    fun getBottomPlayerViewStateLiveData(): LiveData<BottomPlayerViewState> =
        bottomPlayerViewStateLiveData

    fun setCurrentPlayingRadio(radio: Radio) {
        currentSelectedRadioSubject.offer(radio)
    }

    fun addToFavorite() {
        bottomPlayerViewStateLiveData.value?.let {
            viewModelScope.launch {
                favoriteDataSource.addToFavorite(it.radio)
            }
        }
    }

    fun removeFromFavorite() {
        bottomPlayerViewStateLiveData.value?.let {
            viewModelScope.launch {
                favoriteDataSource.removeFromFavorite(it.radio.id)
            }
        }
    }

    fun isFavorited(): Boolean {
        return bottomPlayerViewStateLiveData.value?.isFavorited ?: false
    }
}