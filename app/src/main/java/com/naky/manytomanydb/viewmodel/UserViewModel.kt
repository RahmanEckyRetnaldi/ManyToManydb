package com.naky.manytomanydb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.naky.manytomanydb.database.UserDatabase
import com.naky.manytomanydb.entity.*
import com.naky.manytomanydb.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val _readAllData = MutableLiveData<List<UserWithLibrary>>()
    val readAllData : LiveData<List<UserWithLibrary>> = _readAllData
    private val repository : UserRepository

    private val _readAllDataByLibrary = MutableLiveData<List<LibraryWithUser>>()
    val readAllDataByLibrary : LiveData<List<LibraryWithUser>> = _readAllDataByLibrary

    init {
        val userDao = UserDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
    }

    fun addUser(item : List<User>){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUser(item)
        }
    }
    fun addLibrary(item: List<Library>){
        viewModelScope.launch(Dispatchers.IO){
            repository.addLibrary(item)
        }
    }
    fun addUserLibraryCrossRef(item: List<UserLibraryCrossRef>){
        viewModelScope.launch(Dispatchers.IO){
            repository.addUserLibrarryCrossRef(item)
        }
    }

    fun getUserWithLibrary(userId : Int) {
        viewModelScope.launch(Dispatchers.IO){
            _readAllData.postValue(repository.getUserwithLibrary(userId))
        }
    }

    fun getLibraryWithUser(id :Int){
        viewModelScope.launch(Dispatchers.IO){
            _readAllDataByLibrary.postValue(repository.getLibraryWithUser(id))
        }
    }

}