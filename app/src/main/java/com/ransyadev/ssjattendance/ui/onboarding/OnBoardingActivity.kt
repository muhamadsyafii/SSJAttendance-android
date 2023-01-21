package com.ransyadev.ssjattendance.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ransyadev.ssjattendance.data.implementation.database.SSJPreference
import com.ransyadev.ssjattendance.databinding.ActivityOnBoardingBinding
import com.ransyadev.ssjattendance.ui.onboarding.adapter.OnBoardingAdapter
import com.ransyadev.ssjattendance.utils.dummy.onBoardingList
import com.ransyadev.ssjattendance.utils.invisible
import com.ransyadev.ssjattendance.utils.openActivity
import com.ransyadev.ssjattendance.utils.showToast
import com.ransyadev.ssjattendance.utils.visible

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOnBoardingBinding
    private val adapter by lazy { OnBoardingAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        adapter.submitList(onBoardingList)
        binding.vpOnBoarding.adapter = adapter
        TabLayoutMediator(binding.tbOnBoarding, binding.vpOnBoarding, true) { _, _ -> }.attach()
        binding.tbOnBoarding.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
               if (tab?.position == adapter.itemCount -1){
                   binding.btnAction.visible()
                   binding.btnAction.setOnClickListener {
                       showToast("Cieee Cieee")
//                       SSJPreference.isFirstInstall = true
//                       openActivity()
//                       finish()
                   }
               }else{
                   binding.btnAction.invisible()
               }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }
}
