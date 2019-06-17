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