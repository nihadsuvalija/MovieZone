package com.example.moviezone.screen.profile

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviezone.R
import com.example.moviezone.databinding.OptionItemBinding
import com.example.moviezone.utils.Const

class AccountSettingsAdapter: RecyclerView.Adapter<AccountSettingsAdapter.SettingsViewHolder>() {

    private lateinit var binding: OptionItemBinding
    private var viewModelInteractor: ProfileViewModelInteractor? = null

    private var options = listOf("Change Password", "Change Profile Picture")
    private var icons = listOf(R.drawable.ic_padlock_1, R.drawable.ic_person)

    fun setViewModelInteractor(viewModelInteractor: ProfileViewModelInteractor) {
        this.viewModelInteractor = viewModelInteractor
    }

    class SettingsViewHolder(binding: OptionItemBinding): RecyclerView.ViewHolder(binding.root) {
        val icon = binding.ivOptionIconOptionitem
        val name = binding.tvOptionTitleOptionitem
        val divider = binding.dividerOptionitem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.option_item, parent, false)

        return SettingsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(icons[position])
            .into(holder.icon)

        holder.name.text = options[position]

        if (position == options.size - 1) {
            holder.divider.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            with (holder.name) {

                if (text == Const.CHANGE_PROFILE_PHOTO) {
                    viewModelInteractor?.changeProfilePhotoClick()
                }

                if (text == Const.CHANGE_PASSWORD) {
                    viewModelInteractor?.changePasswordClick()
                }

            }

        }
    }

    override fun getItemCount(): Int {
        return options.size
    }
}