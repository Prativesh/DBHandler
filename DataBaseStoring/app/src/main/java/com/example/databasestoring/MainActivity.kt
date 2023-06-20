package com.example.databasestoring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enterNameTextView = findViewById<TextView>(R.id.enterName)
        val enterAgeTextView = findViewById<TextView>(R.id.enterAge)
        val addBtn = findViewById<Button>(R.id.Add)
        val showBtn = findViewById<Button>(R.id.Print)
        val deleteBtn = findViewById<Button>(R.id.Delete)
        val showNameTextView = findViewById<TextView>(R.id.Name)
        val showAgeTextView = findViewById<TextView>(R.id.Age)

        addBtn.setOnClickListener() {
            val db = DBHandler(this, null)
            val name = enterNameTextView.text.toString()
            val age = enterAgeTextView.text.toString()

            if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age)) {
                db.addName(name, age)
                Toast.makeText(this, name + " Added To DataBase", Toast.LENGTH_LONG).show()

                enterAgeTextView.setText("")
                enterNameTextView.setText("")
            }
        }

        showBtn.setOnClickListener() {
            val db = DBHandler(this, null)
            val name = enterNameTextView.text.toString()
            val cursor = if(TextUtils.isEmpty(name)) db.getName() else db.getName(name)

                cursor?.moveToFirst()
                var colIndx = cursor?.getColumnIndex(DBHandler.NAME_COl)
                showNameTextView.append(cursor?.getString(colIndx!!) + "\n")
                colIndx = cursor?.getColumnIndex(DBHandler.AGE_COL)
                showAgeTextView.append(cursor?.getString(colIndx!!) + "\n")

                while (cursor!!.moveToNext()) {
                    var colIndx = cursor?.getColumnIndex(DBHandler.NAME_COl)
                    showNameTextView.append(cursor?.getString(colIndx!!) + "\n")
                    colIndx = cursor?.getColumnIndex(DBHandler.AGE_COL)
                    showAgeTextView.append(cursor?.getString(colIndx!!) + "\n")
                }
                cursor.close()

        }

        deleteBtn.setOnClickListener() {
            val db = DBHandler(this, null)
            val name = enterNameTextView.text.toString()
            if(!TextUtils.isEmpty(name))
                db.deleteName(name)
            Toast.makeText(this, name + " Deleted From DataBase", Toast.LENGTH_LONG).show()
            enterNameTextView.setText("")
        }
    }
}