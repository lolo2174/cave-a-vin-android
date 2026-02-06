package com.example.caveavin.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.caveavin.data.dao.BottleDao
import com.example.caveavin.data.dao.TastingDao
import com.example.caveavin.data.model.Bottle
import com.example.caveavin.data.model.Tasting

@Database(
    entities = [Bottle::class, Tasting::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class WineCellarDatabase : RoomDatabase() {
    abstract fun bottleDao(): BottleDao
    abstract fun tastingDao(): TastingDao
    
    companion object {
        @Volatile
        private var INSTANCE: WineCellarDatabase? = null
        
        fun getDatabase(context: Context): WineCellarDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WineCellarDatabase::class.java,
                    "wine_cellar_database"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
