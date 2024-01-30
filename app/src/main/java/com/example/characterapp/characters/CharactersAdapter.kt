package com.example.characterapp.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.characterapp.R
import com.example.characterapp.databinding.RecyclerItemCharacterBinding
import com.example.characterapp.data.model.Character

class CharactersAdapter(private var items: List<Character>)
    : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

        lateinit var onItemClickListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding= RecyclerItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() =items.size


    inner class ViewHolder(var binding: RecyclerItemCharacterBinding )
        :RecyclerView.ViewHolder(binding.root){

        fun bind(item: Character){
            binding.tvName.text = item.name
            binding.tvSpeciesAndStatus.text = "${item.species} + ${item.status}"

            Glide.with(binding.layoutRoot).load(item.image)
                .placeholder(R.drawable.ic_avatar_placeholder)
                .dontAnimate().into(binding.ivAvatar)

            binding.layoutRoot.setOnClickListener {
                onItemClickListener.onItemClick(item.id, binding.layoutRoot)
            }
        }
    }

    fun setItems(newItems: List<Character>) {
        this.items = newItems
        notifyDataSetChanged()
    }


    interface ItemClickListener {
        fun onItemClick(id: Int, itemRoot: ConstraintLayout)
    }
}