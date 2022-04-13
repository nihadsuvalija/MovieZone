package com.example.moviezone.screen.moviedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviezone.R
import com.example.moviezone.databinding.PersonCreditsBinding
import com.example.moviezone.model.Cast
import com.example.moviezone.utils.CastDiffUtil

class CastAdapter: RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    private lateinit var binding: PersonCreditsBinding

    private var cast: List<Cast> = listOf()

    fun setCast(cast: List<Cast>) {
        val diffUtil = CastDiffUtil(this.cast, cast)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.cast = cast
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(binding: PersonCreditsBinding): RecyclerView.ViewHolder(binding.root) {
        val photo = binding.ivProfileImagePersoncredits
        val name = binding.tvNamePersoncredits
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastAdapter.ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.person_credits, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastAdapter.ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(R.drawable.ic_person)
            .into(holder.photo)

        holder.name.text = cast[position].castName
    }

    override fun getItemCount(): Int {
        return cast.size
    }

}