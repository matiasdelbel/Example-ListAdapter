package com.mdelbel.android.listadapterexample.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import com.mdelbel.android.listadapterexample.R
import com.mdelbel.android.listadapterexample.model.Task
import com.mdelbel.android.listadapterexample.viewmodel.TasksViewModel


class CreateTaskScreen : Fragment() {

    companion object {
        const val TAG = "CreateTaskScreen"
    }

    private lateinit var titleView: TextView
    private lateinit var saveView: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val contentView = inflater.inflate(R.layout.screen_create_task, container, false) as ViewGroup
        titleView = contentView.findViewById(R.id.create_task_input)
        saveView = contentView.findViewById(R.id.create_task_save)

        saveView.setOnClickListener { _ ->
            saveTask()
            hideKeyboard()
            dismiss()
        }

        return contentView
    }

    private fun saveTask() {
        val task = Task.create(titleView.text.toString())
        val viewModel = ViewModelProviders.of(activity as FragmentActivity).get(TasksViewModel::class.java)

        task.addObserver(viewModel)
        viewModel.saveTask(task)
    }

    private fun dismiss() {
        val fragment = activity?.supportFragmentManager?.findFragmentByTag(TAG)
        activity?.supportFragmentManager?.beginTransaction()?.remove(fragment)?.commit()
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }
}