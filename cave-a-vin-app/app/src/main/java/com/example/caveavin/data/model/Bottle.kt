package com.example.caveavin.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bottles")
data class Bottle(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val appellation: String,
    val vintage: Int,
    val region: String,
    val grapeVariety: String,
    val type: WineType,
    val quantity: Int,
    val purchasePrice: Double,
    val location: String,
    val peakYear: Int?,
    val photoPath: String?,
    val createdAt: Long = System.currentTimeMillis()
)

enum class WineType {
    RED,
    WHITE,
    ROSE,
    SPARKLING
}
