package com.example.android.architecture.blueprints.todoapp.data.source

import com.example.android.architecture.blueprints.todoapp.data.Result
import com.example.android.architecture.blueprints.todoapp.data.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultTasksRepositoryTest {

    private val task1 = Task("Title1", "Description1")
    private val task2 = Task("Title2", "Description2")
    private val task3 = Task("Title3", "Description3")
    private val remoteTasks = listOf(task1, task2).sortedBy { it.id }
    private val localTasks = listOf(task3).sortedBy { it.id }
    private val newTasks = listOf(task3).sortedBy { it.id }

    private lateinit var tasksRemoteDataSource: com.example.android.architecture.blueprints.todoapp.data.source.FakeDataSource
    private lateinit var tasksLocalDataSource: com.example.android.architecture.blueprints.todoapp.data.source.FakeDataSource

    // Class under test
    private lateinit var tasksRepository: DefaultTasksRepository

    @Before
    fun setUp() {
        tasksRemoteDataSource = com.example.android.architecture.blueprints.todoapp.data.source.FakeDataSource(remoteTasks.toMutableList())
        tasksLocalDataSource = com.example.android.architecture.blueprints.todoapp.data.source.FakeDataSource(localTasks.toMutableList())

        tasksRepository = DefaultTasksRepository(
                tasksRemoteDataSource, tasksLocalDataSource, Dispatchers.Unconfined
        )
    }

    @Test
    fun getTasks_requestsAllTasksFromRemoteDataSource() = runBlocking {

        val tasks = tasksRepository.getTasks(true) as Result.Success
        assertThat(tasks.data, IsEqual(remoteTasks))
    }
}