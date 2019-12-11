package com.koducation.androidcourse101.data.remote

import com.koducation.androidcourse101.data.Resource
import com.koducation.androidcourse101.data.remote.model.Radio
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RadioDataSource @Inject constructor(val radioServiceProvider: RadioServiceProvider) {

    fun fetchPopularRadios(): Flow<Resource<List<Radio>>> {
        return flow {
            emit(Resource.loading())

            try {
                val popularRadios = radioServiceProvider.radioService.popularRadios()
                emit(Resource.success(popularRadios))
            } catch (exception: Exception) {
                emit(Resource.error(exception.message ?: "Error"))
            }
        }
    }

    fun fetchLocationRadios(): Flow<Resource<List<Radio>>> {
        return flow {
            emit(Resource.loading())

            try {
                val locationRadios = radioServiceProvider.radioService.locationRadios()
                emit(Resource.success(locationRadios))
            } catch (exception: Exception) {
                emit(Resource.error(exception.message ?: "Error"))
            }
        }
    }
}