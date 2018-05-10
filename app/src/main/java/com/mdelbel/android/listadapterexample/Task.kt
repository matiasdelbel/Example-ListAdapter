package com.mdelbel.android.listadapterexample

import java.util.*

data class Task(val id: Int, val title: String) : Observable() {

    var deleted = false

    fun delete() {
        deleted = true
        setChanged()
        notifyObservers(this)
    }
}

class TaskProvider {

    fun createTasks(): List<Task> {
        return listOf(
                Task(1, "Buy milk"),
                Task(2, "Go to work"),
                Task(3, "Go to the gym"),
                Task(4, "Sleep"),
                Task(5, "Some other cool activity"))
    }
}