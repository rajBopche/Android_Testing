package com.example.android.architecture.blueprints.todoapp.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.Task

class FakeDataSource(var tasks: MutableList<Task>? = mutableListOf()) : TasksDataSource {
    override fun observeTasks(): LiveData<Result<List<Task>>> {
                return liveData {  }
    }

    override suspend fun getTasks(): Result<List<Task>> {
        tasks?.let {
            return Result.Success(ArrayList(it))
        }
        return Result.Error(Exception("Tasks not found"))
    }

    override suspend fun refreshTasks() {

    }

    override fun observeTask(taskId: String): LiveData<Result<Task>> {
        return liveData {  }
    }

    override suspend fun getTask(taskId: String): Result<Task> {
        return Result.Error(java.lang.Exception("Something went wrong"))
    }

    override suspend fun refreshTask(taskId: String) {

    }

    override suspend fun saveTask(task: Task) {
        tasks?.add(task)
    }

    override suspend fun completeTask(task: Task) {

    }

    override suspend fun completeTask(taskId: String) {

    }

    override suspend fun activateTask(task: Task) {

    }

    override suspend fun activateTask(taskId: String) {

    }

    override suspend fun clearCompletedTasks() {

    }

    override suspend fun deleteAllTasks() {
        tasks?.clear()
    }

    override suspend fun deleteTask(taskId: String) {

    }

}