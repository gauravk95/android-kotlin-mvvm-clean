package com.gk.android.artist.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gk.android.artist.R
import com.gk.android.artist.databinding.ItemArtistBinding
import com.gk.android.domain.artist.model.Artist

typealias OnArtistItemSelected = (action: ArtistListAction) -> Unit

class ArtistListAdapter constructor(
    private val onArtistItemSelected: OnArtistItemSelected? = null
) : PagingDataAdapter<Artist, RecyclerView.ViewHolder>(ArtistItemDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemArtistBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { (holder as ViewHolder).bind(it, onArtistItemSelected) }
    }
}

class ViewHolder(private val binding: ItemArtistBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: Artist,
        onArtistItemSelected: OnArtistItemSelected?
    ) = with(binding) {
        // setup data
        artistName.text = item.name
        artistDisambiguation.text = item.disambiguation

        when (item.isBookmarked) {
            true -> bookmarkArtist.setImageResource(R.drawable.ic_baseline_remove_circle_outline_24)
            else -> bookmarkArtist.setImageResource(R.drawable.ic_baseline_bookmark_24)
        }

        // setup listeners
        root.setOnClickListener {
            onArtistItemSelected?.invoke(ArtistListAction.ItemClick(item))
        }
        bookmarkArtist.setOnClickListener {
            onArtistItemSelected?.invoke(ArtistListAction.BookmarkAction(item))
        }
    }
}

class ArtistItemDiff : DiffUtil.ItemCallback<Artist>() {

    override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem == newItem
    }
}

sealed class ArtistListAction {
    class ItemClick constructor(val item: Artist) : ArtistListAction()
    class BookmarkAction constructor(val item: Artist) : ArtistListAction()
}
