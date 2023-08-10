package com.apolisb42.newsproject.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.volley.toolbox.ImageLoader
import com.apolisb42.newsproject.R
import com.apolisb42.newsproject.model.New
import com.apolisb42.newsproject.databinding.ItemNewsBinding
import java.text.SimpleDateFormat
import java.util.Locale

class NewsAdapter(private val list:List<New>, val imageLoader: ImageLoader, val listener: ItemClickListener):Adapter<NewsAdapter.NewsViewHolder>() {



    inner class NewsViewHolder(private val binding: ItemNewsBinding):ViewHolder(binding.root){

        fun bind(data: New){

            with(binding){
                tvTitle.text = data.title
                tvAuthor.text = data.author
                val categories= StringBuilder()
                data.category.forEach {
                    categories.append("$it \t")
                }

                tvCategory.text = categories
                tvDescription.text = data.description
                imageLoader.get(data.image, ImageLoader.getImageListener(
                    NetworkImageView,
                    R.drawable.ic_launcher_background,
                    R.drawable.baseline_newspaper_24
                ))
                NetworkImageView.setImageUrl(data.image, imageLoader)
                binding.ImgBookMark.isSelected = false
                try{
                    val parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
                    val formatter = SimpleDateFormat("dd-MM-yyyy, HH:mm",Locale.US)
                     parser.parse(data.published)?.let {
                        binding.tvTime.text = formatter.format(it)
                    }

                }catch(e:Exception){
                   Log.e("Time-format", e.message.toString())
                }


                binding.ImgBookMark.setOnClickListener{
                    it.isSelected = !it.isSelected
                    if(it.isSelected) {
                        listener.send(data)
                    }else{
                        listener.delete(data.id)
                    }

                }

                binding.root.setOnClickListener {
                    listener.isSelected(data)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(layoutInflater)
        return NewsViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int) = position
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position])

    }
}