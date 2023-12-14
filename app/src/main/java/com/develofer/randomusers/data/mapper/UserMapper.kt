package com.develofer.randomusers.data.mapper

import com.develofer.randomusers.data.source.remote.response.UserCoordinatesDTO
import com.develofer.randomusers.data.source.remote.response.UserDTO
import com.develofer.randomusers.data.source.remote.response.UserDobDTO
import com.develofer.randomusers.data.source.remote.response.UserIdDTO
import com.develofer.randomusers.data.source.remote.response.UserLocationDTO
import com.develofer.randomusers.data.source.remote.response.UserLoginDTO
import com.develofer.randomusers.data.source.remote.response.UserNameDTO
import com.develofer.randomusers.data.source.remote.response.UserProfilePictureDTO
import com.develofer.randomusers.data.source.remote.response.UserRegisteredDTO
import com.develofer.randomusers.data.source.remote.response.UserStreetDTO
import com.develofer.randomusers.data.source.remote.response.UserTimezoneDTO
import com.develofer.randomusers.domain.data.UserCoordinatesDomain
import com.develofer.randomusers.domain.data.UserDobDomain
import com.develofer.randomusers.domain.data.UserDomain
import com.develofer.randomusers.domain.data.UserIdDomain
import com.develofer.randomusers.domain.data.UserLocationDomain
import com.develofer.randomusers.domain.data.UserLoginDomain
import com.develofer.randomusers.domain.data.UserNameDomain
import com.develofer.randomusers.domain.data.UserProfilePictureDomain
import com.develofer.randomusers.domain.data.UserRegisteredDomain
import com.develofer.randomusers.domain.data.UserStreetDomain
import com.develofer.randomusers.domain.data.UserTimezoneDomain
import com.develofer.randomusers.utils.orZero

fun UserDTO.toDomain(): UserDomain = UserDomain(
    gender = gender.orEmpty(),
    name = name.toDomain(),
    location = location.toDomain(),
    email = email.orEmpty(),
    login = login.toDomain(),
    dob = dob.toDomain(),
    registered = registered.toDomain(),
    phone = phone.orEmpty(),
    cell = cell.orEmpty(),
    id = id.toDomain(),
    picture = picture.toDomain(),
    nat = nat.orEmpty()
)

fun UserNameDTO.toDomain(): UserNameDomain = UserNameDomain(
    title = title.orEmpty(),
    first = first.orEmpty(),
    last = last.orEmpty()
)

fun UserLocationDTO.toDomain(): UserLocationDomain = UserLocationDomain(
    street = street.toDomain(),
    city = city.orEmpty(),
    state = state.orEmpty(),
    country = country.orEmpty(),
    postcode = postcode.orEmpty(),
    coordinates = coordinates.toDomain(),
    timezone = timezone.toDomain()
)

fun UserStreetDTO.toDomain(): UserStreetDomain = UserStreetDomain(
    number = number.orZero(),
    name = name.orEmpty()
)

fun UserCoordinatesDTO.toDomain(): UserCoordinatesDomain = UserCoordinatesDomain(
    latitude = latitude.orEmpty(),
    longitude = longitude.orEmpty()
)

fun UserTimezoneDTO.toDomain(): UserTimezoneDomain = UserTimezoneDomain(
    offset = offset.orEmpty(),
    description = description.orEmpty()
)

fun UserLoginDTO.toDomain(): UserLoginDomain = UserLoginDomain(
    uuid = uuid.orEmpty(),
    username = username.orEmpty(),
    password = password.orEmpty(),
    salt = salt.orEmpty(),
    md5 = md5.orEmpty(),
    sha1 = sha1.orEmpty(),
    sha256 = sha256.orEmpty()
)

fun UserDobDTO.toDomain(): UserDobDomain = UserDobDomain(
    date = date.orEmpty(),
    age = age.orZero()
)

fun UserRegisteredDTO.toDomain(): UserRegisteredDomain = UserRegisteredDomain(
    date = date.orEmpty(),
    age = age.orZero()
)

fun UserIdDTO.toDomain(): UserIdDomain = UserIdDomain(
    name = name.orEmpty(),
    value = value.orEmpty()
)

fun UserProfilePictureDTO.toDomain(): UserProfilePictureDomain = UserProfilePictureDomain(
    large = large.orEmpty(),
    medium = medium.orEmpty(),
    thumbnail = thumbnail.orEmpty()
)

