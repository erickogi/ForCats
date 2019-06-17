package com.kogicodes.pawametinderforcats.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kogicodes.pawametinderforcats.R
import com.kogicodes.pawametinderforcats.ui.uiUtils.ViewUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewUtils().makeFullScreen(this)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commitNow()
        setHomeSelectedNav()
    }

    private fun popOutFragments() {
        val fragmentManager = supportFragmentManager
        for (i in 0 until fragmentManager.backStackEntryCount) {
            fragmentManager.popBackStack()
        }
    }

    fun setFavSelectedNav() {

        if (navigation.selectedItemId != R.id.navigation_favorites) {
            navigation.selectedItemId = R.id.navigation_favorites
        }
    }

    fun setProfileSelectedNav() {
        if (navigation.selectedItemId != R.id.navigation_upload) {
            navigation.selectedItemId = R.id.navigation_upload
        }
    }

    fun setHomeSelectedNav() {

        if (navigation.selectedItemId != R.id.navigation_home) {
            navigation.selectedItemId = R.id.navigation_home
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_upload -> {

                supportFragmentManager.beginTransaction().replace(R.id.container, UploadFragment.newInstance())
                    .addToBackStack("upload").commit()

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {

                supportFragmentManager.beginTransaction().replace(R.id.container, FavoritesFragment.newInstance())
                    .addToBackStack("Fav").commit()

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onBackPressed() {
        val f = supportFragmentManager.findFragmentById(R.id.container)
        if (f is MainFragment) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Exit")
            builder.setMessage("Are You Sure?")


            builder.setPositiveButton("Yes") { dialog, _ ->
                dialog.dismiss()
                super.onBackPressed()
            }

            builder.setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
            val alert = builder.create()
            alert.show()            //additional code
        } else {
            popOutFragments()
            navigation.selectedItemId = R.id.navigation_home
        }
    }

}
