package com.startup.app.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.DocumentChange

import com.startup.app.R
import com.startup.app.Users
import com.startup.app.UsersRecyclerAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.startup.app.MainActivity
import java.util.ArrayList

@SuppressLint("ValidFragment")
class FavoriteTabFragment : Fragment {

    //////////////
    private var mUsersListView: RecyclerView? = null

    private var usersList: MutableList<Users>? = null
    private var usersRecyclerAdapter: UsersRecyclerAdapter? = null

    private var mFirestore: FirebaseFirestore? = null
                            /////////////
    private var mPosition: Int = 0

    constructor(position: Int) {
        mPosition = position
    }

    constructor() {}


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        FirebaseApp.initializeApp(context)
        val rootView = inflater!!.inflate(R.layout.fragment_tab, container, false)

        /////////////

        mFirestore = FirebaseFirestore.getInstance()

        mUsersListView = rootView.findViewById(R.id.rV) as RecyclerView

        usersList = ArrayList()
        usersRecyclerAdapter = UsersRecyclerAdapter(container!!.context, usersList as ArrayList<Users>)

        mUsersListView!!.setHasFixedSize(true)
        mUsersListView!!.layoutManager = LinearLayoutManager(container.context)
        mUsersListView!!.adapter = usersRecyclerAdapter
                                    ////////////////////



        return rootView
    }

    override fun onStart() {
        super.onStart()

        usersList!!.clear()

        mFirestore!!.collection("Users").addSnapshotListener(activity) { documentSnapshots, e ->
            for (doc in documentSnapshots.documentChanges) {

                if (doc.type == DocumentChange.Type.ADDED) {

                    val user_id = doc.document.id

                    val users = doc.document.toObject<Users>(Users::class.java!!).withId<Users>(user_id)
                    usersList!!.add(users)

                    usersRecyclerAdapter!!.notifyDataSetChanged()

                }

            }
        }

    }
}
