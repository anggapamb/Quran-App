package com.anggapambudi.quranapp.activity

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.anggapambudi.quranapp.R
import com.anggapambudi.quranapp.adapter.DetailSuratAdapter
import com.anggapambudi.quranapp.databinding.ActivityDetailSuratBinding
import com.anggapambudi.quranapp.databinding.ItemDetailSuratBinding
import com.anggapambudi.quranapp.model.DataItemDetail
import com.anggapambudi.quranapp.model.GetDetailSuratResponse
import com.anggapambudi.quranapp.retrofit.ApiService
import com.anggapambudi.quranapp.room.BookmarkEntity
import com.anggapambudi.quranapp.room.QuranDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailSuratActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSuratBinding
    private val data = ArrayList<DataItemDetail>()
    private val database by lazy { QuranDatabase }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuratBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarDetailSuratActivity)

        setView()
        setDetailSurat()
    }

    private fun setView() {
        val getName = intent.getStringExtra("KEY_NAME")
        val getArti = intent.getStringExtra("KEY_ARTI")
        val getMekah = intent.getStringExtra("KEY_MEKAH")
        val getAyat = intent.getIntExtra("KEY_AYAT", 0).toString()
        val getAsma = intent.getStringExtra("KEY_ASMA")

        binding.tvNameDetail.text = getName
        binding.tvArtiDetail.text = getArti
        binding.tvAsmaDetail.text = getAsma
        binding.tvNameDetailToolbar.text = getName
        binding.tvMekahDetail.text = "$getMekah-$getAyat ayat"

    }

    private fun setDetailSurat() {
        val getNumber = intent.getStringExtra("KEY_NUMBER")

        //progressdialog
        val progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setMessage("memuat...")
        progressDialog.show()

        ApiService.instance.getDetailSurat("$getNumber").enqueue(object : Callback<GetDetailSuratResponse>{
            override fun onResponse(call: Call<GetDetailSuratResponse>, response: Response<GetDetailSuratResponse>) {

                progressDialog.dismiss()
                val dataDetail = response.body()?.data
                dataDetail?.let { data.addAll(it) }

                binding.tvRecyclerviewDetail.layoutManager = LinearLayoutManager(applicationContext)
                binding.tvRecyclerviewDetail.adapter = DetailSuratAdapter(data, object : DetailSuratAdapter.onAdapterListener{
                    override fun onAdd(dataItemDetail: DataItemDetail) {
                        CoroutineScope(Dispatchers.IO).launch {
                            database.getDatabase(this@DetailSuratActivity).bookmarkDao().addBookmark(
                                BookmarkEntity(0, "${dataItemDetail.nomor}", "${dataItemDetail.ar}",
                                    "${dataItemDetail.id}")
                            )
                        }
                    }

                })
            }

            override fun onFailure(call: Call<GetDetailSuratResponse>, t: Throwable) {
                progressDialog.dismiss()
                toast("Oops, jaringan internet anda bermasalah")
                finish()
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}