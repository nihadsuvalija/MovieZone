package com.example.moviezone.screen.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviezone.R
import com.example.moviezone.databinding.PersonCreditsBinding
import com.example.moviezone.model.Cast
import com.example.moviezone.model.MovieCredits
import com.example.moviezone.utils.CastDiffUtil
import com.example.moviezone.utils.Const

class CastAdapter: RecyclerView.Adapter<CastAdapter.CreditsViewHolder>() {

    private lateinit var binding: PersonCreditsBinding
    private var cast: List<Cast> = listOf()

    fun setCredits(movieCredits: MovieCredits) {
        val diffUtil = CastDiffUtil(this.cast, movieCredits.cast)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        this.cast = movieCredits.cast
        diffResult.dispatchUpdatesTo(this)
    }

    class CreditsViewHolder(binding: PersonCreditsBinding): RecyclerView.ViewHolder(binding.root) {
        val profileImage = binding.ivProfileImagePersoncredits
        val name = binding.tvNamePersoncredits
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditsViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.person_credits, parent, false)

        return CreditsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CreditsViewHolder, position: Int) {
        holder.name.text = cast[position].name
        if (!cast[position].profilePath.isNullOrEmpty()) {
            Glide.with(holder.itemView.context)
                .load(Const.TMDB_IMAGE_URL + cast[position].profilePath)
                .circleCrop()
                .into(holder.profileImage)
        } else {
            Glide.with(holder.itemView.context)
                .load(R.drawable.ic_person)
                .circleCrop()
                .into(holder.profileImage)
        }
    }

    override fun getItemCount(): Int {
        return cast.size
    }

}