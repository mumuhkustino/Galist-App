package com.lessthanthree.galistapp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.IntentCompat.getParcelableExtra
import com.bumptech.glide.Glide
import com.lessthanthree.galistapp.R
import com.lessthanthree.galistapp.core.domain.model.Game
import com.lessthanthree.galistapp.databinding.ActivityDetailGameBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailGameActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailGameBinding
    private val detailGameViewModel: DetailGameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val detailGame = getParcelableExtra(intent, EXTRA_DATA, Game::class.java)
        showDetailGame(detailGame)
    }

    private fun showDetailGame(detailGame: Game?) {
        detailGame?.let {
            supportActionBar?.title = ""
            binding.contentDetailGame.rbRatingGame.rating = detailGame.rating.toFloat()
            binding.contentDetailGame.tvDetailDescription.text = detailGame.name
            binding.contentDetailGame.tvDetailReleased.text =
                StringBuilder("Released On : " +
                        (detailGame.released ?: "-"))
            binding.contentDetailGame.tvDetailRatingsCount.text =
                StringBuilder("Number of rating : " +
                        detailGame.ratingsCount.toString())
            binding.contentDetailGame.tvDetailAdded.text =
                StringBuilder("Number of added : " +
                    detailGame.added.toString())
            binding.contentDetailGame.tvDetailPlaytime.text =
                StringBuilder("Number of suggestion : " +
                        detailGame.playtime.toString())
            binding.contentDetailGame.tvDetailSuggestionCount.text =
                StringBuilder("Number of reviews : " +
                        detailGame.suggestionsCount.toString())
            binding.contentDetailGame.tvDetailReviewsCount.text =
                StringBuilder("Playtime : " +
                        detailGame.reviewsCount.toString())
            Glide.with(this@DetailGameActivity)
                .load(detailGame.backgroundImage)
                .into(binding.ivDetailImage)

            var statusFavorite = detailGame.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailGameViewModel.setFavoriteGame(detailGame, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(status: Boolean) {
        if (status) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
        }
    }
}