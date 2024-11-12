package com.example.chapter_ten.database

import androidx.room.TypeConverter
import java.util.Date

class CrimeTypeConverters {

    //converter (type cast from one data type to another)
    @TypeConverter
    fun fromDate(date: Date) : Long{
        return date.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long) : Date {
        return Date(millisSinceEpoch)
    }
}