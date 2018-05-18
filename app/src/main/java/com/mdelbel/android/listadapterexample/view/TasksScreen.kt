package com.mdelbel.android.listadapterexample.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.mdelbel.android.listadapterexample.R
import com.mdelbel.android.listadapterexample.model.Task
import com.mdelbel.android.listadapterexample.viewmodel.TasksViewModel


class TasksScreen : AppCompatActivity() {

    private val todoListAdapter = TasksAdapter()
    private lateinit var viewModel: TasksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_main)
        viewModel = ViewModelProviders.of(this).get(TasksViewModel::class.java)

        initTaskList()
        setUpAddTaskOption()

        observeTasks()
        observeTasksDeletion()
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

    private fun observeTasksDeletion() {
        viewModel.restoreTasksListener().observe(this, Observer { task ->
            val snackbar = Snackbar.make(findViewById(R.id.to_do_list_container), R.string.create_task_deleted, Snackbar.LENGTH_LONG)

            snackbar.setAction(R.string.create_task_restore, { _ -> task?.restore() })
            snackbar.addCallback(RestoreSnackbarCallback(viewModel, task))
            snackbar.show()
        })
    }

    class RestoreSnackbarCallback(private val viewModel: TasksViewModel, private val task: Task?) : Snackbar.Callback() {

        override fun onShown(snackbar: Snackbar?) {
            // do nothing
        }

        override fun onDismissed(snackbar: Snackbar?, event: Int) {
            if (DISMISS_EVENT_ACTION != event) {
                viewModel.stopObserve(task)
            }
        }
    }
}