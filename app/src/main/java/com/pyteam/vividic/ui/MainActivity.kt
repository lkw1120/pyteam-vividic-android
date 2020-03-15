package com.pyteam.vividic.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pyteam.vividic.R
import androidx.databinding.DataBindingUtil.setContentView
import com.pyteam.vividic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)

    }

}
