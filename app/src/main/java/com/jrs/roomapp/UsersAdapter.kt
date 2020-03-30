package com.jrs.roomapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.jrs.roomapp.database.user.User
import com.jrs.roomapp.database.user.UserViewModel
import kotlinx.android.synthetic.main.item_list.view.*

class UsersAdapter(var context: Context, var userViewModel: UserViewModel) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var users = emptyList<User>()// Cached copy of words

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = inflater.inflate(R.layout.item_list, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int = users.size


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindItems(users[position])
    }

    fun deleteUser(position: Int) {

        userViewModel.deleteUser(users[position])
        notifyItemRemoved(position)

    }
    fun getItem(position:Int) = users[position]

    fun editUser(user: User, position: Int) {
        var intent = Intent(context, NewEditUser::class.java)
        intent.putExtra("user", user)
        (context as AppCompatActivity).startActivity(intent)
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(item: User) {
            itemView.textViewId.text = layoutPosition.toString()
            itemView.txtName.text = item.name
            itemView.txtLastName.text = item.lastname
            itemView.txtPhone.text = item.phone

            itemView.editUser.setOnClickListener { editUser(item, layoutPosition) }
        }
    }

    fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

}