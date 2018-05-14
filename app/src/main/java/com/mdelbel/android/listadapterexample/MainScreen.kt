package com.mdelbel.android.listadapterexample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.mdelbel.android.listadapterexample.model.Task


class MainScreen : AppCompatActivity() {

    private val todoListAdapter = TasksAdapter()
    private lateinit var viewModel: TasksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_main)
        viewModel = ViewModelProviders.of(this).get(TasksViewModel::class.java)

        initTaskList()
        setUpAddTaskOption()

        observeTasks()
    }

    private fun initTaskList() {
        findViewById<RecyclerView>(R.id.to_do_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = todoListAdapter
        }
    }

    private fun setUpAddTaskOption() {
        findViewById<FloatingActionButton>(R.id.to_do_add_task).setOnClickListener { _ ->
            val bottomSheetDialogFragment = CreateTaskScreen()
            bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
        }
    }

    private fun observeTasks() {
        viewModel.tasks().observe(this, Observer<List<Task>> { tasks -> todoListAdapter.submitList(tasks) })
    }
}