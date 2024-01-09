package com.develofer.randomusers.di

import com.develofer.randomusers.data.repository.UsersRepository
import com.develofer.randomusers.domain.usecase.GetUsersUseCase
import com.develofer.randomusers.domain.usecase.GetUsersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetUsersUseCase(repository: UsersRepository): GetUsersUseCase =
        GetUsersUseCaseImpl(repository)

}