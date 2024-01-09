package com.develofer.randomusers.data.repository

import com.develofer.randomusers.data.mapper.toDomain
import com.develofer.randomusers.data.source.remote.api.UsersApi
import com.develofer.randomusers.domain.data.UserDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersApi: UsersApi
) : UsersRepository {

    private var seed: String? = null
    override suspend fun getUsers(page: Int): Flow<List<UserDomain>> = flow {
        val response = usersApi.getUsers(page = page, seed = seed)
        if (response.isSuccessful && response.body() != null) {
            response.body()?.let { userResponse ->
                val usersDomain = userResponse.results.map { it.toDomain() }
                seed = userResponse.info.seed
                emit(usersDomain)
            }
        }
    }.catch {
        it.printStackTrace()
    }.flowOn(Dispatchers.IO)

}