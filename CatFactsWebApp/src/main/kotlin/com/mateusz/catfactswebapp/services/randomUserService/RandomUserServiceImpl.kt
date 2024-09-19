package com.mateusz.catfactswebapp.services.randomUserService

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.mateusz.catfactswebapp.dto.RandomUserResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class RandomUserServiceImpl(private val webClient: WebClient, @Value("\${randomuser.api.url}") private val randomUserApiUrl: String) : IRandomUserService {

    private val objectMapper = jacksonObjectMapper()

    override fun getRandomUsers(): Flow<String> = flow {
        while (true) { // infinite loop
            val response = webClient.get()
                .uri(randomUserApiUrl)
                .retrieve()
                .bodyToMono(String::class.java)
                .awaitSingle()
            val userResponse: RandomUserResponse = objectMapper.readValue(response)
            val userName = userResponse.results.firstOrNull()?.name?.let { name ->
                "${name.first} ${name.last}"
            }
            emit(userName ?: "Unknown User")
            delay(10000)
        }
    }
}