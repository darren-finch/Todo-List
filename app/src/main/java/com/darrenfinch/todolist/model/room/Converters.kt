package com.darrenfinch.todolist.model.room

import androidx.room.TypeConverter
import com.darrenfinch.todolist.model.TimeUnit

class Converters
{
    @TypeConverter
    fun timeUnitToString(value: TimeUnit) = value.toString()

    @TypeConverter
    fun stringToTimeUnit(value: String) = enumValueOf<TimeUnit>(value)
}