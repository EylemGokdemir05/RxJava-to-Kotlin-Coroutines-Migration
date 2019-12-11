package com.koducation.androidcourse101.data.local

import com.koducation.androidcourse101.data.local.entity.FavoriteRadioEntity
import com.koducation.androidcourse101.data.remote.model.Radio
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteDataSource @Inject constructor(val favoriteRadioDao: FavoriteRadioDao) {

    fun getFavoriteList(): Flow<List<FavoriteRadioEntity>> {
        return favoriteRadioDao
            .getFavoriteRadios()
            .onStart { emit(arrayListOf()) }
    }

    suspend fun addToFavorite(radio: Radio) {
        val favoriteRadioEntity = FavoriteRadioEntity(
            radio.id,
            radio.band,
            radio.city,
            radio.country,
            radio.dial,
            radio.genres,
            radio.language,
            radio.listenerCount,
            radio.logo_big,
            radio.logo_small,
            radio.radioName,
            radio.spotUrl,
            radio.streams,
            radio.website
        )
        favoriteRadioDao.insertFavorite(favoriteRadioEntity)
    }

    suspend fun removeFromFavorite(radioId: Int) {
        return favoriteRadioDao.removeFavorite(radioId)
    }
}