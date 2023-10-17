package com.aravind.kmmassignment.repository

interface BreedsRemoteSource {

    suspend fun getBreeds(): List<String>

    suspend fun getBreedImage(breed: String): String
}