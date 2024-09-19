package com.mateusz.catfactswebapp.services.catFactService

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.mateusz.catfactswebapp.dto.CatFactResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class CatFactServiceImpl(private val webClient: WebClient,@Value("\${catfact.api.url}") private val catFactApiUrl: String) : ICatFactService {

    private val objectMapper = jacksonObjectMapper()

    override fun getCatFacts(): Flow<String> = flow {
        while (true) {
            val response = webClient.get()
                .uri(catFactApiUrl)
                .retrieve()
                .bodyToMono(String::class.java)
                .awaitSingle()
            val factResponse: CatFactResponse = objectMapper.readValue(response)
            emit(factResponse.text)
            delay(10000)
        }
    }
}