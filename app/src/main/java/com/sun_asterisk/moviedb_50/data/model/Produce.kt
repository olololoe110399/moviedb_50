package com.sun_asterisk.moviedb_50.data.model

import com.google.gson.annotations.SerializedName

data class Produce(
    @SerializedName(ProduceEntry.ID) val produceID: Int,
    @SerializedName(ProduceEntry.LOGO) val produceLogo: String,
    @SerializedName(ProduceEntry.NAME) val produceName: String,
    @SerializedName(ProduceEntry.COUNTRY) val produceCountry: String
) {
    object ProduceEntry {
        const val PRODUCES = "production_companies"
        const val ID = "id"
        const val LOGO = "logo_path"
        const val NAME = "name"
        const val COUNTRY = "origin_country"
    }
}
