package com.anggapambudi.quranapp.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.anggapambudi.quranapp.R
import com.anggapambudi.quranapp.activity.DetailSuratActivity
import com.anggapambudi.quranapp.adapter.BookmarkAdapter
import com.anggapambudi.quranapp.adapter.HistoryAdapter
import com.anggapambudi.quranapp.databinding.FragmentBookmarkBinding
import com.anggapambudi.quranapp.databinding.FragmentHistoryBinding
import com.anggapambudi.quranapp.room.BookmarkEntity
import com.anggapambudi.quranapp.room.HistoryEntity
import com.anggapambudi.quranapp.room.QuranDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.support.v4.toast

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val database by lazy { QuranDatabase }
    lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerviewHistory()

    }

    private fun setRecyclerviewHistory() {
        historyAdapter = HistoryAdapter(arrayListOf(), object : HistoryAdapter.onAdapterListener{
            override fun onClickHistory(historyEntity: HistoryEntity) {
                val moveDetail = Intent(requireContext(), DetailSuratActivity::class.java)
                        .putExtra("KEY_NUMBER", historyEntity.number)
                        .putExtra("KEY_NAME", historyEntity.name)
                        .putExtra("KEY_ARTI", historyEntity.arti)
                        .putExtra("KEY_MEKAH", historyEntity.type)
                        .putExtra("KEY_AYAT", historyEntity.ayat)
                        .putExtra("KEY_ASMA", historyEntity.asma)
                startActivity(moveDetail)
            }

        })

        binding.tvRecyclerviewHistory.layoutManager = LinearLayoutManager(requireContext())
        binding.tvRecyclerviewHistory.adapter = historyAdapter

    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val getHistory = database.getDatabase(requireContext()).historyDao().getHistory()
            Log.d("HistoryFragment", "cekHistory: $getHistory")
            withContext(Dispatchers.Main) {
                historyAdapter.setData(getHistory)
            }
        }
    }
}