package com.startup.app.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.startup.app.R

@SuppressLint("ValidFragment")
class FavoriteTabFragment : Fragment {
    private var mPosition: Int = 0

    constructor(position: Int) {
        mPosition = position
    }

    constructor() {}

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.fragment_tab, container, false)

        val textView = rootView.findViewById(R.id.fav_number) as TextView
        textView.text = "Favorite " + mPosition

        return rootView
    }
}
