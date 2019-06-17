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

package com.kogicodes.pawametinderforcats.ui.uiUtils


import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kogicodes.pawametinderforcats.model.Cat
import com.kogicodes.pawametinderforcats.ui.CardFragment
import java.util.*


class CardFragmentPagerAdapter(
    fm: FragmentManager,
    private val baseElevation: Float,
    private val cats: List<Cat>?,
    size: Int?
) : FragmentStatePagerAdapter(fm), CardAdapter {
    private val fragments: MutableList<CardFragment>
    var noOfCardsF:Int?=0
    init {
        this.fragments = ArrayList()
        this.noOfCardsF = size


        if (noOfCardsF != null){
            for (i in 0 until noOfCardsF!!) {
                addCardFragment(CardFragment.getInstance(i,cats!![i],noOfCardsF!!) as CardFragment)
            }
    }
    }

    override fun getBaseElevation(): Float {
        return baseElevation
    }

    override fun getCardViewAt(position: Int): CardView? {

        return fragments[position].getCarrd()
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {

        return fragments[position]

    }



    override fun instantiateItem(container: ViewGroup, position: Int): CardFragment {
        val fragment = super.instantiateItem(container, position)
        fragments[position] = fragment as CardFragment
        return fragment
    }

    private fun addCardFragment(fragment: CardFragment) {
        fragments.add(fragment)

    }

}
