package com.example.externalapiwithretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var userList : List<UserModel>
    lateinit var rcvMain : RecyclerView
    lateinit var prgrssBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcvMain = findViewById(R.id.rcvMain)
        rcvMain.layoutManager = LinearLayoutManager(this)
        prgrssBar = findViewById(R.id.prgrssBar)

        RetrofitInstance.apiInterface.getUsers().enqueue(object: Callback<List<UserModel>> {
            override fun onResponse(
                call: Call<List<UserModel>>,
                response: Response<List<UserModel>>) {
                userList = response.body()!!
               rcvMain.adapter = UserAdapter(this@MainActivity, userList)
                prgrssBar.visibility=View.INVISIBLE
               // for(i in 0 until userList.size-1)
               //Log.i("onFSuccess", " " + userList[i].gettitle())
            }

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                Log.e("onFailuree", " " + t.localizedMessage)
            }

        })

    }
}