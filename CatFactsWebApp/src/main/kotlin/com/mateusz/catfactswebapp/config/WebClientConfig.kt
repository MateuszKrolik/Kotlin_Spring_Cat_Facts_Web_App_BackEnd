package com.mateusz.catfactswebapp.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@EnableWebFlux
class WebClientConfig : WebFluxConfigurer {

    @Value("\${REMOTE_ALLOWED_ORIGIN}")
    private lateinit var remoteAllowedOrigin: String

    @Value("\${LOCAL_ALLOWED_ORIGIN}")
    private lateinit var localAllowedOrigin: String

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder().build()
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins(remoteAllowedOrigin, localAllowedOrigin)
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(false)
            .maxAge(3600)
    }
}