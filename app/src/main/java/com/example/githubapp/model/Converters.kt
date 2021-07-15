package com.example.githubapp.model

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromOwnerToString(value: Owner?): String? {
        return value?.let { it.login }
    }

    @TypeConverter
    fun stringToOwner(value: String?): Owner?{
        return value?.let { Owner(value) }
    }
}