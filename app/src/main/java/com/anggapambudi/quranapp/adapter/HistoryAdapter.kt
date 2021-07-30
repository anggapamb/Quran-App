package com.anggapambudi.quranapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anggapambudi.quranapp.databinding.ItemSuratQuranBinding
import com.anggapambudi.quranapp.model.DataItem
import com.anggapambudi.quranapp.room.BookmarkEntity
import com.anggapambudi.quranapp.room.HistoryEntity

class HistoryAdapter(private val item: ArrayList<HistoryEntity>, private val listener: onAdapterListener):
        RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(val binding: ItemSuratQuranBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(historyEntity: HistoryEntity) {
            binding.tvNomor.text = historyEntity.number
            binding.tvSurat.text = historyEntity.name
            binding.tvSubtitle.text = "${historyEntity.type}-${historyEntity.ayat} ayat"
            binding.suratArab.text = "${historyEntity.asma}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemSuratQuranBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(item[position])
        val surat = item[position]
        holder.itemView.setOnClickListener {
            listener.onClickHistory( surat )
        }
    }

    override fun getItemCount(): Int {
        //return item.size
        return Math.min(item.size, 7)
    }

    fun setData(data: List<HistoryEntity>) {
        item.clear()
        item.addAll(data)
        notifyDataSetChanged()
    }

    interface onAdapterListener {
        fun onClickHistory( historyEntity: HistoryEntity)
    }

}