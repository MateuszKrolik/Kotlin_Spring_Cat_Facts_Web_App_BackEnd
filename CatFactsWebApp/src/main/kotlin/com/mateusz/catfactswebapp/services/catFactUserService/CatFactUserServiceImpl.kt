package com.mateusz.catfactswebapp.services.catFactUserService

import com.mateusz.catfactswebapp.services.catFactService.ICatFactService
import com.mateusz.catfactswebapp.services.randomUserService.IRandomUserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.zip
import org.springframework.stereotype.Service

@Service
class CatFactUserServiceImpl(
    private val catFactService: ICatFactService,
    private val randomUserService: IRandomUserService
) : ICatFactUserService {
    override fun getCatFactsWithUsers(): Flow<List<Map<String, String>>> = flow {
        val results = mutableListOf<Map<String, String>>()
        val catFacts = catFactService.getCatFacts()
        val randomUsers = randomUserService.getRandomUsers()
        catFacts.zip(randomUsers) { fact, user ->
            mapOf("user" to user, "fact" to fact)
        }.collect { combined ->
            results.add(combined)
            emit(results.toList())
        }
    }
}