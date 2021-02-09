package com.gk.android.artist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gk.android.artist.databinding.ItemArtistBinding
import com.gk.android.domain.artist.model.Artist

class BookmarkArtistListAdapter constructor(
    private val onArtistItemSelected: OnArtistItemSelected? = null
) : ListAdapter<Artist, RecyclerView.ViewHolder>(ArtistItemDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemArtistBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(getItem(position), onArtistItemSelected)
    }
}
