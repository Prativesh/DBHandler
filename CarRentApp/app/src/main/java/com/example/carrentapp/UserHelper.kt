package com.example.carrentapp

class UserHelper(name: String, username : String, email : String, phoneno : String, password : String) {
    var name : String
    var username : String
    var email : String
    var phoneno : String
    var password : String

    init {
        this.name = name
        this.email = email
        this.username = username
        this.phoneno = phoneno
        this.password = password
    }
}