package com.anggapambudi.quranapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val number: String,
        val name: String,
        val arti: String,
        val type: String,
        val ayat: String,
        val asma: String
)
