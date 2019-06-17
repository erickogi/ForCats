package com.kogicodes.pawametinderforcats.ui

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager.widget.ViewPager

import com.kogicodes.pawametinderforcats.R
import com.kogicodes.pawametinderforcats.adapters.CatAdapter
import com.kogicodes.pawametinderforcats.model.Cat
import com.kogicodes.pawametinderforcats.ui.uiUtils.*
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
