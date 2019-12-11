package com.koducation.androidcourse101.ui.radios

import com.koducation.androidcourse101.data.Resource
import com.koducation.androidcourse101.data.remote.model.Radio

suspend fun combineRadiosPage(
    popularRadios: Resource<List<Radio>>,
    locationRadios: Resource<List<Radio>>
): RadiosFragmentViewState {
    return RadiosFragmentViewState(popularRadios, locationRadios)
}