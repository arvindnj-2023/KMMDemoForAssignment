package com.aravind.kmmassignment.repository

import com.aravind.kmmassignment.db.KmmDemoDatabase
import com.aravind.kmmassignment.model.Breed
import com.aravind.kmmassignment.util.ApiDispatcherProvider
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class DefaultBreedsLocalSource(
    database: KmmDemoDatabase,
    private val dispatcherProvider: ApiDispatcherProvider
): BreedsLocalSource {
    private val dao = database.breedsQueries

    override val breeds = dao.selectAll().asFlow().mapToList()
        .map { breeds -> breeds.map { Breed(it.name, it.imageUrl) } }

    override suspend fun selectAll() = withContext(dispatcherProvider.io) {
        dao.selectAll { name, imageUrl -> Breed(name, imageUrl) }
            .executeAsList()
    }

    override suspend fun insert(breed: Breed) = withContext(dispatcherProvider.io) {
        dao.insert(breed.name, breed.imageUrl)
    }

    override suspend fun update(breed: Breed) = withContext(dispatcherProvider.io) {
        dao.update(breed.imageUrl, breed.name)
    }

    override suspend fun clear() = withContext(dispatcherProvider.io) {
        dao.clear()
    }
}