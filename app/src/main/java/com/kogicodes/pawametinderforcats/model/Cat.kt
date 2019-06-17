package com.kogicodes.pawametinderforcats.model

import androidx.annotation.NonNull
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.kogicodes.pawametinderforcats.data.ActionState
import java.io.Serializable

@Entity(
    primaryKeys = ["id"]
)
@JvmSuppressWildcards
class Cat : Serializable{
    @SerializedName("breeds")
    @Expose
    var breeds: List<Breed>? = null
    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null
    @NonNull
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("width")
    @Expose
    var width: Int? = null
    @SerializedName("height")
    @Expose
    var height: Int? = null


    var actionState: ActionState? = null
    var isFavorite: Boolean? = false




}