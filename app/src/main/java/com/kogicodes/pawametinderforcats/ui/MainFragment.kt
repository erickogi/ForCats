package com.kogicodes.pawametinderforcats.ui

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.dev.cabinzz.models.custom.Status

import com.kogicodes.pawametinderforcats.R
import com.kogicodes.pawametinderforcats.ui.uiUtils.CardFragmentPagerAdapter
import com.kogicodes.pawametinderforcats.ui.uiUtils.ShadowTransformer
import com.kogicodes.pawametinderforcats.ui.uiUtils.ViewUtils

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)



        viewModel.listings()
        viewModel.observeListing().observe(this,androidx.lifecycle.Observer {
            ViewUtils.setStatus(
                activity,
                view,
                it.status,
                it.message,
                false,
                ViewUtils.ErrorViewTypes.TOAST,
                it.exception
            )

            if(it.status==Status.SUCCESS){
                val viewPager = view?.findViewById(R.id.viewPager) as ViewPager
                val pagerAdapter =CardFragmentPagerAdapter(childFragmentManager, dpToPixels(2, context!!), it?.data,it.data?.size)

                val fragmentCardShadowTransformer = ShadowTransformer(viewPager, pagerAdapter)
                fragmentCardShadowTransformer.enableScaling(true)

                viewPager.adapter = pagerAdapter
                viewPager.setPageTransformer(false, fragmentCardShadowTransformer)
                viewPager.offscreenPageLimit = 4

            }

        })

    }
    fun dpToPixels(dp: Int, context: Context): Float {
        return dp * context.resources.displayMetrics.density
    }
}
