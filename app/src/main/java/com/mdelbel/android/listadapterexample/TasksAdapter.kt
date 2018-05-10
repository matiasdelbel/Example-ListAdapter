package com.mdelbel.android.listadapterexample

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class TasksAdapter : ListAdapter<Task, TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TaskViewHolder(inflater.inflate(R.layout.item_task, parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(task: Task) {
        itemView.findViewById<TextView>(R.id.item_task_id).text = task.id.toString()
        itemView.findViewById<TextView>(R.id.item_task_title).text = task.title
        itemView.findViewById<ImageView>(R.id.item_task_delete).setOnClickListener { _ ->
            task.delete()
        }
    }
}

class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task?, newItem: Task?): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: Task?, newItem: Task?): Boolean {
        return oldItem == newItem
    }
}