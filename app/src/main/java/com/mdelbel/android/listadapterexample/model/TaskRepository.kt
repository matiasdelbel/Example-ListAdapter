package com.mdelbel.android.listadapterexample.model

class TaskRepository {

    private val tasks: MutableList<Task> = mutableListOf()

    fun save(task: Task) {
        tasks.add(task)
    }

    fun delete(task: Task) {
        tasks.remove(task)
    }

    fun taskAsList(): List<Task> {
        return tasks.toList()
    }
}