package com.develofer.randomusers.domain.usecase

import com.develofer.randomusers.data.repository.UsersRepository
import com.develofer.randomusers.domain.data.UserDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetUsersUseCase {
    suspend operator fun invoke(page: Int): Flow<List<UserDomain>>
}

class GetUsersUseCaseImpl @Inject constructor(
    private val repository: UsersRepository
): GetUsersUseCase {
     override suspend operator fun invoke(page: Int): Flow<List<UserDomain>> =
         repository.getUsers(page)
}