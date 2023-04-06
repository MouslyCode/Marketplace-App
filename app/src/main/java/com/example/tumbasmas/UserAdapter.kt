package com.example.tumbasmas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
class UserAdapter(private val context: Context, private val UserList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.user_item, parent, false))


    override fun getItemCount(): Int {
        return UserList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(UserList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var txtUserName: TextView = itemView.findViewById(R.id.txtUserName)
        private var txtUserEmail: TextView = itemView.findViewById(R.id.txtUserEmail)
        private var txtUserAddress: TextView = itemView.findViewById(R.id.txtUserAddress)
        private var txtUserPhone: TextView = itemView.findViewById(R.id.txtUserTelp)

        fun bindItem(item: User) {
            txtUserName.text = item.name
            txtUserEmail.text = item.email
            txtUserAddress.text = item.alamat
            txtUserPhone.text = item.telp
        }
    }
}