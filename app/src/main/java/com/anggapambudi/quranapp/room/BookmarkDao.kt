package com.anggapambudi.quranapp.room

import androidx.room.*

@Dao
interface BookmarkDao {

    @Insert
    suspend fun addBookmark(bookmarkEntity: BookmarkEntity)

    @Update
    suspend fun updateBookmark(bookmarkEntity: BookmarkEntity)

    @Delete
    suspend fun deleteBookmark(bookmarkEntity: BookmarkEntity)

    @Query("SELECT * FROM BookmarkEntity ORDER BY id DESC")
    suspend fun getBookmark(): List<BookmarkEntity>

}