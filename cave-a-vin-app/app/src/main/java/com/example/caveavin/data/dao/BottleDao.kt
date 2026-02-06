package com.example.caveavin.data.dao

import androidx.room.*
import com.example.caveavin.data.model.Bottle
import com.example.caveavin.data.model.WineType
import kotlinx.coroutines.flow.Flow

@Dao
interface BottleDao {
    @Query("SELECT * FROM bottles ORDER BY createdAt DESC")
    fun getAllBottles(): Flow<List<Bottle>>
    
    @Query("SELECT * FROM bottles WHERE id = :bottleId")
    suspend fun getBottleById(bottleId: Long): Bottle?
    
    @Query("SELECT * FROM bottles WHERE type = :type ORDER BY vintage DESC")
    fun getBottlesByType(type: WineType): Flow<List<Bottle>>
    
    @Query("SELECT * FROM bottles WHERE region LIKE '%' || :region || '%' ORDER BY name")
    fun getBottlesByRegion(region: String): Flow<List<Bottle>>
    
    @Query("SELECT * FROM bottles WHERE vintage = :vintage ORDER BY name")
    fun getBottlesByVintage(vintage: Int): Flow<List<Bottle>>
    
    @Query("SELECT * FROM bottles WHERE name LIKE '%' || :query || '%' OR appellation LIKE '%' || :query || '%' OR region LIKE '%' || :query || '%'")
    fun searchBottles(query: String): Flow<List<Bottle>>
    
    @Query("SELECT SUM(quantity) FROM bottles")
    fun getTotalBottles(): Flow<Int?>
    
    @Query("SELECT SUM(quantity * purchasePrice) FROM bottles")
    fun getTotalValue(): Flow<Double?>
    
    @Query("SELECT DISTINCT region FROM bottles ORDER BY region")
    fun getAllRegions(): Flow<List<String>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBottle(bottle: Bottle): Long
    
    @Update
    suspend fun updateBottle(bottle: Bottle)
    
    @Delete
    suspend fun deleteBottle(bottle: Bottle)
    
    @Query("UPDATE bottles SET quantity = :newQuantity WHERE id = :bottleId")
    suspend fun updateQuantity(bottleId: Long, newQuantity: Int)
}
