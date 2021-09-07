package com.android.distilled.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.distilled.DistilledApplication.Companion.context
import com.android.distilled.R
import com.android.distilled.database.TvShowEntity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.tvshow_cell.view.*

class ShowsListAdapter(
    private var statesList: MutableList<TvShowEntity> = mutableListOf(),
    private val onClick: (TvShowEntity) -> Unit,
) :
    RecyclerView.Adapter<ShowsListAdapter.StateListViewHolder>() {

    fun refreshList(list: MutableList<TvShowEntity> = mutableListOf()) {
        val diffCallback = ShowsDiffCallback(statesList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        statesList.clear()
        statesList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateListViewHolder {
        return StateListViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.tvshow_cell, parent, false))
    }


    override fun onBindViewHolder(holder: StateListViewHolder, position: Int) {
        holder.bind(statesList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return statesList.size
    }

    inner class StateListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: TvShowEntity) {
            itemView.overview.text = item.overview
            itemView.title.text = item.name
            itemView.releaseDate.text =
                context.resources.getString(R.string.release_date_s, item.firstAirDate.toString())
            itemView.rating.text =
                context.resources.getString(R.string.rating_s, item.voteAverage.toString())
            Glide.with(view.context)
                .load("https://image.tmdb.org/t/p/w500${item.posterPath}")
                .centerCrop()
                .into(itemView.imageView)
            itemView.tvShow.setOnClickListener {
                onClick.invoke(item)
            }
        }
    }
}

class ShowsDiffCallback(
    private val oldList: List<TvShowEntity>,
    private val newList: List<TvShowEntity>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id === newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (_, v1, n1) = oldList[oldPosition]
        val (_, v2, n2) = newList[newPosition]

        return n1 == n2 && v1 == v2
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}