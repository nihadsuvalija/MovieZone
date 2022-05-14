package com.example.moviezone.screen.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.findFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviezone.R
import com.example.moviezone.databinding.OptionItemBinding

class MoreSettingsAdapter: RecyclerView.Adapter<MoreSettingsAdapter.MoreViewHolder>() {

    private lateinit var binding: OptionItemBinding
    private lateinit var viewModel: ProfileViewModel

    private var options = listOf("Legal and Policies", "Help & Feedback", "About Us")
    private var icons = listOf(R.drawable.ic_shield, R.drawable.ic_question, R.drawable.ic_alert)

    class MoreViewHolder(binding: OptionItemBinding): RecyclerView.ViewHolder(binding.root) {
        val name = binding.tvOptionTitleOptionitem
        val icon = binding.ivOptionIconOptionitem
        val divider = binding.dividerOptionitem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoreViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.option_item, parent, false)
        viewModel = ViewModelProvider(parent.findFragment())[ProfileViewModel::class.java]

        return MoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoreSettingsAdapter.MoreViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(icons[position])
            .into(holder.icon)

        holder.name.text = options[position]

        if (position == options.size - 1) {
            holder.divider.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            if (position == 0) viewModel.privacyClick()
            if (position == 1) viewModel.helpAndFeedbackClick()
        }
    }

    override fun getItemCount(): Int {
        return options.size
    }
}