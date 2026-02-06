package com.example.caveavin.data

import com.example.caveavin.data.dao.BottleDao
import com.example.caveavin.data.dao.TastingDao
import com.example.caveavin.data.model.Bottle
import com.example.caveavin.data.model.Tasting
import com.example.caveavin.data.model.WineType
import kotlinx.coroutines.flow.Flow

class WineCellarRepository(
    private val bottleDao: BottleDao,
    private val tastingDao: TastingDao
) {
    // Bottle operations
    fun getAllBottles(): Flow<List<Bottle>> = bottleDao.getAllBottles()
    
    suspend fun getBottleById(id: Long): Bottle? = bottleDao.getBottleById(id)
    
    fun getBottlesByType(type: WineType): Flow<List<Bottle>> = bottleDao.getBottlesByType(type)
    
    fun getBottlesByRegion(region: String): Flow<List<Bottle>> = bottleDao.getBottlesByRegion(region)
    
    fun getBottlesByVintage(vintage: Int): Flow<List<Bottle>> = bottleDao.getBottlesByVintage(vintage)
    
    fun searchBottles(query: String): Flow<List<Bottle>> = bottleDao.searchBottles(query)
    
    fun getTotalBottles(): Flow<Int?> = bottleDao.getTotalBottles()
    
    fun getTotalValue(): Flow<Double?> = bottleDao.getTotalValue()
    
    fun getAllRegions(): Flow<List<String>> = bottleDao.getAllRegions()
    
    suspend fun insertBottle(bottle: Bottle): Long = bottleDao.insertBottle(bottle)
    
    suspend fun updateBottle(bottle: Bottle) = bottleDao.updateBottle(bottle)
    
    suspend fun deleteBottle(bottle: Bottle) = bottleDao.deleteBottle(bottle)
    
    suspend fun updateQuantity(bottleId: Long, newQuantity: Int) = 
        bottleDao.updateQuantity(bottleId, newQuantity)
    
    // Tasting operations
    fun getTastingsForBottle(bottleId: Long): Flow<List<Tasting>> = 
        tastingDao.getTastingsForBottle(bottleId)
    
    fun getRecentTastings(): Flow<List<Tasting>> = tastingDao.getRecentTastings()
    
    suspend fun insertTasting(tasting: Tasting): Long = tastingDao.insertTasting(tasting)
    
    suspend fun updateTasting(tasting: Tasting) = tastingDao.updateTasting(tasting)
    
    suspend fun deleteTasting(tasting: Tasting) = tastingDao.deleteTasting(tasting)
}
