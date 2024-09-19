package com.mateusz.catfactswebapp.controllers

import com.mateusz.catfactswebapp.services.catFactUserService.ICatFactUserService
import kotlinx.coroutines.flow.Flow
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CatFactController(private val catFactUserService: ICatFactUserService) {
    @GetMapping("/cat-facts", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun getCatFacts(): Flow<List<Map<String, String>>> = catFactUserService.getCatFactsWithUsers()
}