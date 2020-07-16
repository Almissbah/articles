package com.almissbah.articles

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import com.almissbah.articles.ui.base.ArticlesActivity

class MainActivity : ArticlesActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
    }
}