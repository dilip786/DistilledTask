package com.android.distilled.database

import androidx.room.TypeConverter
object BaseTypeConverter {

    @TypeConverter
    @JvmStatic
    fun textsFromList(texts: List<String>): String {
        return texts.convertListToString()
    }

    @TypeConverter
    @JvmStatic
    fun textsFromString(texts: String): List<String> {
        return texts.convertStringToList()
    }

    @TypeConverter
    @JvmStatic
    fun integerFromList(integers: List<Int>): String {
        return integers.convertIntegerListToString()
    }

    @TypeConverter
    @JvmStatic
    fun integerFromString(integers: String): List<Int> {
        return integers.convertStringToIntegerList()
    }
}