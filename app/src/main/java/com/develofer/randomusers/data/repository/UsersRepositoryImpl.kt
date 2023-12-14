package com.develofer.randomusers.data.repository

import com.develofer.randomusers.data.mapper.toDomain
import com.develofer.randomusers.data.source.remote.api.UsersApi
import com.develofer.randomusers.domain.data.UserDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val usersApi: UsersApi
) : UsersRepository {

    private var seed: String? = "af11c47597b58cd4"

    override suspend fun getUsers(page: Int): Flow<List<UserDomain>> = flow {
        if (seed == null) {
            val response = usersApi.getUsers(page = page)
            if (response.isSuccessful && response.body() != null) {
                response.body()?.let {userResponse ->
                    seed = userResponse.info.seed
                    emit(userResponse.results.map {
                        it.toDomain()
                    })
                }
            }
        } else {
            seed?.let { seed ->
                val response = usersApi.getUsers(page = page, seed = seed)
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {userResponse ->
                        emit(userResponse.results.map { userDTO ->
                            userDTO.toDomain()
                        })
                    }
                }
            }
        }
    }

}