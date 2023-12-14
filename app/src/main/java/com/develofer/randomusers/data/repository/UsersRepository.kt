package com.develofer.randomusers.data.repository

import com.develofer.randomusers.domain.data.UserDomain
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun getUsers(page: Int): Flow<List<UserDomain>>

}