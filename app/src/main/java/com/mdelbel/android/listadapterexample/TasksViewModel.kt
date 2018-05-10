package com.mdelbel.android.listadapterexample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import java.util.*

class TasksViewModel : ViewModel(), Observer {

    private val taskData = MutableLiveData<List<Task>>()
    //private var tasks: MutableList<Task> = TaskProvider().createTasks().toMutableList()

    fun getTasks(): LiveData<List<Task>> {
        //if (users == null) {
        //    users = MutableLiveData<List<Users>>()
        //    loadUsers()
        //}
        return taskData
    }

    /*private var users: MutableLiveData<List<User>>? = null

    fun getUsers(): LiveData<List<User>> {
        if (users == null) {
            users = MutableLiveData<List<Users>>()
            loadUsers()
        }
        return users
    }

    private fun loadUsers() {
        // Do an asynchronous operation to fetch users.
    }


        for (task in tasks) { //TODO mock for now
            task.addObserver(this)
        }
        */

    override fun update(o: Observable?, arg: Any?) {
        //TODO stop observe arg
        //tasks.remove(arg)
        //val updatedList = tasks.toList()
        //TODO todoListAdapter.submitList(updatedList)
    }
}