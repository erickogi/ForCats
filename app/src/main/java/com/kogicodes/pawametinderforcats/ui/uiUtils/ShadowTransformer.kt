package com.kogicodes.pawametinderforcats.ui.uiUtils

import android.view.View
import androidx.viewpager.widget.ViewPager


class ShadowTransformer(private val viewPager: ViewPager, private val cardAdapter: CardAdapter) :
    ViewPager.OnPageChangeListener, ViewPager.PageTransformer {
    private var lastOffset: Float = 0.toFloat()
    private var scalingEnabled: Boolean = false

    init {
        viewPager.addOnPageChangeListener(this)
    }


    fun enableScaling(enable: Boolean) {
        if (scalingEnabled && !enable) {
            val currentCard = cardAdapter.getCardViewAt(viewPager.currentItem)
            if (currentCard != null) {
                currentCard.animate().scaleY(1f)
                currentCard.animate().scaleX(1f)
            }
        } else if (!scalingEnabled && enable) {
            val currentCard = cardAdapter.getCardViewAt(viewPager.currentItem)
            if (currentCard != null) {
                currentCard.animate().scaleY(1.1f)
                currentCard.animate().scaleX(1.1f)
            }
        }
        scalingEnabled = enable
    }

    override fun transformPage(page: View, position: Float) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        val realCurrentPosition: Int
        val nextPosition: Int
        val baseElevation = cardAdapter.getBaseElevation()
        val realOffset: Float
        val goingLeft = lastOffset > positionOffset
       if (goingLeft) {
            realCurrentPosition = position + 1
            nextPosition = position
            realOffset = 1 - positionOffset
        } else {
            nextPosition = position + 1
            realCurrentPosition = position
            realOffset = positionOffset
        }
        if (nextPosition > cardAdapter.getCount() - 1 || realCurrentPosition > cardAdapter.getCount() - 1) {
            return
        }

        val currentCard = cardAdapter.getCardViewAt(realCurrentPosition)
       if (currentCard != null) {
            if (scalingEnabled) {
                currentCard.scaleX = (1 + 0.1 * (1 - realOffset)).toFloat()
                currentCard.scaleY = (1 + 0.1 * (1 - realOffset)).toFloat()
            }
            currentCard.cardElevation = baseElevation + (baseElevation
                    * (CardAdapter.MAX_ELEVATION_FACTOR - 1).toFloat() * (1 - realOffset))
        }

        val nextCard = cardAdapter.getCardViewAt(nextPosition)
        if (nextCard != null) {
            if (scalingEnabled) {
                nextCard.scaleX = (1 + 0.1 * realOffset).toFloat()
                nextCard.scaleY = (1 + 0.1 * realOffset).toFloat()
            }
            nextCard.cardElevation = baseElevation + (baseElevation
                    * (CardAdapter.MAX_ELEVATION_FACTOR - 1).toFloat() * realOffset)
        }

        lastOffset = positionOffset
    }

    override fun onPageSelected(position: Int) {

    }

    override fun onPageScrollStateChanged(state: Int) {

    }
}
