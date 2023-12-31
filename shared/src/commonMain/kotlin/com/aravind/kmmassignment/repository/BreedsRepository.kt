package com.aravind.kmmassignment.repository

import com.aravind.kmmassignment.model.Breed
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class BreedsRepository: KoinComponent {

    private val remoteSource: BreedsRemoteSource = get(null)
    private val localSource: BreedsLocalSource = get(null)

    val breeds = localSource.breeds

    internal suspend fun get() = with(localSource.selectAll()) {
        if (isNullOrEmpty()) {
            return@with fetch()
        } else {
            this
        }
    }

    internal suspend fun fetch() = supervisorScope {
        remoteSource.getBreeds().map {
            async { Breed(name = it, imageUrl = remoteSource.getBreedImage(it)) }
        }.awaitAll().also {
            localSource.clear()
            it.map { async { localSource.insert(it) } }.awaitAll()
        }
    }

    internal suspend fun update(breed: Breed) = localSource.update(breed)
}