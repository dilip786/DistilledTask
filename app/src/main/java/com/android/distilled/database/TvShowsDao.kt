package com.android.distilled.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery

import androidx.room.RawQuery




@Dao
interface TvShowsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: TvShowEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(vararg obj: TvShowEntity): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(obj: List<TvShowEntity>): List<Long>

    @Update
    fun update(TvShowEntity: TvShowEntity): Int

    @Delete
    fun delete(TvShowEntity: TvShowEntity)

    @Query("SELECT * FROM tvShows ORDER BY :orderBy DESC")
    fun getTvShowsList(orderBy: String = "name"): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tvShows")
    fun getTvShowsListOnce():List<TvShowEntity>

    @Query("SELECT * FROM tvShows WHERE name =(:name)")
    fun getEmployeesByDesignation(name: String): LiveData<List<TvShowEntity>>

    @RawQuery(observedEntities = [TvShowEntity::class])
    fun getShowsEntities(query: SupportSQLiteQuery?): LiveData<List<TvShowEntity>>
}