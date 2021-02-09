package com.gk.android.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gk.android.artist.adapter.ArtistListAdapter
import com.gk.android.artist.databinding.FragmentArtistBinding
import com.gk.android.artist.util.launchArtistDetails
import com.gk.android.common.toast
import com.gk.android.ui_components.bind
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ArtistFragment : Fragment() {

    private val viewModel by sharedViewModel<ArtistViewModel>()
    private lateinit var binding: FragmentArtistBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentArtistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUi()
    }

    private fun setupUi() {

        // setup the list adapter
        val adapter = ArtistListAdapter { viewModel.artistItemClick(it) }
        with(binding) {
            rvArtists.layoutManager = LinearLayoutManager(requireContext())
            rvArtists.adapter = adapter
            rvArtists.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))

            adapter.addLoadStateListener {
                progress.isVisible = it.refresh is LoadState.Loading
            }
        }

        // setup the search
        setupSearch()

        viewModel.artistsData.bind(this) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
        viewModel.goToDetails.bind(this) {
            requireActivity().launchArtistDetails(it)
        }

        viewModel.toastMsg.bind(this) {
            requireContext().toast(it)
        }
    }

    private fun setupSearch() {
        binding.searchArtists.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // do nothing
                return true
            }
        })
    }

    private fun search(query: String?) {
        binding.rvArtists.scrollToPosition(0)
        viewModel.search(query?.trim())
    }
}