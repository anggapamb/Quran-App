package com.anggapambudi.quranapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import com.anggapambudi.quranapp.R
import com.anggapambudi.quranapp.adapter.BookmarkAdapter
import com.anggapambudi.quranapp.databinding.FragmentBookmarkBinding
import com.anggapambudi.quranapp.databinding.FragmentQuranBinding
import com.anggapambudi.quranapp.model.DataItem
import com.anggapambudi.quranapp.room.BookmarkEntity
import com.anggapambudi.quranapp.room.QuranDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.support.v4.toast

class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding
    private val database by lazy { QuranDatabase }
    lateinit var bookmarkAdapter: BookmarkAdapter
    private var data = ArrayList<BookmarkEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookmarkBinding.inflate(layoutInflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerview()

    }

    private fun setRecyclerview() {
        bookmarkAdapter = BookmarkAdapter(arrayListOf(), object : BookmarkAdapter.onAdapterListener{
            override fun onDelete(bookmarkEntity: BookmarkEntity) {
                CoroutineScope(Dispatchers.IO).launch {
                    database.getDatabase(requireContext()).bookmarkDao().deleteBookmark(
                            bookmarkEntity
                    )
                    loadData()
                }
                toast("Deleted")
            }

        })

        binding.tvRecyclerviewBookmark.layoutManager = LinearLayoutManager(requireContext())
        binding.tvRecyclerviewBookmark.adapter = bookmarkAdapter
    }


    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val getBookmark = database.getDatabase(requireContext()).bookmarkDao().getBookmark()
            Log.d("BookmarkFragment", "cekBookmark: $getBookmark")
            withContext(Dispatchers.Main) {
                bookmarkAdapter.setData(getBookmark)
            }
        }
    }

}