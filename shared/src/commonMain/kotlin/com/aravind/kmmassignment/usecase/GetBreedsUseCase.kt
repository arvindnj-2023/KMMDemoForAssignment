package com.aravind.kmmassignment.usecase

import com.aravind.kmmassignment.api.BreedsApi
import com.aravind.kmmassignment.model.Breed
import com.aravind.kmmassignment.repository.BreedsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import kotlin.native.concurrent.SharedImmutable
import kotlin.native.concurrent.ThreadLocal

class GetBreedsUseCase : KoinComponent {

    private val breedsRepository: BreedsRepository = get()

    suspend operator fun invoke(): List<Breed> = breedsRepository.get()
}