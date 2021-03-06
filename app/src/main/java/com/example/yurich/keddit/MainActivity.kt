package com.example.yurich.keddit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
        setContentView(R.layout.activity_main)

        setupToolbar()

        if (savedInstanceState == null) {
            changeFragment(NewsFragment())
        }

    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)

        toolbar.setLogo(R.drawable.logo_reddit)

        toolbar.title = getString(R.string.title)
        toolbar.setTitleTextColor(R.color.title_color)
        toolbar.subtitle = getString(R.string.subtitle)
        toolbar.setSubtitleTextColor(R.color.subtitle_color)

    }

    @SuppressWarnings("WeakerAccess")
    fun changeFragment(f: Fragment, cleanStack: Boolean = false) {
        val ft = supportFragmentManager.beginTransaction()

        if (cleanStack) {
            clearBackStack()
        }

        ft.setCustomAnimations(
                R.anim.abc_fade_in
                , R.anim.abc_fade_out
                , R.anim.abc_popup_enter
                , R.anim.abc_popup_exit
        )
        ft.replace(R.id.activity_base_fragment, f)
        ft.addToBackStack(null)
        ft.commit()
    }

    fun clearBackStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    /**
     * Finish activity when reaching the last fragment.
     */
    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}
