package com.kogicodes.pawametinderforcats.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Weight : Serializable {
    @SerializedName("imperial")
    @Expose
    var imperial: String? = null
    @SerializedName("metric")
    @Expose
    var metric: String? = null
}