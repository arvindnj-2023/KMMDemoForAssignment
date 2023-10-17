package com.aravind.kmmassignment.di

import com.aravind.kmmassignment.api.BreedsApi
import com.aravind.kmmassignment.database.createDriver
import com.aravind.kmmassignment.db.KmmDemoDatabase
import com.aravind.kmmassignment.repository.*
import com.aravind.kmmassignment.repository.DefaultBreedsLocalSource
import com.aravind.kmmassignment.repository.DefaultBreedsRemoteSource
import com.aravind.kmmassignment.usecase.FetchBreedsUseCase
import com.aravind.kmmassignment.usecase.GetBreedsUseCase
import com.aravind.kmmassignment.util.getDispatcherProvider
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private val utilityModule = module {
    factory { getDispatcherProvider() }
    single { KmmDemoDatabase(createDriver("KmmDemo.db")) }
}

private val apiModule = module {
    factory { BreedsApi() }
}

private val repositoryModule = module {
    single { BreedsRepository() }

    factory<BreedsRemoteSource> { DefaultBreedsRemoteSource(get(), get()) }
    factory<BreedsLocalSource> { DefaultBreedsLocalSource(get(), get()) }
}

private val usecaseModule = module {
    factory { GetBreedsUseCase() }
    factory { FetchBreedsUseCase() }
    //factory { ToggleFavouriteStateUseCase() }
}

private val sharedModules = listOf(usecaseModule, repositoryModule, apiModule, utilityModule)

fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    modules(sharedModules)
    appDeclaration()
}

fun initKoin() = initKoin { }