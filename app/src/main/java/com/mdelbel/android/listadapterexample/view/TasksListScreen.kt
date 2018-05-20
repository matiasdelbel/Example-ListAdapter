package com.mdelbel.android.listadapterexample.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdelbel.android.listadapterexample.R
import com.mdelbel.android.listadapterexample.model.Task
import com.mdelbel.android.listadapterexample.viewmodel.TasksViewModel

class TasksListScreen : Fragment() {

    companion object {
        const val TAG = "TasksListScreen"
    }

    private val todoListAdapter = TasksAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val contentView = inflater.inflate(R.layout.screen_task_list, container, false) as ViewGroup

        initTaskList(contentView)
        setUpAddTaskOption(contentView)

        observeTasks()
        observeTasksDeletion(contentView)

        return contentView
    }

    private fun initTaskList(contentView: View) {
        contentView.findViewById<RecyclerView>(R.id.tasks_list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = todoListAdapter
        }
    }

    private fun setUpAddTaskOption(contentView: View) {
        contentView.findViewById<FloatingActionButton>(R.id.tasks_add_task).setOnClickListener { _ ->
            activity?.supportFragmentManager?.beginTransaction()?.add(
                    R.id.main_container, CreateTaskScreen(), CreateTaskScreen.TAG)?.commit()
        }
    }

    private fun observeTasks() {
        val viewModel = ViewModelProviders.of(activity as FragmentActivity).get(TasksViewModel::class.java)
        viewModel.tasks().observe(this, Observer<List<Task>> { tasks -> todoListAdapter.submitList(tasks) })
    }

    private fun observeTasksDeletion(container: View) {
        val viewModel = ViewModelProviders.of(activity as FragmentActivity).get(TasksViewModel::class.java)
        viewModel.removedTask().observe(this, Observer { task ->
            val snackbar = Snackbar.make(container, R.string.create_task_deleted, Snackbar.LENGTH_LONG)

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
                task?.deleteObserver(viewModel)
            }
        }
    }
}