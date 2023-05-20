package com.example.twotaskandroid.presentationLayer

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.twotaskandroid.R

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val imageUrl = intent.getStringExtra("cat_image_url")
        val imageView = findViewById<ImageView>(R.id.image_view)

        Glide.with(this)
            .load(imageUrl)
            .into(imageView)
    }
}