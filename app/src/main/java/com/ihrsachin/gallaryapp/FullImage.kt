package com.ihrsachin.gallaryapp


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class FullImage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_image)



        val bundle = intent.extras
        val links = bundle!!.getString("link")


        val imageView : ImageView = findViewById(R.id.imageView)

        try {
            Glide
                .with(imageView)
                .applyDefaultRequestOptions(
                    RequestOptions()
                        .placeholder(android.R.drawable.stat_notify_error)
                        .error(android.R.drawable.stat_notify_error)
                )
                .asBitmap()
                .load(links)
                .into(imageView)
        } catch (e: Exception){
            imageView.setImageResource(android.R.drawable.stat_notify_error)
        }

    }

}