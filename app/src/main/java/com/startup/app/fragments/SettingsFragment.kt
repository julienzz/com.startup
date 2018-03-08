package com.startup.app.fragments

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.startup.app.FabActivity
import com.startup.app.R

class SettingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_settings, container, false)

        val floatingActionButton = rootView.findViewById(R.id.fab) as FloatingActionButton
        floatingActionButton.setOnClickListener {
            val intent = Intent(activity, FabActivity::class.java)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())
            else
                startActivity(intent)
        }

        return rootView
    }
}
