package com.almissbah.articles

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.almissbah.articles.ui.base.ArticlesActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ArticlesActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.nav_splash
            ) {
                toolbar.visibility = View.GONE
            } else {
                //    supportActionBar!!.show()
                toolbar.visibility = View.VISIBLE
            }
        }
    }

    fun updateActionBarTitle(title: String) {
        toolbar.title = title
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}