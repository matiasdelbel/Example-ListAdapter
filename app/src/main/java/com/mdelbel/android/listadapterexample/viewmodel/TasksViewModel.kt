package com.mdelbel.android.listadapterexample.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mdelbel.android.listadapterexample.model.Task
import com.mdelbel.android.listadapterexample.model.TaskRepository
import java.util.*

class TasksViewModel : ViewModel() {

    private val repository = TaskRepository()
    //TODO el observer donde deberia estar aca o en la activity
    private val deletionObserver = Observer { o, _ ->

        if ((o as Task).deleted) {
            deleteTask(o)
        } else {
            restoreTask(o)
        }
    }

    private val taskData = MutableLiveData<List<Task>>()
    private val deletionEvent = SingleLiveEvent<Task>()

    fun tasks(): LiveData<List<Task>> {
        return taskData
    }

    fun restoreTasksListener(): SingleLiveEvent<Task> {
        return deletionEvent
    }

    fun stopObserve(task: Task?) { //TODO no me copa del todo
        task?.deleteObserver(deletionObserver)
    }

    fun saveTask(task: Task) {
        repository.save(task)

        task.addObserver(deletionObserver)
        taskData.postValue(repository.search())
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