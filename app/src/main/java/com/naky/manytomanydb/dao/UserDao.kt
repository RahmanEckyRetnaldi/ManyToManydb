package com.naky.manytomanydb.dao

import androidx.room.*
import com.naky.manytomanydb.entity.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(item : List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLibrary(item : List<Library>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserLibraryCrossRef(item : List<UserLibraryCrossRef>)

    @Transaction
    @Query("SELECT * FROM User WHERE userId = :userId")
    fun getUserWithLibrary(userId : Int) : List<UserWithLibrary>

    @Transaction
    @Query("SELECT * FROM Library WHERE id = :id")
    fun getLibraryWithUser(id : Int) : List<LibraryWithUser>
}