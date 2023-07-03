package com.example.carrentapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

const val  DELAY_DURATION :Long = 2000

class MainActivity : AppCompatActivity() {

    lateinit var topAnimation : Animation
    lateinit var bottomAnimation: Animation
    lateinit var mainText : TextView
    lateinit var secondaryText : TextView
    lateinit var imageView : ImageView
    lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        mainText = findViewById(R.id.main_text)
        secondaryText = findViewById(R.id.secondary_text)
        imageView = findViewById(R.id.imageView)

        imageView.animation = topAnimation
        secondaryText.animation = bottomAnimation
        mainText.animation = bottomAnimation

        handler = Handler()
        handler.postDelayed( {
            val intent = Intent(this, Login::class.java)
            //val options = ActivityOptions.makeSceneTransitionAnimation(this, android.util.Pair(imageView, "logo_image"), android.util.Pair(mainText, "logo_text"))
            //startActivity(intent, options.toBundle())
            startActivity(intent)
            finish()
        }, DELAY_DURATION)
    }
}