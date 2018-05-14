package com.mdelbel.android.listadapterexample.model

import java.util.*
import java.util.concurrent.atomic.AtomicInteger

data class Task(val id: Int, val title: String) : Observable() {

    companion object Factory {

        private val idGenerator = AtomicInteger(1)
        fun create(title: String): Task = Task(idGenerator.addAndGet(1), title)
    }

    var deleted = false

    fun delete() {
        deleted = true
        setChanged()
        notifyObservers(this)
    }
}