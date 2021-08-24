package com.ihrsachin.gallaryapp



import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<List<String>> {
    val baseUrl = "https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s"
    private var adapter : GallaryAdapter? = null
    var progressBar : ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = GallaryAdapter(this,ArrayList<String>())
        val gridView = findViewById<GridView>(R.id.gridView)

        gridView.adapter = adapter

        // Get a reference to the LoaderManager, in order to interact with loaders.
        // Get a reference to the LoaderManager, in order to interact with loaders.
        val loaderManager: LoaderManager = supportLoaderManager

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).

        val cm : ConnectivityManager = applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val nInfo : NetworkInfo? = cm.activeNetworkInfo
        if(nInfo != null && nInfo.isAvailable && nInfo.isConnected()) {
            loaderManager.initLoader(1, null, this)
        }
        else{
            progressBar = findViewById(R.id.progressBar)
            progressBar!!.visibility = View.GONE
            val starterText = findViewById<TextView>(R.id.StarterText)
            starterText.text = getString(R.string.no_network_connection)
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<List<String>> {
        progressBar = findViewById(R.id.progressBar)
        progressBar!!.visibility = View.VISIBLE
        return ImageLoader(this,baseUrl)
    }

    override fun onLoadFinished(loader: Loader<List<String>>, data: List<String>?) {
        adapter!!.clear()
        if(data != null && data.isNotEmpty()){
            adapter!!.addAll(data)
        }
        else{
            val starterText = findViewById<TextView>(R.id.StarterText)
            starterText.text = getString(R.string.no_photo_found)
        }
        progressBar = findViewById(R.id.progressBar)
        progressBar!!.visibility = View.GONE
    }

    override fun onLoaderReset(loader: Loader<List<String>>) {
        adapter!!.clear()
    }

}

