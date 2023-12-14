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
    private val getUsersDataUseCase: GetUsersUseCase
): ViewModel() {

    private val usersLiveData: MutableLiveData<List<UserDomain>> = MutableLiveData()
    fun getUsersLiveData() = usersLiveData as LiveData<List<UserDomain>>

    fun getUsers(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getUsersDataUseCase(page).collect {
                usersLiveData.postValue(it)
            }
        }
    }
}