# Example-ListAdapter

<p align="center">
  <img src="https://media.giphy.com/media/jyuur7gXUCfAfR4kZr/giphy.gif"/>
</p>

## Overview
[Support Library 27.1.0](https://developer.android.com/topic/libraries/support-library/revisions#27-1-0) introduced new API:  ListAdapter. ListAdapter for RecyclerView make it easier to compute list diffs on a background thread. These can help your RecyclerView animate content changes automatically, with minimal work on the UI thread. They use DiffUtil under the hood.

## Implementation
First you need to extend you recylcer view's adapter from `ListAdapter`
```kotlin

class TasksAdapter : ListAdapter<Task, TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return TaskViewHolder(inflater.inflate(R.layout.item_task, parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
```

`ListAdapter`'s constructor receives `DiffUtil.ItemCallback<>` as parameter. This callback it is use internally to check if the item list are the same or if its content may changed.

```kotlin
class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task?, newItem: Task?): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: Task?, newItem: Task?): Boolean {
        return oldItem == newItem
    }
}
```

