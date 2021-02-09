package com.gk.android.navigation

import android.content.Context
import android.content.Intent

object Actions {
    private fun internalIntent(
        context: Context,
        action: String
    ): Intent =
        Intent(action).setPackage(context.packageName)

    fun openArtistDetailsIntent(
        context: Context,
        args: ArtistArgs
    ): Intent =
        internalIntent(context, "com.gk.android.artistdetails.open")
            .putExtra(EXTRA_ARTIST_ARGS, args)
}