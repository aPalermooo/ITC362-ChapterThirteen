package com.example.chapter_ten.database

import androidx.room.Dao
import androidx.room.Query
import com.example.chapter_ten.Crime
import java.util.UUID
import kotlinx.coroutines.flow.Flow

// KEYWORD INTERFACE
//      similar to abstract, but for a function. Functions must be implemented before use

@Dao    // Database Access Object
interface CrimeDao {

    @Query("Select * From crime")
//    suspend fun getCrimes() : List<Crime>
    fun getCrimes() : Flow<List<Crime>>

    @Query("Select * From crime Where id=(:id)")
    suspend fun getCrime( id : UUID) : Crime
}