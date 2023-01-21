package com.ransyadev.ssjattendance.ui.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.ktx.Firebase
import com.ransyadev.ssjattendance.R
import com.ransyadev.ssjattendance.data.implementation.database.SSJPreference
import com.ransyadev.ssjattendance.databinding.ActivityMainBinding
import com.ransyadev.ssjattendance.ui.home.HomeActivity
import com.ransyadev.ssjattendance.utils.openActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

    }

    private fun initView() {
        /*val isFirstInstall = SSJPreference.isFirstInstall
        val isLogin = SSJPreference.isLogin

        Handler(Looper.getMainLooper()).postDelayed({
            if (isFirstInstall){
                if (isLogin){
                    openActivity(HomeActivity::class.java)
                    finish()
                }else{
                    //TODO : open login screen
                    finish()
                }
            }else{
                //TODO : open on boarding screen
                finish()
            }
        }, 1500L)*/
    }
}
