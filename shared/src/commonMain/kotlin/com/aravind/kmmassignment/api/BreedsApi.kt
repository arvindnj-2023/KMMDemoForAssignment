package com.aravind.kmmassignment.api

import com.aravind.kmmassignment.api.model.BreedImageResponse
import com.aravind.kmmassignment.api.model.BreedsResponse
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlin.collections.get

/**
 * Ktor Networking Api for getting information about a Breed entity
 */
internal class BreedsApi : KtorApi() {

    suspend fun getBreeds(): BreedsResponse = client.get {
        apiUrl("breeds/list")
    }.body()

    suspend fun getRandomBreedImageFor(breed: String): BreedImageResponse = client.get {
        apiUrl("breed/$breed/images/random")
    }.body()
}