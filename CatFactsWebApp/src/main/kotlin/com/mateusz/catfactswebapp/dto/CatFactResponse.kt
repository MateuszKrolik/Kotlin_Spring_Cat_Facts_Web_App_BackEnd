package com.mateusz.catfactswebapp.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CatFactResponse(
    val text: String
)