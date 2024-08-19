package com.avrians.core.domain.repository

import androidx.lifecycle.LiveData
import com.avrians.core.data.Resource
import com.avrians.core.domain.model.Tourism
import kotlinx.coroutines.flow.Flow

interface ITourismRepository {

    fun getAllTourism(): Flow<com.avrians.core.data.Resource<List<Tourism>>>

    fun getFavoriteTourism(): Flow<List<Tourism>>

    fun setFavoriteTourism(tourism: Tourism, state: Boolean)

}
