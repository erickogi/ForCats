
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
import kotlinx.android.synthetic.main.cat_card.*

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