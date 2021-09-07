package com.android.distilled.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.distilled.DistilledApplication.Companion.context
import com.android.distilled.R
import com.android.distilled.database.TvShowEntity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.tvshow_cell.view.*

@SuppressLint("NotifyDataSetChanged")
class ShowsListAdapter(private var statesList: MutableList<TvShowEntity> = mutableListOf()) :
    RecyclerView.Adapter<ShowsListAdapter.StateListViewHolder>() {

    fun refreshList(list: MutableList<TvShowEntity> = mutableListOf()) {
        statesList.clear()
        statesList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateListViewHolder {
        return StateListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tvshow_cell,parent,false))
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

    class StateListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: TvShowEntity) {
            Log.e("StateListViewHolder",item.toString())
            itemView.overview.text = item.overview
            itemView.title.text = item.name
            itemView.releaseDate.text =
                context.resources.getString(R.string.release_date_s, item.firstAirDate.toString())
            itemView.rating.text =
                context.resources.getString(R.string.rating_s, item.voteAverage.toString())
            Glide.with(view.context)
                .load("https://image.tmdb.org/t/p/w500${item.posterPath}")
                .centerCrop()
                .into(itemView.imageView);

        }
    }
}