package com.mdelbel.android.listadapterexample.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mdelbel.android.listadapterexample.R

class MainScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_main)

        setUpView(savedInstanceState)
    }

    private fun setUpView(savedInstanceState: Bundle?) {
        when (savedInstanceState) {
            null -> supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, TasksListScreen(), TasksListScreen.TAG).commit()
        }
    }
}