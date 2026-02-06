package com.example.caveavin.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tastings",
    foreignKeys = [
        ForeignKey(
            entity = Bottle::class,
            parentColumns = ["id"],
            childColumns = ["bottleId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Tasting(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val bottleId: Long,
    val date: Long,
    val rating: Float,
    val notes: String,
    val aromas: String?,
    val foodPairing: String?
)
