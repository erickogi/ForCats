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

package com.kogicodes.pawametinderforcats.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kogicodes.pawametinderforcats.R
import com.kogicodes.pawametinderforcats.model.Cat
import com.kogicodes.pawametinderforcats.ui.uiUtils.ViewUtils
import kotlinx.android.synthetic.main.cat_card.*


class CardFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    @SuppressLint("DefaultLocale")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cat_card, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        initView()

    }

    fun initView(){
        val cat=arguments?.getSerializable("data")  as Cat
        ViewUtils.setImage(context!!,cat.url!!, image)

        viewModel.getCat(catId =cat.id!!).observe(this, Observer {



            if(it!=null&&it.url!=null) {

                val myCat=it
                if (myCat.isFavorite!!) {
                    fav2.visibility = View.VISIBLE
                    fav1.visibility = View.GONE
                } else {
                    fav1.visibility = View.VISIBLE
                    fav2.visibility = View.GONE
                }


                fav1.setOnClickListener {
                    viewModel.setIsFavorite(myCat, true)

                }

                fav2.setOnClickListener {

                    viewModel.setIsFavorite(myCat, false)
                }
                btn_like.setOnClickListener {

                    viewModel.like(myCat)

                }

                btn_dislike.setOnClickListener {

                    viewModel.dislike(myCat)

                }

                info.setOnClickListener {


                }
                title.text = myCat.actionState?.name

            }

        })

    }






    fun getCarrd(): CardView? {

        return cardView
    }

    companion object {

        fun getInstance(position: Int, cat: Cat, lastOne: Int): Fragment {
            val f = CardFragment()
            val args = Bundle()

            args.putInt("last", lastOne)
            args.putSerializable("data", cat)
            args.putInt("position", position)
            f.arguments = args

            return f
        }
    }

}
