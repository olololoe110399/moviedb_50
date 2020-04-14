package com.sun_asterisk.moviedb_50.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.sun_asterisk.moviedb_50.data.model.Genres

data class GenresResponse(@SerializedName(Genres.GenresEntry.GENRES) var list: List<Genres>)
