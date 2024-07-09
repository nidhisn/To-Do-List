package com.example.todolist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

private val TAG="ToDoAdapter"

class ToDoAdapter(private val context: Context, private val descriptionList:MutableList<ToDO>):RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.to_do_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return descriptionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val toDo=descriptionList[position]
        holder.bind(toDo)
        Log.d(TAG, "ToDoAdapter-adapter entry "+descriptionList.size)

        holder.tvDescription.setOnClickListener {
            descriptionList.removeAt(position)
            notifyDataSetChanged()
        }
    }


    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
         val tvDescription: TextView = itemView.findViewById(R.id.tvToDoDescription)
        fun bind(toDo: ToDO) {
            tvDescription.text=toDo.description

        }
    }


    fun addItem(toDo: ToDO) {
        descriptionList.add(toDo)
        notifyItemInserted(descriptionList.size - 1)
    }



}