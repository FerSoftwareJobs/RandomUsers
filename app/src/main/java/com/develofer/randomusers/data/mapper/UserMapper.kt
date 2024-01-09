package com.develofer.randomusers.data.mapper

import com.develofer.randomusers.data.source.remote.response.UserCoordinatesDTO
import com.develofer.randomusers.data.source.remote.response.UserDTO
import com.develofer.randomusers.data.source.remote.response.UserLocationDTO
import com.develofer.randomusers.data.source.remote.response.UserLoginDTO
import com.develofer.randomusers.data.source.remote.response.UserNameDTO
import com.develofer.randomusers.data.source.remote.response.UserProfilePictureDTO
import com.develofer.randomusers.data.source.remote.response.UserRegisteredDTO
import com.develofer.randomusers.domain.data.UserCoordinatesDomain
import com.develofer.randomusers.domain.data.UserDomain
import com.develofer.randomusers.domain.data.UserLocationDomain
import com.develofer.randomusers.domain.data.UserLoginDomain
import com.develofer.randomusers.domain.data.UserNameDomain
import com.develofer.randomusers.domain.data.UserProfilePictureDomain
import com.develofer.randomusers.domain.data.UserRegisteredDomain

fun UserDTO.toDomain(): UserDomain = UserDomain(
    gender = parseGender(gender.orEmpty()),
    name = name.toDomain(),
    location = location.toDomain(),
    email = email.orEmpty(),
    login = login.toDomain(),
    registered = registered.toDomain(),
    phone = phone.orEmpty(),
    picture = picture.toDomain(),
)

fun UserNameDTO.toDomain(): UserNameDomain = UserNameDomain(
    title = title.orEmpty(),
    first = first.orEmpty(),
    last = last.orEmpty()
)

fun UserLocationDTO.toDomain(): UserLocationDomain = UserLocationDomain(
    coordinates = coordinates.toDomain()
)

fun UserCoordinatesDTO.toDomain(): UserCoordinatesDomain = UserCoordinatesDomain(
    latitude = latitude.orEmpty(),
    longitude = longitude.orEmpty()
)

fun UserLoginDTO.toDomain(): UserLoginDomain = UserLoginDomain(
    uuid = uuid.orEmpty()
)

fun UserRegisteredDTO.toDomain(): UserRegisteredDomain = UserRegisteredDomain(
    date = date.orEmpty()
)

fun UserProfilePictureDTO.toDomain(): UserProfilePictureDomain = UserProfilePictureDomain(
    large = large.orEmpty(),
    medium = medium.orEmpty(),
    thumbnail = thumbnail.orEmpty()
)

fun parseGender(apiGender: String): String {
    return when (apiGender) {
        "male" -> "Hombre"
        "female" -> "Mujer"
        else -> apiGender
    }
}