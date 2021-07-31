package com.ihrsachin.gallaryapp



import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // var BASE_URL : String = Network().getText("")
        val url = "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s"
        var s : String = ""
        try{
            val website = URL(url)
            val connection = website.openConnection()
            val `in` = BufferedReader(
                InputStreamReader(
                    connection.getInputStream()))
            val response = StringBuilder()
            var inputLine: String?
            while (true){
                inputLine = `in`.readLine()
                if(inputLine.isEmpty()) break
                else response.append(inputLine)
            }
            `in`.close()
            s = response.substring(0,response.length-1)
        } catch (e: Exception) {
        }
        val jsonObject : JSONObject = JSONObject(s)



        val photos : JSONArray = jsonObject.getJSONObject("photos").getJSONArray("photo")

        val list = ArrayList<String>()

        for(i in 1..photos.length()){
           list.add(photos.getJSONObject(i-1).getString("url_s"))
        }
        val gridView : GridView = findViewById(R.id.gridView)

        val adapter = GallaryAdapter(this,list)

        gridView.adapter = adapter

    }

}

