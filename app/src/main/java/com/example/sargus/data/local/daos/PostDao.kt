package com.example.sargus.data.local.daos

import androidx.room.*
import com.example.sargus.data.local.model.UserRepos

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(userRepos: UserRepos)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(contributors: List<UserRepos>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePost(userRepos: UserRepos)

    @Query("SELECT * FROM posts")
    fun getAllPosts():List<UserRepos>

}