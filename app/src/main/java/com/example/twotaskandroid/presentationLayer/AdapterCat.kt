package com.example.twotaskandroid.presentationLayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.twotaskandroid.objects.Cat
import com.example.twotaskandroid.R

class AdapterCat : RecyclerView.Adapter<AdapterCat.CatsViewHolder>() {

    private var cats = ArrayList<Cat>()
    private lateinit var onReachEndListener: OnReachEndListener
    private lateinit var onItemClickListener: OnItemClickListener
    private val BASE_URL = "https://cataas.com/cat?id="

    fun setCats(cats : List<Cat>){
        this.cats = cats as ArrayList<Cat>
        notifyDataSetChanged()
    }

    fun setOnReachEndListener(onReachEndListener: OnReachEndListener){
        this.onReachEndListener = onReachEndListener
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cat_object, parent, false)

        return CatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        val cat = cats.get(position)
        Glide.with(holder.itemView).load(BASE_URL + cat.url).into(holder.imageCat)

        if(position >= cats.size - 10)
            onReachEndListener.onReachEnd()

        holder.itemView.setOnClickListener{
            onItemClickListener.onItemClick(cat)
        }
    }

    override fun getItemCount(): Int {
        return cats.size
    }

    class CatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageCat = itemView.findViewById<ImageView>(R.id.image_cat)
    }

    interface OnReachEndListener{
        fun onReachEnd()
    }

    interface OnItemClickListener{
        fun onItemClick(cat : Cat)
    }
}