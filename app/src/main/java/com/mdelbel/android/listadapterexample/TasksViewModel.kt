package com.mdelbel.android.listadapterexample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mdelbel.android.listadapterexample.model.Task
import com.mdelbel.android.listadapterexample.model.TaskRepository
import java.util.*

class TasksViewModel : ViewModel() {

    private val repository = TaskRepository()
    private val deletionObserver = Observer { o, _ -> deleteTask(o as Task) }

    private val taskData = MutableLiveData<List<Task>>()

    fun tasks(): LiveData<List<Task>> {
        return taskData
    }

    fun saveTask(task: Task) {
        task.addObserver(deletionObserver)
        repository.save(task)

        taskData.postValue(repository.taskAsList())
    }

    fun deleteTask(task: Task) {
        task.deleteObserver(deletionObserver)
        repository.delete(task)

        taskData.postValue(repository.taskAsList())
    }
}