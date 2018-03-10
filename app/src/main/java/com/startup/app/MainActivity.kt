package com.startup.app

import android.annotation.TargetApi
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.FrameLayout
import com.google.firebase.FirebaseApp
import com.startup.app.fragments.FavoriteTabFragment

import com.startup.app.fragments.FavoritesFragment
import com.startup.app.fragments.HomeFragment
import com.startup.app.fragments.SettingsFragment


class MainActivity : AppCompatActivity() {
    internal lateinit var drawerToggle: ActionBarDrawerToggle
    internal lateinit var drawerLayout: DrawerLayout
    internal lateinit var toolbar: Toolbar

    internal lateinit var fragmentManager: FragmentManager
    internal lateinit var navigationView: NavigationView
    internal lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        fragmentManager = supportFragmentManager

        setupView()
        if (savedInstanceState == null) showHome()
    }

    private fun setupView() {
        toolbar = (findViewById(R.id.toolbar) as Toolbar?)!!
        setSupportActionBar(toolbar)

        frameLayout = (findViewById(R.id.content_frame) as FrameLayout?)!!

        drawerLayout = (findViewById(R.id.drawer_layout) as DrawerLayout?)!!
        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawerLayout.addDrawerListener(drawerToggle)

        navigationView = (findViewById(R.id.navigation_view) as NavigationView?)!!
        navigationView.setNavigationItemSelectedListener { menuItem ->
            selectDrawerItem(menuItem)
            true
        }
    }

    private fun showHome() {
        selectDrawerItem(navigationView.menu.getItem(0))
        drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun selectDrawerItem(menuItem: MenuItem) {
        var specialToolbarBehaviour = false
        val fragmentClass: Class<*>

        when (menuItem.itemId) {
            R.id.drawer_home -> fragmentClass = HomeFragment::class.java
            R.id.drawer_favorites -> {
                fragmentClass = FavoritesFragment::class.java
                specialToolbarBehaviour = true
            }
            R.id.drawer_settings -> fragmentClass = SettingsFragment::class.java

            else -> fragmentClass = HomeFragment::class.java
        }

        try {
            val fragment = fragmentClass.newInstance() as Fragment
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        setToolbarElevation(specialToolbarBehaviour)
        menuItem.isChecked = true
        title = menuItem.title
        drawerLayout.closeDrawers()
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setToolbarElevation(specialToolbarBehaviour: Boolean) {
        if (specialToolbarBehaviour) {
            toolbar.elevation = 0.0f
            frameLayout.elevation = resources.getDimension(R.dimen.elevation_toolbar)
        } else {
            toolbar.elevation = resources.getDimension(R.dimen.elevation_toolbar)
            frameLayout.elevation = 0.0f
        }
    }

    fun showSnackbarMessage(v: View) {
        val et_snackbar = findViewById(R.id.et_snackbar) as EditText?
        val textInputLayout = findViewById(R.id.textInputLayout) as TextInputLayout?
        val view = findViewById(R.id.coordinator_layout)
        if (et_snackbar!!.text.toString().isEmpty()) {
            textInputLayout!!.error = getString(R.string.alert_text)
        } else {
            textInputLayout!!.isErrorEnabled = false
            et_snackbar.onEditorAction(EditorInfo.IME_ACTION_DONE)
            Snackbar.make(view!!, et_snackbar.text.toString(), Snackbar.LENGTH_LONG)
                    .setAction(resources.getString(android.R.string.ok)) {
                        // Do nothing
                    }
                    .show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        drawerToggle.syncState()
        super.onPostCreate(savedInstanceState)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }
}
