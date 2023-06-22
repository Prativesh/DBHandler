package com.example.externalapiwithretrofit

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(mainActivity : MainActivity, userList : List<UserModel>) : RecyclerView.Adapter<UserAdapter.UserHolder>() {

    var userList : List<UserModel>
    var mainActivity : MainActivity

    init {
        this.userList = userList
        this.mainActivity =mainActivity
    }
    class UserHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    var user_txt :TextView
    init {
        user_txt = itemView.findViewById(R.id.user_txt)
    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(LayoutInflater.from(this.mainActivity).inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.user_txt.setText(userList[position].title)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}