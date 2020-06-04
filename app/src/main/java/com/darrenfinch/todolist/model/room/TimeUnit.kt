package com.darrenfinch.todolist.model.room

import java.util.*

enum class TimeUnit {
    MIN {
        override fun toString() = timeUnitToStringValues[MIN] ?: ""
    },
    HR {
        override fun toString() = timeUnitToStringValues[HR] ?: ""
    },
    DAY {
        override fun toString() = timeUnitToStringValues[DAY] ?: ""
    };

    companion object {
        val defaultUnit = MIN

        //Make sure these maps are in the exact order needed and have the exact values needed.
        val timeUnitToStringValues = mapOf(
            MIN to "min",
            HR to "hr",
            DAY to "day"
        )
        val stringToTimeUnitValues = mapOf(
            "min" to MIN,
            "hr" to HR,
            "day" to DAY
        )

        fun fromString(value: String): TimeUnit {
            return stringToTimeUnitValues[formatString(value)] ?: defaultUnit
        }

        private fun formatString(value: String) = value.toLowerCase(Locale.ROOT).removeSuffix("s")
    }
}