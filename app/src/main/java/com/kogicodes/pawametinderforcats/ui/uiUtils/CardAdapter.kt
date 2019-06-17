package com.kogicodes.pawametinderforcats.ui.uiUtils


import androidx.cardview.widget.CardView

interface CardAdapter {

     fun getCount(): Int

    fun  getBaseElevation(): Float

    fun getCardViewAt(position: Int): CardView?

    companion object {

        const val MAX_ELEVATION_FACTOR = 8
    }
}
