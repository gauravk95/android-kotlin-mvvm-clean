package com.gk.android.artistinfo

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.gk.android.common.toast
import com.gk.android.domain.artist.model.Artist
import com.gk.android.features.artistinfo.R
import com.gk.android.features.artistinfo.databinding.ActivityArtistDetailBinding
import com.gk.android.navigation.ArtistArgs
import com.gk.android.navigation.EXTRA_ARTIST_ARGS
import com.gk.android.ui_components.bind
import com.gk.android.ui_components.loadImage
import com.gk.android.ui_components.setToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtistDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtistDetailBinding
    private val viewModel by viewModel<ArtistDetailsViewModel>()

    private val args by lazy {
        intent.getParcelableExtra<ArtistArgs>(EXTRA_ARTIST_ARGS)
            ?: throw IllegalStateException("Artist args is required")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArtistDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
        subscribeUi()
    }

    private fun setupUi() {
        setToolbar(binding.toolbar, R.string.artist_details)

        // set initial ui
        binding.artistName.text = args.name
        binding.artistDisambiguation.text = args.disambiguation

        binding.bookmarkArtist.setOnClickListener {
            viewModel.bookmarkArtist()
        }
    }

    private fun subscribeUi() {

        viewModel.artist.bind(this) {
            setupArtistDetailsUi(it)
        }

        viewModel.toastMsg.bind(this) {
            toast(it)
        }

        viewModel.loadArtist(args)
    }

    private fun setupArtistDetailsUi(artist: Artist?) {
        if (artist == null) return
        with(binding) {
            artistName.text = getString(R.string.label_name, artist.name)
            artistDisambiguation.text = getString(R.string.label_aka, artist.disambiguation)
            artistCountry.text = getString(R.string.label_country, artist.country)
            artistGender.text = getString(R.string.label_gender, artist.gender)
            artistType.text = getString(R.string.label_type, artist.type)
            artistImage.loadImage(artist.imageUrl)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
