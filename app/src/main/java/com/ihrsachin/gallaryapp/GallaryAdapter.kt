package com.ihrsachin.gallaryapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class GallaryAdapter(context: Context, list: List<String>):  ArrayAdapter<String>(context,0, list ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var currentView = convertView
        val link : String? = getItem(position)
        if(currentView == null){
           currentView =  LayoutInflater.from(context).inflate(R.layout.grid_item,parent,false)
        }

        val imageView : ImageView = currentView!!.findViewById(R.id.grid_item_imageview)

        try {
            Glide
                .with(imageView)
                .applyDefaultRequestOptions(
                    RequestOptions()
                        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                        .error(android.R.drawable.stat_notify_error)
                )
                .asBitmap()
                .load(link)
                .into(imageView)


            imageView.setOnClickListener{
                val intent = Intent(context, FullImage::class.java)
                intent.putExtra("link", link)
                startActivity(context,intent,null)
            }
        } catch (e: Exception) {
            imageView.setImageResource(android.R.drawable.stat_notify_error)
        }

        return currentView
    }

}