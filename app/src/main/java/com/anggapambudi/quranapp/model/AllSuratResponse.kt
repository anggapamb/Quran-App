package com.anggapambudi.quranapp.model

import com.google.gson.annotations.SerializedName

data class AllSuratResponse(

	@field:SerializedName("data")
	val data: ArrayList<DataItem>,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItem(

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("rukuk")
	val rukuk: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("ayat")
	val ayat: Int? = null,

	@field:SerializedName("urut")
	val urut: String? = null,

	@field:SerializedName("arti")
	val arti: String? = null,

	@field:SerializedName("asma")
	val asma: String? = null,

	@field:SerializedName("audio")
	val audio: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("nomor")
	val nomor: String? = null
)
