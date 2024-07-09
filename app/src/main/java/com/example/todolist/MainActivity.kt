package com.example.todolist

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding

private val TAG="ToDoMainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ToDoAdapter
    private val descriptionList = mutableListOf<ToDO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        //set up RecyclerView
        adapter= ToDoAdapter(this,descriptionList)
        binding.rvToDo.layoutManager=LinearLayoutManager(this)
        binding.rvToDo.adapter=adapter


        binding.fabToDoAdd.setOnClickListener {
            showAddToDoDialog()

        }


    }

    private fun showAddToDoDialog() {
        //inflate the dialog layout
        val dialogView=layoutInflater.inflate(R.layout.dialog_add_todo, null)
        val etToDoDescription=dialogView.findViewById<EditText>(R.id.etToDoDescription)
        //create and show dialog
        AlertDialog.Builder(this)
            .setTitle("Add To-Do")
            .setView(dialogView)
            .setPositiveButton("Add"){dialog, which->
                val description=etToDoDescription.text.toString()
                Log.d(TAG, "ToDoMainActivity-add button")
                if(description.isNotEmpty()){
                    addToDoItem(description)
                    Log.d(TAG, "ToDoMainActivity-adapter entry")
                }
            }
            .setNegativeButton("Cancel", null)
            .show()


    }

    private fun addToDoItem(description: String) {
            val newToDo=ToDO(description)
            descriptionList.add(newToDo)
            adapter.notifyDataSetChanged()

    }

}