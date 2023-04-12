package com.example.browser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(supportFragmentManager.findFragmentById(R.id.page_viewer) !is fragment_page_viewer){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.page_viewer, fragment_page_viewer())
                .commit()
        }
    }
}