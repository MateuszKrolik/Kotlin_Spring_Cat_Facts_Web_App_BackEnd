package com.mateusz.catfactswebapp.services.catFactService

import kotlinx.coroutines.flow.Flow

interface ICatFactService {
    fun getCatFacts(): Flow<String>
}