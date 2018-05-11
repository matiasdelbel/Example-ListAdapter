package com.mdelbel.android.listadapterexample

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetBehavior.STATE_COLLAPSED
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView

//TODO no se esta refrescando bien la vista con el bottom shet, sacarlo a la bosta
class MainActivity : AppCompatActivity() {

    private var todoListAdapter = TasksAdapter()
    private lateinit var viewModel: TasksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(TasksViewModel::class.java)

        initTaskListView()
        initTaskCreationView()
        observeTasks()
    }

    private fun initTaskListView() {
        findViewById<RecyclerView>(R.id.to_do_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = todoListAdapter
        }
    }

    private fun initTaskCreationView() {
        findViewById<Button>(R.id.create_task_save).setOnClickListener { _ ->
            createTask()
            collapsedBottomSheet()
        }
    }

    private fun observeTasks() {
        viewModel.getTasks().observe(this, Observer<List<Task>> { tasks -> todoListAdapter.submitList(tasks) })
    }

    private fun createTask() {
        val title = findViewById<TextView>(R.id.create_task_input).text
        val task = Task.create(title.toString())

        viewModel.saveTask(task)
    }

    private fun collapsedBottomSheet() {
        val bottomSheet = findViewById<View>(R.id.create_task_container)
        BottomSheetBehavior.from(bottomSheet).state = STATE_COLLAPSED
    }
}