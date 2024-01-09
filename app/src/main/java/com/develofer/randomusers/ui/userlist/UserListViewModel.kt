package com.develofer.randomusers.ui.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develofer.randomusers.domain.data.UserDomain
import com.develofer.randomusers.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {

    init {
        getUsers()
    }

    private val usersLiveData: MutableLiveData<List<UserDomain>> = MutableLiveData()
    private val usersList: MutableList<UserDomain> = mutableListOf()
    private var usersFilteredList: MutableList<UserDomain> = mutableListOf()
    private var currentPage = 1
    private var isShowingRawList = true

    fun getCurrentPage() = currentPage

    fun addUsers(users: List<UserDomain>) {
        usersList.addAll(users)
    }

    fun getUsersLiveData() = usersLiveData as LiveData<List<UserDomain>>

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            getUsersUseCase(currentPage).collect {
                usersList.addAll(removeDuplicates(it))
                usersLiveData.postValue(usersList.toList())
            }
        }
    }

    fun increasePage() {
        currentPage++
    }

    private fun removeDuplicates(newUsersList: List<UserDomain>): List<UserDomain> =
        newUsersList.distinctBy { it.login.uuid }

    fun filterByName(searchText: String) {
        usersFilteredList = usersList.filter {
            it.name.first.contains(searchText, ignoreCase = true)
        }.toMutableList()
        usersLiveData.postValue(usersFilteredList.toList())
        isShowingRawList = false
    }

    fun filterByEmail(searchText: String) {
        usersFilteredList = usersList.filter {
            it.email.contains(searchText, ignoreCase = true)
        }.toMutableList()
        usersLiveData.postValue(usersFilteredList.toList())
        isShowingRawList = false
    }

    fun showRawList() {
        usersLiveData.postValue(usersList.toList())
        isShowingRawList = true
    }

    fun isShowingRawList(): Boolean = isShowingRawList

}