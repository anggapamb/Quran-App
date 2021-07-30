package com.anggapambudi.quranapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookmarkEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val ayat: String,
        val arab: String,
        val indo: String
)
