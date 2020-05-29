package com.example.week4

import androidx.room.*
@Dao
interface MovieDAO {
    @Query("SELECT * FROM Movie")
    fun getAll(): List<Movie>

    @Query("SELECT * FROM movie WHERE name LIKE :name")
    fun findByName(name: String): Movie

    @Query("SELECT * FROM movie WHERE id =:id")
    fun findById(id: Int): Movie

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: Movie): Long

    @Delete
    fun delete(movie: Movie)

    @Update
    fun update(movie: Movie)

    @Query("DELETE FROM movie")
    fun deleteAll()
}