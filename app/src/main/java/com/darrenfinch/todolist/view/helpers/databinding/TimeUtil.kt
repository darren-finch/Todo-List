package com.darrenfinch.todolist.view.helpers.databinding

import com.darrenfinch.todolist.model.TimeUnit
import java.text.DateFormat
import java.util.*

object TimeUtil
{
    fun dateStringFromLong(value: Long?): String = if(value != null) DateFormat.getDateInstance().format(Date(value)) else ""
    fun putTogetherTimeAndUnit(value: Int?, estimatedTTCUnit: TimeUnit?) = if(value != null && estimatedTTCUnit != null) "$value$estimatedTTCUnit" else "1 hr" //TODO: REMOVE THIS
}