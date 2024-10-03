package com.lessthanthree.galistapp.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lessthanthree.galistapp.core.databinding.ItemListGameBinding
import com.lessthanthree.galistapp.core.domain.model.Game

class GameAdapter : ListAdapter<Game, GameAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Game) -> Unit)? = null

    inner class ListViewHolder(private var binding: ItemListGameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Game) {
            Glide.with(itemView.context)
                .load(data.backgroundImage)
                .into(binding.ivImageGame)
            binding.tvTitleGame.text = data.name
            binding.tvReleaseGame.text = data.released
            binding.rbRatingGame.rating = data.rating.toFloat()
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Game> =
            object : DiffUtil.ItemCallback<Game>() {
                override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                    return oldItem.gameId == newItem.gameId
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                    return oldItem == newItem
                }

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            ItemListGameBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
}