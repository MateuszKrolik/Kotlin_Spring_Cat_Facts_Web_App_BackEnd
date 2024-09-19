package com.mateusz.catfactswebapp.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class RandomUserResponse(
    val results: List<User>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class User(
    val name: Name
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Name(
    val first: String,
    val last: String
)