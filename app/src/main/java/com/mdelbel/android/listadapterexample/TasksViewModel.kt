package com.mdelbel.android.listadapterexample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import java.util.*

class TasksViewModel : ViewModel(), Observer {

    private val tasks: MutableList<Task> = ArrayList()
    private val taskData = MutableLiveData<List<Task>>()

    fun getTasks(): LiveData<List<Task>> {
        return taskData
    }

    fun saveTask(task: Task) {
        task.addObserver(this)
        tasks.add(task)

        taskData.postValue(tasks)
    }

    override fun update(task: Observable?, arg: Any?) {
        (task as Task).deleteObserver(this)
        tasks.remove(task)

        taskData.postValue(tasks.toList())
    }
}