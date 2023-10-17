package com.aravind.kmmassignment.repository

import com.aravind.kmmassignment.api.BreedsApi
import com.aravind.kmmassignment.util.ApiDispatcherProvider
import kotlinx.coroutines.withContext

internal class DefaultBreedsRemoteSource(
    private val api: BreedsApi,
    private val dispatcherProvider: ApiDispatcherProvider
): BreedsRemoteSource {

    override suspend fun getBreeds() = withContext(dispatcherProvider.io) {
        api.getBreeds().breeds
    }

    override suspend fun getBreedImage(breed: String) = withContext(dispatcherProvider.io) {
        api.getRandomBreedImageFor(breed).breedImageUrl
    }
}