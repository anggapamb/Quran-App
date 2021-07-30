package com.anggapambudi.quranapp.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.anggapambudi.quranapp.activity.DetailSuratActivity
import com.anggapambudi.quranapp.adapter.AllSuratAdapter
import com.anggapambudi.quranapp.databinding.FragmentQuranBinding
import com.anggapambudi.quranapp.model.AllSuratResponse
import com.anggapambudi.quranapp.model.DataItem
import com.anggapambudi.quranapp.retrofit.ApiService
import com.anggapambudi.quranapp.room.HistoryEntity
import com.anggapambudi.quranapp.room.QuranDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuranFragment : Fragment() {

    private lateinit var binding: FragmentQuranBinding
    val list = ArrayList<DataItem>()
    private val database by lazy { QuranDatabase }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuranBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllSurat()

    }

    private fun setAllSurat() {

        //progressdialog
        val progressDialog = ProgressDialog(requireContext())
        progressDialog.setCancelable(false)
        progressDialog.setMessage("memuat...")
        progressDialog.show()

        ApiService.instance.getAllSurat().enqueue(object : Callback<AllSuratResponse>{
            override fun onResponse(call: Call<AllSuratResponse>, response: Response<AllSuratResponse>) {

                progressDialog.dismiss()
                val allSurat = response.body()?.data
                allSurat?.let { list.addAll((it)) }

                binding.tvRecyclerviewSurat.layoutManager = LinearLayoutManager(requireContext())
                binding.tvRecyclerviewSurat.adapter = AllSuratAdapter(list, object : AllSuratAdapter.onAdapterListener{
                    override fun onAddHistory(dataItem: DataItem) {
                        val moveDetail = Intent(requireContext(), DetailSuratActivity::class.java)
                                .putExtra("KEY_NUMBER", dataItem.nomor)
                                .putExtra("KEY_NAME", dataItem.nama)
                                .putExtra("KEY_ARTI", dataItem.arti)
                                .putExtra("KEY_MEKAH", dataItem.type)
                                .putExtra("KEY_AYAT", dataItem.ayat)
                                .putExtra("KEY_ASMA", dataItem.asma)

                            CoroutineScope(Dispatchers.IO).launch {
                                database.getDatabase(requireContext()).historyDao().onAddHistory(
                                        HistoryEntity(0, "${dataItem.nomor}", "${dataItem.nama}",
                                        "${dataItem.arti}", "${dataItem.type}", "${dataItem.ayat}",
                                        "${dataItem.asma}")
                                )
                            }


                        startActivity(moveDetail)
                    }

                })


            }

            override fun onFailure(call: Call<AllSuratResponse>, t: Throwable) {
                progressDialog.dismiss()
                toast("Oops, jaringan internet anda bermasalah")
            }

        })
    }


}