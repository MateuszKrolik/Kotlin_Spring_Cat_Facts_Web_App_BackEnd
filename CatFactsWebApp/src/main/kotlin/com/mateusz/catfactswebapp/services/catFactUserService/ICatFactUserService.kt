package com.mateusz.catfactswebapp.services.catFactUserService

import kotlinx.coroutines.flow.Flow

interface ICatFactUserService {
    fun getCatFactsWithUsers(): Flow<List<Map<String, String>>>
}