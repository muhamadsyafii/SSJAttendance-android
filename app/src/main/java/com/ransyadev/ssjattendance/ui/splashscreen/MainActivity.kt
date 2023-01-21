package com.ransyadev.ssjattendance.ui.splashscreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.google.firebase.messaging.FirebaseMessaging
import com.ransyadev.ssjattendance.data.implementation.database.SSJPreference
import com.ransyadev.ssjattendance.databinding.ActivityMainBinding
import com.ransyadev.ssjattendance.ui.home.HomeActivity
import com.ransyadev.ssjattendance.ui.onboarding.OnBoardingActivity
import com.ransyadev.ssjattendance.utils.firebase.MyFirebaseMessagingService
import com.ransyadev.ssjattendance.utils.firebase.NotificationListener
import com.ransyadev.ssjattendance.utils.openActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Firebase.initialize(this)
        initView()

    }

    private fun initView() {
        setNewFirebaseToken()
        val isFirstInstall = SSJPreference.isFirstInstall
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
                openActivity(OnBoardingActivity::class.java)
                finish()
            }
        }, 1500L)
    }

    private fun setNewFirebaseToken() {
        var newToken = ""
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                newToken = token
            }

            MyFirebaseMessagingService().sendNotifications(newToken, object :
                NotificationListener {
                override fun onNewToken(token: String) {
                    SSJPreference.tokenFCM = token
                }
            })
        }
    }
}
