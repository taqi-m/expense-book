package com.expense.book.model.data.database.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VehicleUsageLogDao {
    @Insert
    suspend fun insertVehicleUsageLog(vehicleUsageLog: VehicleUsageLogEntity)

    @Query("SELECT * FROM vehicle_usage_logs")
    suspend fun getAllVehicleUsageLogs(): List<VehicleUsageLogEntity>
}