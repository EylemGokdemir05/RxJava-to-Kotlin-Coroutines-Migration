package com.koducation.androidcourse101.ui.main

import com.koducation.androidcourse101.data.local.entity.FavoriteRadioEntity
import com.koducation.androidcourse101.data.remote.model.Radio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun produceBottomPlayerViewState(
    selectedRadio: Radio,
    favoriteList: List<FavoriteRadioEntity>
): BottomPlayerViewState = withContext(Dispatchers.Default) {
    favoriteList.forEach {
        if (it.id == selectedRadio.id) {
            return@withContext BottomPlayerViewState(selectedRadio, true)
        }
    }

    return@withContext BottomPlayerViewState(selectedRadio, false)
}