package com.koducation.androidcourse101.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.koducation.androidcourse101.data.local.FavoriteDataSource
import com.koducation.androidcourse101.data.local.entity.FavoriteRadioEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteFragmentViewModel @Inject constructor(
    val app: Application,
    val favoriteDataSource: FavoriteDataSource
) : AndroidViewModel(app) {

    private val favoriteViewStateListLiveData = MutableLiveData<List<FavoriteRadioItemViewState>>()

    init {
        favoriteDataSource
            .getFavoriteList()
            .map { mapToViewState(it) }
            .onEach { favoriteViewStateListLiveData.value = it }
            .launchIn(viewModelScope)
    }

    fun getFavoriteRadiosLiveData(): LiveData<List<FavoriteRadioItemViewState>> =
        favoriteViewStateListLiveData

    fun removeFromFavorites(radioId: Int) {
        viewModelScope.launch {
            favoriteDataSource.removeFromFavorite(radioId)
        }
    }

    private suspend fun mapToViewState(entityList: List<FavoriteRadioEntity>): List<FavoriteRadioItemViewState> =
        withContext(Dispatchers.Default) {
            val viewStateList = arrayListOf<FavoriteRadioItemViewState>()
            entityList.forEach { viewStateList.add(FavoriteRadioItemViewState(it)) }
            return@withContext viewStateList
        }
}