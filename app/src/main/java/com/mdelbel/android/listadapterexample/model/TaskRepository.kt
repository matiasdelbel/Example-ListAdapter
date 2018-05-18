package com.mdelbel.android.listadapterexample.model

class TaskRepository {

    private val tasks: MutableList<Task> = mutableListOf()

    fun search(): List<Task> {
        return tasks.sortedBy { it.id }
    }

    fun save(task: Task) {
        tasks.add(task)
    }

    fun delete(task: Task) {
        tasks.remove(task)
    }
}