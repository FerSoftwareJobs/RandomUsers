package com.develofer.randomusers.data.source.remote.response

data class UserDTO(
    val gender: String?,
    val name: UserNameDTO,
    val location: UserLocationDTO,
    val email: String?,
    val login: UserLoginDTO,
    val dob: UserDobDTO,
    val registered: UserRegisteredDTO,
    val phone: String?,
    val cell: String?,
    val id: UserIdDTO,
    val picture: UserProfilePictureDTO,
    val nat: String?
)


data class UserNameDTO(
    val title: String?,
    val first: String?,
    val last: String?
)

data class UserLocationDTO(
    val street: UserStreetDTO,
    val city: String?,
    val state: String?,
    val country: String?,
    val postcode: String?,
    val coordinates: UserCoordinatesDTO,
    val timezone: UserTimezoneDTO
)

data class UserStreetDTO(
    val number: Int?,
    val name: String?
)

data class UserCoordinatesDTO(
    val latitude: String?,
    val longitude: String?
)

data class UserTimezoneDTO(
    val offset: String?,
    val description: String?
)

data class UserLoginDTO(
    val uuid: String?,
    val username: String?,
    val password: String?,
    val salt: String?,
    val md5: String?,
    val sha1: String?,
    val sha256: String?
)

data class UserDobDTO(
    val date: String?,
    val age: Int?
)

data class UserRegisteredDTO(
    val date: String?,
    val age: Int?
)

data class UserIdDTO(
    val name: String?,
    val value: String?
)

data class UserProfilePictureDTO(
    val large: String?,
    val medium: String?,
    val thumbnail: String?
)
