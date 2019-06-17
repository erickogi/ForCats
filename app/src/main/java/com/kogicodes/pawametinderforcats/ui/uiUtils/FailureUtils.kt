/*
 * *
 *  * Created by Kogi Eric  on 5/20/19 6:30 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 5/20/19 6:30 PM
 *
 */

package com.kogicodes.pawametinderforcats.ui.uiUtils

import com.kogicodes.pawametinderforcats.ui.uiUtils.AppiException
import retrofit2.Call
import java.io.IOException


class FailureUtils {

    fun parseError(call: Call<*>?, t: Throwable?): AppiException {


        try {
            var ve: AppiException? = null
            if (call != null) {
                if (call.isCanceled) {
                    ve = AppiException("Canceled By User")
                } else {
                    if (t != null) {
                        if (t is IOException) {
                            ve = AppiException("Network failure. Please retry")

                        } else {
                            ve = AppiException(t.cause?.message)
                        }
                    }
                }
            } else {
                ve = AppiException(t?.message)
            }


            if (ve == null) {
                ve = AppiException(" Unknown Error ")
            }
            return ve
        } catch (ex: Exception) {
            return AppiException("Error Loading Data")

        }


    }
}