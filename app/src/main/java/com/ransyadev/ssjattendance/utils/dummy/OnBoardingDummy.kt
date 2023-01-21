package com.ransyadev.ssjattendance.utils.dummy

import com.ransyadev.ssjattendance.R
import com.ransyadev.ssjattendance.SSJApp
import com.ransyadev.ssjattendance.data.api.OnBoarding

val onBoardingList = listOf(
    OnBoarding(
        id = 1,
        image = R.drawable.ic_onboarding_one,
        title = SSJApp.context.resources.getString(R.string.txt_title_boarding_one),
        desc = SSJApp.context.resources.getString(R.string.txt_title_boarding_one_desc)
    ),
    OnBoarding(
        id = 2,
        image = R.drawable.ic_onboarding_two,
        title = SSJApp.context.resources.getString(R.string.txt_title_boarding_two),
        desc = SSJApp.context.resources.getString(R.string.txt_title_boarding_two_desc)
    )
)
