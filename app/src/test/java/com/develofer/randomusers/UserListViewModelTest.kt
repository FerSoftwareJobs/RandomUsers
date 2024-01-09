package com.develofer.randomusers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.develofer.randomusers.domain.data.UserDomain
import com.develofer.randomusers.domain.data.UserNameDomain
import com.develofer.randomusers.domain.usecase.GetUsersUseCase
import com.develofer.randomusers.ui.userlist.UserListViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getUsersUseCase: GetUsersUseCase = mockk()

    private lateinit var viewModel: UserListViewModel

    @Before
    fun setup() {
        viewModel = UserListViewModel(getUsersUseCase)
    }

    @Test
    fun `getUsers updates usersLiveData`() = runBlocking {
        // Arrange
        val users = listOf(
            mockk<UserDomain>(relaxed = true) {
                every { name } returns UserNameDomain(title = "Mr", first = "John", last = "Doe")
            },
            mockk(relaxed = true) {
                every { name } returns UserNameDomain(title = "Mr", first = "John", last = "Smith")
            },
            mockk(relaxed = true) {
                every { name } returns UserNameDomain(title = "Mr", first = "Alice", last = "Johnson")
            }
        )

        coEvery { getUsersUseCase.invoke(any()) } returns flowOf(users)

        // Act
        viewModel.getUsers()

        // Assert
        coVerify { getUsersUseCase.invoke(viewModel.getCurrentPage()) }

        assert(viewModel.getUsersLiveData().value is List<UserDomain>)
    }

    @Test
    fun `increasePage increments currentPage`() {
        // Arrange
        val initialPage = viewModel.getCurrentPage()

        // Act
        viewModel.increasePage()

        // Assert
        assert(viewModel.getCurrentPage() == initialPage + 1)
    }

    @Test
    fun `filterByName updates usersLiveData`() {
        // Arrange
        val searchText = "John"
        val users = listOf(
            mockk<UserDomain>(relaxed = true) {
                every { name } returns UserNameDomain(title = "Mr", first = "John", last = "Doe")
            },
            mockk(relaxed = true) {
                every { name } returns UserNameDomain(title = "Mr", first = "John", last = "Smith")
            },
            mockk(relaxed = true) {
                every { name } returns UserNameDomain(title = "Mr", first = "Alice", last = "Johnson")
            }
        )
        viewModel.addUsers(users)

        // Act
        viewModel.filterByName(searchText)

        // Assert
        assert(viewModel.getUsersLiveData().value == users.filter {
            it.name.first.contains(searchText, ignoreCase = true)
        })
    }

    @Test
    fun `filterByEmail updates usersLiveData`() {
        // Arrange
        val searchText = "john@example.com"
        val users = listOf(
            mockk<UserDomain>(relaxed = true) {
                every { email } returns "john@example.com"
            },
            mockk(relaxed = true) {
                every { email } returns "john@example.com"
            },
            mockk(relaxed = true) {
                every { email } returns "alice@example.com"
            }
        )
        viewModel.addUsers(users)

        // Act
        viewModel.filterByEmail(searchText)

        // Assert
        assert(viewModel.getUsersLiveData().value == users.filter {
            it.email.contains(searchText, ignoreCase = true)
        })
    }

    @Test
    fun `showRawList updates usersLiveData`() {
        // Arrange
        val users = listOf(
            mockk<UserDomain>(relaxed = true) {
                every { name } returns UserNameDomain(title = "Mr", first = "John", last = "Doe")
            },
            mockk(relaxed = true) {
                every { name } returns UserNameDomain(title = "Mr", first = "John", last = "Smith")
            },
            mockk(relaxed = true) {
                every { name } returns UserNameDomain(title = "Mr", first = "Alice", last = "Johnson")
            }
        )
        viewModel.addUsers(users)

        // Act
        viewModel.showRawList()

        // Assert
        assert(viewModel.getUsersLiveData().value == users)
    }
}