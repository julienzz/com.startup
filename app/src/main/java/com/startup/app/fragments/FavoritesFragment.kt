package com.startup.app.fragments

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.startup.app.R
import com.startup.app.adapters.TabsAdapter

class FavoritesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_favorites, container, false)

        val viewPager = rootView.findViewById(R.id.viewpager) as ViewPager
        val tabLayout = rootView.findViewById(R.id.tablayout) as TabLayout

        val tabsAdapter = TabsAdapter(childFragmentManager)
        tabsAdapter.addFragment(FavoriteTabFragment(1), "Favorite 1")
        tabsAdapter.addFragment(FavoriteTabFragment2(2), "Favorite 2")

        viewPager.adapter = tabsAdapter
        tabLayout.setupWithViewPager(viewPager)

        return rootView
    }
}
