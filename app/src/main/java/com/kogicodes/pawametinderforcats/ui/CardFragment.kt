package com.kogicodes.pawametinderforcats.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.kogicodes.pawametinderforcats.R
import com.kogicodes.pawametinderforcats.model.Cat
import com.kogicodes.pawametinderforcats.ui.uiUtils.CardAdapter
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



            Log.d("dvdssf",Gson().toJson(it))
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
