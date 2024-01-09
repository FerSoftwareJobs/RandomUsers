package com.develofer.randomusers

import com.develofer.randomusers.data.repository.UsersRepositoryImpl
import com.develofer.randomusers.data.source.remote.api.UsersApi
import com.develofer.randomusers.data.source.remote.response.InfoDTO
import com.develofer.randomusers.data.source.remote.response.UserCoordinatesDTO
import com.develofer.randomusers.data.source.remote.response.UserDTO
import com.develofer.randomusers.data.source.remote.response.UserDobDTO
import com.develofer.randomusers.data.source.remote.response.UserIdDTO
import com.develofer.randomusers.data.source.remote.response.UserLocationDTO
import com.develofer.randomusers.data.source.remote.response.UserLoginDTO
import com.develofer.randomusers.data.source.remote.response.UserNameDTO
import com.develofer.randomusers.data.source.remote.response.UserProfilePictureDTO
import com.develofer.randomusers.data.source.remote.response.UserRegisteredDTO
import com.develofer.randomusers.data.source.remote.response.UserResponseDTO
import com.develofer.randomusers.data.source.remote.response.UserStreetDTO
import com.develofer.randomusers.data.source.remote.response.UserTimezoneDTO
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class UsersRepositoryImplTest {

    @Mock
    private lateinit var usersApi: UsersApi

    private lateinit var repository: UsersRepositoryImpl

    @Before
    fun setUp() {
        repository = UsersRepositoryImpl(usersApi)
    }

    @Test
    fun `getUsers returns Flow of ListUserDomain`(): Unit = runBlocking {
        val fakeUserResponse = UserResponseDTO(
            results = listOf(
                UserDTO(
                    name = UserNameDTO("Mr", "John", "Doe"),
                    email = "john.doe@example.com",
                    gender = "male",
                    phone = "1234567890",
                    registered = UserRegisteredDTO("2022-01-01", 25),
                    picture = UserProfilePictureDTO("large", "medium", "thumbnail"),
                    location = UserLocationDTO(
                        street = UserStreetDTO(
                            number = 123,
                            name = "Fake Street"
                        ),
                        city = "Anytown",
                        state = "CA",
                        country = "USA",
                        postcode = "12345",
                        coordinates = UserCoordinatesDTO("12.34", "56.78"),
                        timezone = UserTimezoneDTO("UTC", "PST")
                    ),
                    login = UserLoginDTO("fake_uuid", "fake_username", "fake_password", "fake_salt", "fake_md5", "fake_sha1", "fake_sha256"),
                    id = UserIdDTO("fake_uuid", "jhbu"),
                    nat = "US",
                    cell = "1234567890",
                    dob = UserDobDTO("2022-01-01", 25),
                )

            ),
            info = InfoDTO(page = 1, results = 1, seed = "fake_seed", version = "fake_version")
        )
        Mockito.`when`(usersApi.getUsers(20, 1)).thenReturn(Response.success(fakeUserResponse))

        val flow = repository.getUsers(1)

        flow.toList().first().let { users ->
            assertEquals(1, users.size)
            val user = users[0]
            assertEquals("Mr", user.name.title)
            assertEquals("John", user.name.first)
            assertEquals("Doe", user.name.last)
            assertEquals("john.doe@example.com", user.email)
            assertEquals("Hombre", user.gender)
            assertEquals("1234567890", user.phone)
            assertEquals("2022-01-01", user.registered.date)
            assertEquals("large", user.picture.large)
            assertEquals("12.34", user.location.coordinates.latitude)
            assertEquals("56.78", user.location.coordinates.longitude)
        }

        Mockito.verify(usersApi).getUsers(20, 1)
    }
}
