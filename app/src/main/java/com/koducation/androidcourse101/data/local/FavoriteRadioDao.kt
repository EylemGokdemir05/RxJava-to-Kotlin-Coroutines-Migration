package com.koducation.androidcourse101.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.koducation.androidcourse101.data.local.entity.FavoriteRadioEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FavoriteRadioDao {

    @Query("SELECT * FROM favorite_radios")
    abstract fun getFavoriteRadios(): Flow<List<FavoriteRadioEntity>>

    @Insert
    abstract suspend fun insertFavorite(favoriteRadioEntity: FavoriteRadioEntity)

    @Query("DELETE FROM favorite_radios WHERE id=:radioId")
    abstract suspend fun removeFavorite(radioId: Int)

}