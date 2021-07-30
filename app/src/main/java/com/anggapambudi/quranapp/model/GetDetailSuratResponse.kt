package com.anggapambudi.quranapp.model

import com.google.gson.annotations.SerializedName

data class GetDetailSuratResponse(

	@field:SerializedName("data")
	val data: ArrayList<DataItemDetail>,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItemDetail(

	@field:SerializedName("ar")
	val ar: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("nomor")
	val nomor: String? = null,

	@field:SerializedName("tr")
	val tr: String? = null
)
