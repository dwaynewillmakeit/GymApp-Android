package com.dwaynewillmakeit.toughfitnessapp.data.local.converter

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.TimeZone


class DateConverter {

    @TypeConverter
    fun datetimeToLong(dateTime: LocalDateTime):Long{

        return dateTime.toEpochSecond(ZoneOffset.UTC);
    }

    @TypeConverter
    fun longToLocalDateTime(datetime: Long):LocalDateTime{
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(datetime),TimeZone.getDefault().toZoneId())
    }
}