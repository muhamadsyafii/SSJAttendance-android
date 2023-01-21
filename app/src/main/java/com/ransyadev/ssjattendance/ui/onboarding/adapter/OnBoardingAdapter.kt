package com.ransyadev.ssjattendance.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ransyadev.ssjattendance.data.api.OnBoarding
import com.ransyadev.ssjattendance.databinding.ItemOnBoardingBinding

class OnBoardingAdapter : ListAdapter<OnBoarding, OnBoardingAdapter.ViewHolder>(OnBoardingDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemOnBoardingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class ViewHolder (private val binding: ItemOnBoardingBinding) : RecyclerView.ViewHolder(binding.root){
        fun bindData(data : OnBoarding){
            binding.run {
                ivOnBoarding.setImageResource(data.image)
                tvTitleOnBoarding.text = data.title
                tvDescOnBoarding.text = data.desc
            }
        }
    }

    class OnBoardingDiffCallBack : DiffUtil.ItemCallback<OnBoarding>(){
        override fun areItemsTheSame(oldItem: OnBoarding, newItem: OnBoarding): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: OnBoarding, newItem: OnBoarding): Boolean {
            return oldItem == newItem
        }
    }

}
