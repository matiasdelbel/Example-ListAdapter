package com.mdelbel.android.listadapterexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity(), Observer {

    private var tasks: MutableList<Task> = TaskProvider().createTasks().toMutableList()//TODO move to ViewModel

    private lateinit var todoList: RecyclerView
    private var todoListAdapter = TasksAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoList = findViewById<RecyclerView>(R.id.to_do_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = todoListAdapter
        }

        for (task in tasks) { //TODO mock for now
            task.addObserver(this)
        }
        todoListAdapter.submitList(tasks.toList())
    }

    //TODO move to ViewModel
    override fun update(o: Observable?, arg: Any?) {
        tasks.remove(arg)
        val updatedList = tasks.toList()
        todoListAdapter.submitList(updatedList)
    }

}