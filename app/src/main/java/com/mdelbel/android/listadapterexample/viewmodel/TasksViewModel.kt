package com.mdelbel.android.listadapterexample.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mdelbel.android.listadapterexample.model.Task
import com.mdelbel.android.listadapterexample.model.TaskRepository
import java.util.*

class TasksViewModel : ViewModel(), Observer {

    private val repository = TaskRepository()

    private val taskData = MutableLiveData<List<Task>>()
    private val deletionEvent = SingleLiveEvent<Task>()

    override fun update(o: Observable?, arg: Any?) {
        if ((o as Task).deleted) {
            deleteTask(o)
        } else {
            restoreTask(o)
        }
    }

    fun tasks(): LiveData<List<Task>> {
        return taskData
    }

    fun saveTask(task: Task) {
        repository.save(task)
        taskData.postValue(repository.search())
    }

    fun removedTask(): SingleLiveEvent<Task> {
        return deletionEvent
    }

    private fun deleteTask(task: Task) {
        repository.delete(task)

        taskData.postValue(repository.search())
        deletionEvent.setValue(task)
    }

    private fun restoreTask(task: Task) {
        repository.save(task)
        taskData.postValue(repository.search())
    }
}