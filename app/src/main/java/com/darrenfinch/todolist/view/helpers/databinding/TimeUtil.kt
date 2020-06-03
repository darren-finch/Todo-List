package com.darrenfinch.todolist.view.helpers.databinding

import com.darrenfinch.todolist.model.TimeUnit
import java.text.DateFormat
import java.util.*

object TimeUtil
{
    fun dateStringFromLong(value: Long): String = DateFormat.getDateInstance().format(Date(value))
    fun putTogetherTimeAndUnit(value: Int, estimatedTTCUnit: TimeUnit) = "$value$estimatedTTCUnit"
}