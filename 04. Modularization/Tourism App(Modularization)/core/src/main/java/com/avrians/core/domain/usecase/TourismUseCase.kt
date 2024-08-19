package com.avrians.core.domain.usecase

import androidx.lifecycle.LiveData
import com.avrians.core.data.Resource
import com.avrians.core.domain.model.Tourism
import kotlinx.coroutines.flow.Flow

interface TourismUseCase {
    fun getAllTourism(): Flow<com.avrians.core.data.Resource<List<Tourism>>>
    fun getFavoriteTourism(): Flow<List<Tourism>>
    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}