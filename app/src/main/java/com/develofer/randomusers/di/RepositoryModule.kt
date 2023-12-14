package com.develofer.randomusers.di

import com.develofer.randomusers.data.repository.UsersRepository
import com.develofer.randomusers.data.repository.UsersRepositoryImpl
import com.develofer.randomusers.data.source.remote.api.UsersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideUsersRepository(
        usersApi: UsersApi
    ): UsersRepository =
        UsersRepositoryImpl(usersApi)

}