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

package com.kogicodes.pawametinderforcats.data


import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kogicodes.pawametinderforcats.model.Breed
import com.kogicodes.pawametinderforcats.model.Category
import java.util.*

class Converters {
    var gson = Gson()

    @TypeConverter
    fun stringToBreeds(data: String?): List<Breed>? {
        if (data == null) {
            return Collections.emptyList()
        }


        val listType = object : TypeToken<List<Breed>>() {

        }.type

        return gson.fromJson<List<Breed>>(data, listType)
    }

    @TypeConverter
    fun breedsToString(someObjects: List<Breed>?): String? {
        if (someObjects != null) {
            return gson.toJson(someObjects)
        }
        return null
    }



    @TypeConverter
    fun stringToCategories(data: String?): List<Category>? {
        if (data == null) {
            return Collections.emptyList()
        }


        val listType = object : TypeToken<List<Category>>() {

        }.type

        return gson.fromJson<List<Category>>(data, listType)
    }

    @TypeConverter
    fun categpriesToString(someObjects: List<Category>?): String? {
        if (someObjects != null) {
            return gson.toJson(someObjects)
        }
        return null
    }






    @TypeConverter
    fun stringToActionState(data: String?): ActionState? {
        if (data == null) {
            return null
        }
        return ActionState.valueOf(data)
    }


    @TypeConverter
    fun actionStateToString(data: ActionState?): String? {
        if (data == null) {
            return null
        }
        return data.name
    }




}
