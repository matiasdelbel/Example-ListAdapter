package com.mdelbel.android.listadapterexample

import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.mdelbel.android.listadapterexample.model.Task

class CreateTaskScreen : BottomSheetDialogFragment() {

    private lateinit var titleView: TextView
    private lateinit var saveView: Button

    override fun setupDialog(dialog: Dialog, style: Int) {
        val contentView = View.inflate(context, R.layout.screen_create_task, null)
        dialog.setContentView(contentView)

        titleView = contentView.findViewById(R.id.create_task_input)
        saveView = contentView.findViewById(R.id.create_task_save)

        saveView.setOnClickListener { _ ->
            saveTask()
            dismiss()
        }
    }

    private fun saveTask() {
        val task = Task.create(titleView.text.toString())

        val viewModel = ViewModelProviders.of(activity as FragmentActivity).get(TasksViewModel::class.java)
        viewModel.saveTask(task)
    }
}