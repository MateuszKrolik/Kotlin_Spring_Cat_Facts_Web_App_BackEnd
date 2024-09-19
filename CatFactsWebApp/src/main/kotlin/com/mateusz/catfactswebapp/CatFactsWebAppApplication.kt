package com.mateusz.catfactswebapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CatFactsWebAppApplication

fun main(args: Array<String>) {
	runApplication<CatFactsWebAppApplication>(*args)
}
