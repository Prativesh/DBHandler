package com.example.carrentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class UserProfile : AppCompatActivity() {
    lateinit var fullNameField : TextView
    lateinit var usernameField : TextView
    lateinit var fullNameTxt : TextInputLayout
    lateinit var emailTxt : TextInputLayout
    lateinit var phoneNoTxt : TextInputLayout
    lateinit var passwordTxt : TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        fullNameField = findViewById(R.id.profile_fullname)
        usernameField = findViewById(R.id.profile_username)
        fullNameTxt = findViewById(R.id.profile_edit_fullname)
        emailTxt = findViewById(R.id.profile_edit_email)
        passwordTxt = findViewById(R.id.profile_edit_password)
        phoneNoTxt = findViewById(R.id.profile_edit_phoneNo)

        showUserData()
    }

    private fun showUserData() {
        val intent = intent
        val name = intent.getStringExtra("name")
        val username = intent.getStringExtra("username")
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val phoneNo = intent.getStringExtra("phoneNo")

        fullNameField.text = name
        usernameField.text = username

        emailTxt.editText?.setText(email)
        passwordTxt.editText?.setText(password)
        phoneNoTxt.editText?.setText(phoneNo)
        fullNameTxt.editText?.setText(name)
    }
}