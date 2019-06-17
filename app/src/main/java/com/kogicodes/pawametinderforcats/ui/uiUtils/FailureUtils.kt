

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

package com.kogicodes.pawametinderforcats.ui.uiUtils

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