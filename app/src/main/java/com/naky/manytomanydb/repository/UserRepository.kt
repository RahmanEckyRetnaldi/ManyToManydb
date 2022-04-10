package com.naky.manytomanydb.repository

import com.naky.manytomanydb.dao.UserDao
import com.naky.manytomanydb.entity.*

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(item : List<User>){
        userDao.insertUser(item)
    }

    suspend fun addLibrary(item : List<Library>){
        userDao.insertLibrary(item)
    }

    suspend fun addUserLibrarryCrossRef(item: List<UserLibraryCrossRef>){
        userDao.insertUserLibraryCrossRef(item)
    }

    fun getUserwithLibrary(userId :Int) : List<UserWithLibrary>{
        return userDao.getUserWithLibrary(userId)
    }

    fun getLibraryWithUser(id :Int) : List<LibraryWithUser>{
        return userDao.getLibraryWithUser(id)
    }
}