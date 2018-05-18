package com.mdelbel.android.listadapterexample.model

import java.util.*
import java.util.concurrent.atomic.AtomicInteger

data class Task(val id: Int, val title: String) : Observable() {

    companion object Factory {
        private val idGenerator = AtomicInteger()
        fun create(title: String): Task = Task(idGenerator.addAndGet(1), title)
    }

    var deleted = false

    fun delete() {
        deleted = true
        notifyObserver()
    }

    fun restore() {
        deleted = false
        notifyObserver()
    }

    private fun notifyObserver() {
        setChanged()
        notifyObservers(this)
    }
}