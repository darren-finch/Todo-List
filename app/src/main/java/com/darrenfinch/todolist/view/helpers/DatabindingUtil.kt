package com.darrenfinch.todolist.view.helpers

import com.darrenfinch.todolist.model.TimeUnit
import java.text.DateFormat
import java.util.*

object DatabindingUtil
{
    fun dateStringFromLong(value: Long): String = DateFormat.getDateInstance().format(Date(value))
    fun putTogetherTimeAndUnit(time: Int, timeUnit: TimeUnit) = "$time $timeUnit${ifTimeIsPluralAppendAnS(time)}"
    private fun ifTimeIsPluralAppendAnS(time: Int) = if(time > 1) "s" else ""
}