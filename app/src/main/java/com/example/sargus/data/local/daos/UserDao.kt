package com.example.sargus.data.local.daos

import androidx.room.*
import com.example.sargus.data.local.model.Users

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: Users)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(contributors: List<Users>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(user: Users)

    @Query("SELECT * FROM users")
    fun getAllUsers():List<Users>

}