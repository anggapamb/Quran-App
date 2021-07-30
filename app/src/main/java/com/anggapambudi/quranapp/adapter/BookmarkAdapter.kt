package com.anggapambudi.quranapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.anggapambudi.quranapp.databinding.ItemDetailSuratBinding
import com.anggapambudi.quranapp.room.BookmarkEntity

class BookmarkAdapter(private val item: ArrayList<BookmarkEntity>, private val listener: onAdapterListener):
        RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    class BookmarkViewHolder(val binding: ItemDetailSuratBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val binding = ItemDetailSuratBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val bookmark = item[position]
        holder.binding.tvNomorDetail.text = bookmark.ayat
        holder.binding.tvArabDetail.text = bookmark.arab
        holder.binding.tvIndoDetail.text = bookmark.indo
        holder.binding.btnShare.setOnClickListener {
            val t1 = "${bookmark.arab} \n\nArtinya: ${bookmark.indo}"
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, t1)
            holder.itemView.context.startActivity(Intent.createChooser(shareIntent,"Share via"))
        }

        holder.binding.btnBookmarkBaseline.visibility = View.VISIBLE
        holder.binding.btnBookmarkOutline.visibility = View.GONE
        holder.binding.btnBookmarkBaseline.setOnClickListener {
            listener.onDelete( bookmark )
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun setData(data: List<BookmarkEntity>) {
        item.clear()
        item.addAll(data)
        notifyDataSetChanged()
    }

    interface onAdapterListener {
        fun onDelete(bookmarkEntity: BookmarkEntity)
    }

}