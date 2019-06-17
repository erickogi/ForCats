
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

package com.kogicodes.pawametinderforcats.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kogicodes.pawametinderforcats.R
import com.kogicodes.pawametinderforcats.model.Cat
import com.kogicodes.pawametinderforcats.ui.uiUtils.OnRecyclerViewItemClick
import com.kogicodes.pawametinderforcats.ui.uiUtils.ViewUtils

class CatAdapter(
    private var context: Context,
    private var modelList: List<Cat>?,
    private val lst: OnRecyclerViewItemClick
) : RecyclerView.Adapter<CatItemHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatItemHolder {
        val itemView: View? =
            LayoutInflater.from(parent.context).inflate(R.layout.cat_item, parent, false)



        return CatItemHolder(itemView!!, lst)
    }


    override fun onBindViewHolder(holder: CatItemHolder, position: Int) {

        val model = modelList!![position]
        holder.title.text = model.actionState?.name

        ViewUtils.setImage(context, model.url!!, holder.image)


        if (model.isFavorite != null) {
            if (model.isFavorite!!) {
                holder.fav2.visibility = View.VISIBLE
                holder.fav1.visibility = View.GONE
            } else {
                holder.fav1.visibility = View.VISIBLE
                holder.fav2.visibility = View.GONE
            }
        }

    }


    override fun getItemCount(): Int {
        return if (null != modelList) modelList!!.size else 0
    }

    fun updateList(modelLists: List<Cat>?) {

        this.modelList = modelLists
        notifyDataSetChanged()

    }


}