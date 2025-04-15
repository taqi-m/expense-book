package com.expense.book.model.data.database.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicle_usage_logs")
data class VehicleUsageLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val vehicleName: String,
    val usage: String
)