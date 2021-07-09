package com.example.exam10

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exam10.databinding.CubeLayoutBinding

typealias OnClick = (position: Int) -> Unit

class GameAdapter : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    lateinit var click: OnClick

    private val items = mutableListOf<Int?>()

    fun setItems(items: MutableList<Int?>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(
            CubeLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = items.size

    inner class GameViewHolder(private val binding: CubeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val value = items[adapterPosition]
            binding.root.text = value?.toString() ?: ""
            if(value == adapterPosition+1)
                binding.root.setBackgroundColor(Color.GREEN)
            else
                binding.root.setBackgroundResource(R.color.standard)
            binding.root.setOnClickListener {
                click(adapterPosition)
            }
        }
    }
}