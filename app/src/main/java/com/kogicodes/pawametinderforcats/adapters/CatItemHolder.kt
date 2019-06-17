
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


import android.view.View

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kogicodes.pawametinderforcats.R
import com.kogicodes.pawametinderforcats.ui.uiUtils.OnRecyclerViewItemClick
import java.lang.ref.WeakReference


class CatItemHolder(itemView: View, lst: OnRecyclerViewItemClick) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
    override fun onClick(v: View?) {
        listenerWeakReference.get()?.onClickListener(adapterPosition)
    }

    private val listenerWeakReference: WeakReference<OnRecyclerViewItemClick> = WeakReference(lst)
    var itemVew: View

    init {
        this.itemVew = itemView
        this.itemVew.setOnClickListener(this)
    }

    var title: TextView = itemView.findViewById(R.id.title)
    var image: ImageView = itemView.findViewById(R.id.image)
    var fav1 = itemView.findViewById<View>(R.id.fav1) as ImageView
    var fav2 = itemView.findViewById<View>(R.id.fav2) as ImageView


}
