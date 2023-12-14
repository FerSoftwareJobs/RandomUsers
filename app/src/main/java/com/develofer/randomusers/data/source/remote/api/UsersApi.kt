package com.develofer.randomusers.data.source.remote.api

import com.develofer.randomusers.data.source.remote.response.UserResponseDTO
import com.develofer.randomusers.utils.Constants.USERS_COUNT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApi {
    @GET("api/")
    suspend fun getUsers(
        @Query("results") count: Int = USERS_COUNT,
        @Query("page") page: Int,
        @Query("seed") seed: String? = null
    ): Response<UserResponseDTO>
}