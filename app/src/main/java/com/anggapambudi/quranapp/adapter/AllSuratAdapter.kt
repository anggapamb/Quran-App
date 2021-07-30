package com.anggapambudi.quranapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anggapambudi.quranapp.databinding.ItemSuratQuranBinding
import com.anggapambudi.quranapp.model.DataItem

class AllSuratAdapter(private val item: ArrayList<DataItem>, private val listener: onAdapterListener):
        RecyclerView.Adapter<AllSuratAdapter.AllSuratViewHolder>() {

    class AllSuratViewHolder(val binding: ItemSuratQuranBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dataItem: DataItem) {
            binding.tvNomor.text = dataItem.nomor
            binding.tvSurat.text = dataItem.nama
            binding.tvSubtitle.text = "${dataItem.type}-${dataItem.ayat} ayat"
            binding.suratArab.text = dataItem.asma
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllSuratViewHolder {
        val binding = ItemSuratQuranBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllSuratViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllSuratViewHolder, position: Int) {
        holder.bind(item[position])
        val surat = item[position]
        holder.itemView.setOnClickListener {
            listener.onAddHistory( surat )
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    interface onAdapterListener {
        fun onAddHistory( dataItem: DataItem )
    }

}