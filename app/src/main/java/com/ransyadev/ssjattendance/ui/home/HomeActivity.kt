package com.ransyadev.ssjattendance.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ransyadev.ssjattendance.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

    }
}
