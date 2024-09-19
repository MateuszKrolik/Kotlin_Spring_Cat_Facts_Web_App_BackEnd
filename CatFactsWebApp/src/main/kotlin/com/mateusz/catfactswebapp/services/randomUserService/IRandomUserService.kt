package com.mateusz.catfactswebapp.services.randomUserService

import kotlinx.coroutines.flow.Flow

interface IRandomUserService {
    fun getRandomUsers(): Flow<String>
}