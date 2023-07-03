package com.example.carrentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {
    private lateinit var loginBtn : Button
    private lateinit var nametxt : TextInputLayout
    private lateinit var usernametxt : TextInputLayout
    private lateinit var emailtxt : TextInputLayout
    private lateinit var phonenotxt : TextInputLayout
    private lateinit var passwordtxt : TextInputLayout
    private lateinit var goBtn : Button
    private lateinit var rootNode : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        nametxt = findViewById(R.id.signup_fullname)
        usernametxt = findViewById(R.id.signup_username)
        emailtxt = findViewById(R.id.signup_email)
        phonenotxt = findViewById(R.id.signup_phoneno)
        passwordtxt = findViewById(R.id.signup_password)

        loginBtn = findViewById(R.id.signup_login)
        loginBtn.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        goBtn = findViewById(R.id.signup_go)
        goBtn.setOnClickListener {
            registerUser()
        }
    }

    private fun validateName() : Boolean {
        val value = nametxt.editText?.text.toString()
        if(value.isEmpty()) {
            nametxt.error = "Field Cannot Be Empty"
            return false
        }
        else {
            nametxt.error = null
            nametxt.isErrorEnabled = false
            return true
        }
    }

    private fun validateUsername() : Boolean {
        val value = usernametxt.editText?.text.toString()
        val whiteSpaces = Regex("\\s")
        if(value.isEmpty()) {
            usernametxt.error = "Field Cannot Be Empty"
            return false
        }
        else if(value.length>=15) {
            usernametxt.error = "Username Too Long"
            return false
        }
        else if (value.contains(whiteSpaces)) {
            usernametxt.error = "No Whitespaces Allowed"
            return false
        }
        else {
            usernametxt.error = null
            usernametxt.isErrorEnabled = false
            return true
        }
    }

    private fun validateEmail() : Boolean {
        val value = emailtxt.editText?.text.toString()
        val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        if(value.isEmpty()) {
            emailtxt.error = "Field Cannot Be Empty"
            return false
        }
        else if(!value.matches(emailPattern)) {
            emailtxt.error = "Invalid Email"
            return false
        }
        else {
            emailtxt.error = null
            emailtxt.isErrorEnabled = false
            return true
        }
    }

    private fun validatePhoneNo() : Boolean {
        val value = phonenotxt.editText?.text.toString()
        val phonePattern = Regex("[0-9]+")
        if(value.isEmpty()) {
            phonenotxt.error = "Field Cannot Be Empty"
            return false
        }
        else if(!value.matches(phonePattern)) {
            phonenotxt.error = "Invalid Phone No"
            return false
        }
        else {
            phonenotxt.error = null
            phonenotxt.isErrorEnabled = false
            return true
        }
    }

    private fun validatePassword() : Boolean {
        val value = passwordtxt.editText?.text.toString()
        val passwordPattern =  Regex("^" +
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$")
        if(value.isEmpty()) {
            passwordtxt.error = "Field Cannot Be Empty"
            return false
        }
        if(!value.matches(passwordPattern)) {
            passwordtxt.error = "Password Invalid"
            return false
        }
        else {
            passwordtxt.setError(null)
            passwordtxt.isErrorEnabled = false
            return true
        }
    }

    private fun registerUser() {
        if(!validateName() || !validateUsername() || !validateEmail() || !validatePhoneNo() || !validatePassword())
            return
        val name = nametxt.editText?.text.toString()
        val username = usernametxt.editText?.text.toString()
        val email = emailtxt.editText?.text.toString()
        val phoneNo = phonenotxt.editText?.text.toString()
        val password = passwordtxt.editText?.text.toString()

        val userHelper = UserHelper(name, username, email, phoneNo, password)
        rootNode = Firebase.database
        reference = rootNode.getReference("users")
        reference.child(username).setValue(userHelper)

        nametxt.editText?.setText("")
        usernametxt.editText?.setText("")
        emailtxt.editText?.setText("")
        phonenotxt.editText?.setText("")
        passwordtxt.editText?.setText("")

        Toast.makeText(this, " Registration Successful", Toast.LENGTH_LONG).show()
    }
}