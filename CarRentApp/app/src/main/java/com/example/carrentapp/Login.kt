package com.example.carrentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Login : AppCompatActivity() {
    private lateinit var signupBtn : Button
    private lateinit var goBtn : Button
    private lateinit var userNametxt : TextInputLayout
    private lateinit var passwordtxt : TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signupBtn = findViewById(R.id.login_new_user)
        goBtn=findViewById(R.id.login_go)

        userNametxt = findViewById(R.id.login_username)
        passwordtxt = findViewById(R.id.login_password)


        signupBtn.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        goBtn.setOnClickListener {
            loginUser()
        }
    }

    private fun validateUserName() : Boolean {
        val value = userNametxt.editText?.text.toString()
        if(value.isEmpty()) {
            userNametxt.error = "Field Cannot Be Empty"
        }
        else {
            userNametxt.error = null
            userNametxt.isErrorEnabled = false
            return true
        }
        return false
    }

    private fun validatePassword() : Boolean {
        val value = passwordtxt.editText?.text.toString()

        if(value.isEmpty()) {
            passwordtxt.error = "Field Cannot Be Empty"
        }
        else {
            passwordtxt.error = null
            passwordtxt.isErrorEnabled = false
            return true
        }
        return false
    }

    private fun loginUser() {
        if(!validateUserName() || !validatePassword())
            return
        else {
            isUserValid()
        }
    }

     private fun isUserValid() {
        val enteredUserName = userNametxt.editText?.text.toString().trim()
        val enteredPassword = passwordtxt.editText?.text.toString().trim()

        val databaseReference = FirebaseDatabase.getInstance().getReference("users")
        val queryCheckUser = databaseReference.orderByChild("username").equalTo(enteredUserName)
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    userNametxt.error = null
                    userNametxt.isErrorEnabled = false

                    val passwordDB = dataSnapshot.child(enteredUserName).child("password").getValue(String::class.java)
                    if(passwordDB.equals(enteredPassword)) {
                        passwordtxt.error = null
                        passwordtxt.isErrorEnabled = false

                        val nameDB = dataSnapshot.child(enteredUserName).child("name").getValue(String::class.java)
                        val emailDB = dataSnapshot.child(enteredUserName).child("email").getValue(String::class.java)
                        val phoneNoDB = dataSnapshot.child(enteredUserName).child("phoneno").getValue(String::class.java)

                        val intent = Intent(applicationContext, UserProfile::class.java)
                        intent.putExtra("name",nameDB)
                        intent.putExtra("username",enteredUserName)
                        intent.putExtra("email",emailDB)
                        intent.putExtra("phoneNo",phoneNoDB)
                        intent.putExtra("password",enteredPassword)
                        startActivity(intent)
                    }
                    else {
                        passwordtxt.error = " Wrong Password"
                        passwordtxt.requestFocus()
                    }
                }
                else {
                    userNametxt.error = " No Such User"
                    userNametxt.requestFocus()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }

        queryCheckUser.addListenerForSingleValueEvent(postListener)
    }
}