package com.gk.android.data.remote

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.ApolloQueryCall
import com.gk.android.data.ArtistQuery
import com.gk.android.data.ArtistsQuery

class RemoteService constructor(private val apolloClient: ApolloClient) {
    // search for artists
    fun searchArtists(query: String, first: Int, after: String = ""): ApolloQueryCall<ArtistsQuery.Data> =
        apolloClient.query(ArtistsQuery(query, first, after))

    // get artist details
    fun getArtistDetails(id: String): ApolloQueryCall<ArtistQuery.Data> = apolloClient.query(ArtistQuery(id))
}