package com.develofer.randomusers.domain.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDomain(
    val gender: String,
    val name: UserNameDomain,
    val location: UserLocationDomain,
    val email: String,
    val login: UserLoginDomain,
    val dob: UserDobDomain,
    val registered: UserRegisteredDomain,
    val phone: String,
    val cell: String,
    val id: UserIdDomain,
    val picture: UserProfilePictureDomain,
    val nat: String
): Parcelable

@Parcelize
data class UserNameDomain(
    val title: String,
    val first: String,
    val last: String
): Parcelable

@Parcelize
data class UserLocationDomain(
    val street: UserStreetDomain,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: UserCoordinatesDomain,
    val timezone: UserTimezoneDomain
): Parcelable

@Parcelize
data class UserStreetDomain(
    val number: Int,
    val name: String
): Parcelable

@Parcelize
data class UserCoordinatesDomain(
    val latitude: String,
    val longitude: String
): Parcelable

@Parcelize
data class UserTimezoneDomain(
    val offset: String,
    val description: String
): Parcelable

@Parcelize
data class UserLoginDomain(
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
): Parcelable

@Parcelize
data class UserDobDomain(
    val date: String,
    val age: Int
): Parcelable

@Parcelize
data class UserRegisteredDomain(
    val date: String,
    val age: Int
): Parcelable

@Parcelize
data class UserIdDomain(
    val name: String,
    val value: String
): Parcelable

@Parcelize
data class UserProfilePictureDomain(
    val large: String,
    val medium: String,
    val thumbnail: String
): Parcelable

