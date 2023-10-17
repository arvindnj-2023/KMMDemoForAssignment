package com.aravind.kmmassignment.usecase

import com.aravind.kmmassignment.model.Breed
import com.aravind.kmmassignment.repository.BreedsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class FetchBreedsUseCase : KoinComponent {

    private val breedsRepository: BreedsRepository = get()

    suspend operator fun invoke(): List<Breed> = breedsRepository.fetch()
}