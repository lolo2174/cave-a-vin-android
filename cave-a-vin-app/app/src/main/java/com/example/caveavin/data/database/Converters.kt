package com.example.caveavin.data.database

import androidx.room.TypeConverter
import com.example.caveavin.data.model.WineType

class Converters {
    @TypeConverter
    fun fromWineType(value: WineType): String {
        return value.name
    }
    
    @TypeConverter
    fun toWineType(value: String): WineType {
        return WineType.valueOf(value)
    }
}
