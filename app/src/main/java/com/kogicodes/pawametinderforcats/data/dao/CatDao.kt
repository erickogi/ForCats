/*
 * Copyright 2019 Eric Kogi. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kogicodes.pawametinderforcats.data.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
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
