/*
 * *
 *  * Created by Kogi Eric  on 5/17/19 4:11 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 6/14/19 4:09 PM
 *
 */

import com.kogicodes.pawametinderforcats.model.Cat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author kogi
 */
interface EndPoints {



    /**GET CATS  **/
    @GET("images/search")
    fun getCats(@Query("page")  pageIndex: Int,@Query("limit")  limit: Int): Call<List<Cat>>


}
