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
