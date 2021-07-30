package com.anggapambudi.quranapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.anggapambudi.quranapp.databinding.ItemDetailSuratBinding
import com.anggapambudi.quranapp.helper.MyApplication
import com.anggapambudi.quranapp.model.DataItemDetail
import com.anggapambudi.quranapp.room.BookmarkEntity
import org.jetbrains.anko.sdk27.coroutines.onClick

class DetailSuratAdapter(private val item: ArrayList<DataItemDetail>, private val listener: onAdapterListener):
        RecyclerView.Adapter<DetailSuratAdapter.DetailSuratViewHolder>() {

    class DetailSuratViewHolder(val binding: ItemDetailSuratBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dataItemDetail: DataItemDetail) {
            binding.tvNomorDetail.text = dataItemDetail.nomor
            binding.tvArabDetail.text = dataItemDetail.ar
            binding.tvIndoDetail.text = dataItemDetail.id

            binding.btnShare.setOnClickListener {
                val t1 = "${dataItemDetail.ar} \n\nArtinya: ${dataItemDetail.id}"
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type="text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, t1)
                itemView.context.startActivity(Intent.createChooser(shareIntent,"Share via"))
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailSuratViewHolder {
        val binding = ItemDetailSuratBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailSuratViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailSuratViewHolder, position: Int) {
        holder.bind(item[position])

        val dataItemDetail = item[position]
        holder.binding.btnBookmarkOutline.setOnClickListener {
            listener.onAdd( dataItemDetail )
            Toast.makeText(holder.itemView.context, "Saved", Toast.LENGTH_SHORT).show()
            holder.binding.btnBookmarkOutline.visibility = View.GONE
            holder.binding.btnBookmarkBaseline.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    interface onAdapterListener {
        fun onAdd(dataItemDetail: DataItemDetail)
    }

}