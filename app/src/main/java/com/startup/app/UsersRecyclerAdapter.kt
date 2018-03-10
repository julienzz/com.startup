package com.startup.app

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide

import de.hdodenhof.circleimageview.CircleImageView
/**
 * Created by julienchahoud on 3/10/18.
 */
class UsersRecyclerAdapter(private val context: Context, private val usersList: List<Users>) : RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user_name = usersList[position].name

        holder.user_name_view.text = user_name

        val user_image_view = holder.user_image_view
        Glide.with(context).load(usersList[position].image).into(user_image_view)

        val user_id = usersList[position].userId

      /*  holder.mView.setOnClickListener {
            val sendIntent = Intent(context, SendActivity::class.java)
            sendIntent.putExtra("user_id", user_id)
            sendIntent.putExtra("user_name", user_name)
            context.startActivity(sendIntent)
        }*/


    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    inner class ViewHolder(internal val mView: View) : RecyclerView.ViewHolder(mView) {

        internal val user_image_view: CircleImageView
        internal val user_name_view: TextView

        init {

            user_image_view = mView.findViewById(R.id.user_list_image) as CircleImageView
            user_name_view = mView.findViewById(R.id.user_list_name) as TextView

        }
    }

}