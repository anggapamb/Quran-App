package com.anggapambudi.quranapp.room

import androidx.room.*

@Dao
interface HistoryDao {

    @Insert
    suspend fun onAddHistory(historyEntity: HistoryEntity)

    @Delete
    suspend fun onDelete(historyEntity: HistoryEntity)

    @Query("SELECT * FROM HistoryEntity ORDER BY id DESC")
    suspend fun getHistory(): List<HistoryEntity>

}