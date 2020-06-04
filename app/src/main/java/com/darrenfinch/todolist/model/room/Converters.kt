package com.darrenfinch.todolist.model.room

import androidx.room.TypeConverter

class Converters
{
    @TypeConverter
    fun timeUnitToString(value: TimeUnit) = value.toString()

    @TypeConverter
    fun stringToTimeUnit(value: String) : TimeUnit
    {
        //If the enum schema changes, we'll just catch that exception here.
        try
        {
            return TimeUnit.fromString(value)
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
        return TimeUnit.defaultUnit
    }
}