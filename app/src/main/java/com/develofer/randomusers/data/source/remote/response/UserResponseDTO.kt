package com.develofer.randomusers.data.source.remote.response

data class UserResponseDTO(
    val info: InfoDTO,
    val results: List<UserDTO>
)

data class InfoDTO(
    val page: Int?,
    val results: Int?,
    val seed: String?,
    val version: String?
)
