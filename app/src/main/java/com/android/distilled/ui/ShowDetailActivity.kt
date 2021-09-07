package com.android.distilled.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.distilled.R
import com.android.distilled.database.TvShowEntity
import com.android.distilled.databinding.ShowDetailViewBinding
import com.bumptech.glide.Glide

class ShowDetailActivity : AppCompatActivity() {
    lateinit var binding: ShowDetailViewBinding
    lateinit var entity: TvShowEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShowDetailViewBinding.inflate(layoutInflater)
        entity = intent.getSerializableExtra("DetailedObj") as TvShowEntity
        loadPageDetails()
        setContentView(binding.root)
    }

    private fun loadPageDetails() {
        binding.overview.text = entity.overview
        binding.title.text = entity.name
        binding.ratingVotes.text =
            resources.getString(R.string.vote_count, entity.voteCount.toString())
        binding.popularity.text =
            resources.getString(R.string.popularity, entity.popularity.toString())
        binding.originCountry.text =
            resources.getString(R.string.original_country, entity.originCountry?.toString())
        binding.releaseDate.text =
            resources.getString(R.string.release_date_s, entity.firstAirDate.toString())
        binding.rating.text = resources.getString(R.string.rating_s, entity.voteAverage.toString())
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${entity.posterPath}")
            .centerCrop()
            .into(binding.imageView)
    }
}