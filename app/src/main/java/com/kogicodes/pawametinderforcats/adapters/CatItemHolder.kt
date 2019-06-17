
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
