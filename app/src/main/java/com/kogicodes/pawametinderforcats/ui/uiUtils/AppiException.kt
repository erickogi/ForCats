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

class AppiException : Exception {
    var errorType: String? = null
    var errorMessage: String? = null
    var errors: List<String>? = null

    constructor (errorMessage: String?) : super(errorMessage) {
        this.errorMessage = errorMessage

    }


    constructor(errorType: String?, errorMessage: String?, errors: List<String>?) : super(errorMessage) {

        this.errorType = errorType
        this.errorMessage = errorMessage
        this.errors = errors
    }


}