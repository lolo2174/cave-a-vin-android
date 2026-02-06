package com.example.caveavin.data.dao

import androidx.room.*
import com.example.caveavin.data.model.Tasting
import kotlinx.coroutines.flow.Flow

@Dao
interface TastingDao {
    @Query("SELECT * FROM tastings WHERE bottleId = :bottleId ORDER BY date DESC")
    fun getTastingsForBottle(bottleId: Long): Flow<List<Tasting>>
    
    @Query("SELECT * FROM tastings ORDER BY date DESC LIMIT 10")
    fun getRecentTastings(): Flow<List<Tasting>>
    
    @Insert
    suspend fun insertTasting(tasting: Tasting): Long
    
    @Update
    suspend fun updateTasting(tasting: Tasting)
    
    @Delete
    suspend fun deleteTasting(tasting: Tasting)
}
