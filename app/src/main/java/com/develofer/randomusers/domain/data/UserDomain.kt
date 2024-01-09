package com.develofer.randomusers.domain.data

import android.os.Parcelable
import com.develofer.randomusers.data.source.remote.response.UserLoginDTO
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDomain(
    val gender: String,
    val name: UserNameDomain,
    val location: UserLocationDomain,
    val email: String,
    val login: UserLoginDomain,
    val registered: UserRegisteredDomain,
    val phone: String,
    val picture: UserProfilePictureDomain,
): Parcelable

@Parcelize
data class UserNameDomain(
    val title: String,
    val first: String,
    val last: String
): Parcelable

@Parcelize
data class UserLocationDomain(
    val coordinates: UserCoordinatesDomain
): Parcelable

@Parcelize
data class UserCoordinatesDomain(
    val latitude: String,
    val longitude: String
): Parcelable

@Parcelize
data class UserLoginDomain(
    val uuid: String,
): Parcelable

@Parcelize
data class UserRegisteredDomain(
    val date: String
): Parcelable

@Parcelize
data class UserProfilePictureDomain(
    val large: String,
    val medium: String,
    val thumbnail: String
): Parcelable

