package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {


    private lateinit var tasksViewModel: TasksViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUpViewModel() {
        tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
    }


    //the boilerplate version
    /*@Test
    fun addNewTask_setsNewTaskEvent() {


        // Given a fresh TasksViewModel
        val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())


        // Then the new task event is triggered
        // TODO test LiveData


        // Create observer - no need for it to do anything!
        val observer = Observer<Event<Unit>> {}

        try {

            // Observe the LiveData forever
            tasksViewModel.newTaskEvent.observeForever(observer)

            // When adding a new task
            tasksViewModel.addNewTask()


            // Then the new task event is triggered
            val value = tasksViewModel.newTaskEvent.value


            assertThat(value?.getContentIfNotHandled(), not(nullValue()))

        } finally {
            tasksViewModel.newTaskEvent.removeObserver(observer)
        }
    }*/

    //non-boilerplate version
    @Test
    fun addNewTask_setsNewTaskEvent() {
        // Given a fresh ViewModel
        val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())

        // When adding a new task
        tasksViewModel.addNewTask()

        // Then the new task event is triggered
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
        assertThat(value.getContentIfNotHandled(), not(nullValue()))
    }

    @Test
    fun setFilterAllTasks_tasksAddViewVisible() {

        // Given a fresh ViewModel
        val taskViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())


        // When the filter type is ALL_TASKS
        taskViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then the "Add task" action is visible
        assertThat(taskViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(true))
    }

}