package com.example.chapter_ten

import android.content.Context
import androidx.room.Room
import com.example.chapter_ten.database.CrimeDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import java.util.UUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor( context : Context ){

    @OptIn(DelicateCoroutinesApi::class)
    private val coroutineScope: CoroutineScope = GlobalScope

    private val database : CrimeDatabase = Room
        .databaseBuilder(context.applicationContext, CrimeDatabase::class.java, DATABASE_NAME)
        .createFromAsset(DATABASE_NAME)
        .build()

//    suspend fun getCrimes() : List<Crime> = database.CrimeDao().getCrimes()
    fun getCrimes() : Flow<List<Crime>> = database.CrimeDao().getCrimes()

    suspend fun getCrime( id : UUID ) : Crime = database.CrimeDao().getCrime(id)

    fun updateCrime(crime : Crime) {
        coroutineScope.launch{ database.CrimeDao().updateCrime(crime) }
    }

    // STATIC FUNCTIONS / VARIABLES
    companion object {
        private var INSTANCE : CrimeRepository? = null

        fun initialize(context: Context) {
            if ( INSTANCE == null ) {
                INSTANCE = CrimeRepository(context)
            }
        }

        // IF NULL - THROW EXCEPTION
        fun get() : CrimeRepository {
            return INSTANCE ?: throw IllegalStateException("CrimeRepository must be initalized")
        }

    }
}