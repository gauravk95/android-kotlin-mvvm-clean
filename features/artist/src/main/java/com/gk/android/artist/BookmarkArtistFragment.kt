package com.gk.android.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gk.android.artist.adapter.BookmarkArtistListAdapter
import com.gk.android.artist.databinding.FragmentBookmarkArtistBinding
import com.gk.android.artist.util.launchArtistDetails
import com.gk.android.common.toast
import com.gk.android.ui_components.bind
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class BookmarkArtistFragment : Fragment() {

    private val viewModel by sharedViewModel<BookmarkArtistViewModel>()
    private lateinit var binding: FragmentBookmarkArtistBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBookmarkArtistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUi()
    }

    private fun setupUi() {
        val adapter = BookmarkArtistListAdapter { viewModel.artistItemClick(it) }
        with(binding) {
            rvArtists.layoutManager = LinearLayoutManager(requireContext())
            rvArtists.adapter = adapter
            rvArtists.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }

        // artists
        viewModel.artists.bind(this) {
            adapter.submitList(it)
        }
        viewModel.toastMsg.bind(this) {
            requireContext().toast(it)
        }
        viewModel.goToDetails.bind(this) {
            requireActivity().launchArtistDetails(it)
        }
        viewModel.state.bind(this) {
            when (it) {
                BookmarkArtistState.EMPTY -> showEmptyUi()
                BookmarkArtistState.SHOW_BOOKMARKS -> showBookmarksUi()
                else -> {
                }
            }
        }
    }

    private fun showBookmarksUi() {
        with(binding) {
            rvArtists.isVisible = true
            emptyBookmarks.isVisible = false
        }
    }

    private fun showEmptyUi() {
        with(binding) {
            rvArtists.isVisible = false
            emptyBookmarks.isVisible = true
        }
    }
}