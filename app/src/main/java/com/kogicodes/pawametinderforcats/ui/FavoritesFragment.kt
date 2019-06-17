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


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.kogicodes.pawametinderforcats.R
import com.kogicodes.pawametinderforcats.adapters.CatAdapter
import com.kogicodes.pawametinderforcats.model.Cat
import com.kogicodes.pawametinderforcats.ui.uiUtils.OnRecyclerViewItemClick
import com.kogicodes.pawametinderforcats.ui.uiUtils.OnViewItemClick
import com.kogicodes.pawametinderforcats.ui.uiUtils.SimpleDialogModel
import com.kogicodes.pawametinderforcats.ui.uiUtils.ViewUtils
import kotlinx.android.synthetic.main.favorites_fragment.*
import java.util.*

class FavoritesFragment : Fragment() {

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        initCats()

        viewModel.getFavorites().observe(this,androidx.lifecycle.Observer {
            cats=it
            catAdapter?.updateList(cats)
        })



    }
    private var catAdapter: CatAdapter? = null
    private var cats:  List<Cat>?=LinkedList()
    private fun initCats() {

        catAdapter = context?.let {
            CatAdapter(it, cats, object : OnRecyclerViewItemClick {
                override fun onClickListener(position: Int) {


                    ViewUtils.simpleYesNoDialog(context!!,"Remove","Remove this cat from list of favorites",
                        SimpleDialogModel("Yes","No",null),object : OnViewItemClick{
                            override fun onPositiveClick() {

                                viewModel.setIsFavorite(cats!![position],false)
                            }

                            override fun onNegativeClick() {
                            }

                            override fun onNeutral() {
                            }

                        }
                    )

                }

                override fun onLongClickListener(position: Int) {

                }
            })
        }!!
        val spanCount = 1
        val layoutManager = GridLayoutManager(context, spanCount)
        recyclerview_favorites.layoutManager = layoutManager
        recyclerview_favorites.itemAnimator = DefaultItemAnimator()
        recyclerview_favorites.adapter = catAdapter
        catAdapter!!.notifyDataSetChanged()

    }

}
