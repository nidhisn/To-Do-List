package com.example.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(private val context: Context, private val descriptionList:List<ToDO>):RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {

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
    }


    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val tvDescription: TextView = itemView.findViewById(R.id.tvToDoDescription)
        fun bind(toDo: ToDO) {
            tvDescription.text=toDo.description
        }


    }


}