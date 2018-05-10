package com.mdelbel.android.listadapterexample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var todoListAdapter = TasksAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTaskListView()
        observeTasks()
    }

    private fun initTaskListView() {
        findViewById<RecyclerView>(R.id.to_do_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = todoListAdapter
        }
    }

    private fun observeTasks() {
        val viewModel = ViewModelProviders.of(this).get(TasksViewModel::class.java)
        viewModel.getTasks().observe(this, Observer<List<Task>> { tasks -> todoListAdapter.submitList(tasks) })
    }
}