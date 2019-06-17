package com.kogicodes.pawametinderforcats.data.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import com.kogicodes.pawametinderforcats.model.Cat

@Dao
interface CatDao {
    @Query("SELECT * FROM CAT   ")
    fun getAll(): LiveData<List<Cat>>

    @Query("SELECT * FROM CAT  WHERE isFavorite= 1 ")
    fun getAllFavorites(): LiveData<List<Cat>>


    @Query("SELECT * FROM CAT WHERE id = :id")
    fun getWithId(id : String) : LiveData<Cat>

    @Insert(onConflict = IGNORE)
    fun insert(model: Cat)

    @Update
    fun update(model: Cat)

    @Delete
    fun delete(model: Cat)

    @Query("DELETE FROM Cat")
    fun delete()
}
