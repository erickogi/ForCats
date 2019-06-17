package com.kogicodes.pawametinderforcats.model.custom


import com.dev.cabinzz.models.custom.Status
import com.kogicodes.pawametinderforcats.ui.uiUtils.AppiException

class Resource<T>
private constructor(val status: Status, val data: T?, val message: String?, val exception: AppiException?) {
    companion object {


        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(msg: String, data: T?, exception: AppiException): Resource<T> {
            return Resource(Status.ERROR, data, msg, exception)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null, null)
        }
    }
}