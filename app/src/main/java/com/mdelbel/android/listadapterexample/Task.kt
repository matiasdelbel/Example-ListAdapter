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