package com.jrs.roomapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.jrs.roomapp.database.user.User
import com.jrs.roomapp.database.user.UserViewModel
import kotlinx.android.synthetic.main.activity_new_edit_user.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class NewEditUser : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_edit_user)
        setSupportActionBar(toolbar)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        fab.setOnClickListener { saveUser() }

        user = intent.extras?.let {
            it.getParcelable("user") as User?
        } ?: run {
            null
        }

        user?.let { it ->
            editTextName.setText(it.name)
            editTextLastName.setText(it.lastname)
            editTextPhone.setText(it.phone)

        }

    }

    fun saveUser(){

        if (checkIsEmpty()) {
            return
        }
        user?.let {
            it.apply {
                this.id = user?.id
                this.name = editTextName.text.toString()
                this.lastname = editTextLastName.text.toString()
                this.phone = editTextPhone.text.toString()

            }
        } ?: run {
            user = User(
                null,
                editTextName.text.toString(),
                editTextLastName.text.toString(),
                editTextPhone.text.toString()
            )

        }
        userViewModel.insertOrUpdateUser(user!!)
        clearData();
        Snackbar
            .make(mainView,"Ok ,Usuario creado o editado correctamente",Snackbar.LENGTH_LONG)
            .setAction("Salir", View.OnClickListener { finish() })
            .show()

    }

    fun clearData() {
        editTextName.setText("")
        editTextLastName.setText("")
        editTextPhone.setText("")
    }

    @SuppressLint("ResourceType")
    fun checkIsEmpty(): Boolean {
        when ("") {
            editTextName.text.toString() -> {
                editTextName.setError("Error: Name can be empty")
                editTextName.requestFocus()
                return true
            }
            editTextLastName.text.toString() -> {
                editTextLastName.requestFocus()
                editTextLastName.error = "Error: Last Name can be empty"
                return true
            }
            editTextPhone.text.toString() -> {
                editTextPhone.requestFocus()
                editTextPhone.error = "Error: Phone can be empty"
                return true
            }
            else -> {
                editTextName.error = null
                editTextLastName.error = null
                editTextPhone.error = null
                return false
            }
        }

    }
}
