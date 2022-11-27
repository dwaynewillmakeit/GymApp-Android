package com.dwaynewillmakeit.toughfitnessapp.data.local.converter

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*


class DateConverter {

    @TypeConverter
    fun datetimeToLong(dateTime: LocalDateTime):Long{

        return dateTime.toEpochSecond(ZoneOffset.UTC);
    }

    @TypeConverter
    fun longToLocalDateTime(datetime: Long):LocalDateTime{
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(datetime),TimeZone.getDefault().toZoneId())
    }
}