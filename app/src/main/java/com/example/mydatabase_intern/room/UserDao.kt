package com.example.mydatabase_intern.room

import androidx.room.*

@Dao
interface UserDao {

    //retrieve
    @Query("SELECT * FROM users_table")
    fun getAllUsers():List<User>

    //update manually
    @Query("update users_table set age=:age,name=:name where id=:id")
    fun updateUserManually(id:Int,name:String,age:Int)
    //update where id
    @Update
    fun updateUser(user: User)

    //insert
    @Insert
    fun inserUser (user: User)
    @Insert
    fun adduserList(users:List<User>)

    @Delete
    fun deleteUser (user: User)
    @Query("DELETE FROM users_table WHERE name=:name")
    fun deleteUserManually (name: String)
}