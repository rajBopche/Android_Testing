package com.example.android.architecture.blueprints.todoapp.tasks

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.ServiceLocator
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.TaskRepository
import com.example.android.architecture.blueprints.todoapp.source.FakeAndroidTestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import timber.log.Timber

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class TasksFragmentTest {

    private lateinit var repository: TaskRepository
    private lateinit var navController: NavController

    @Before
    fun initRepository() {
        repository = FakeAndroidTestRepository()
        ServiceLocator.tasksRepository = repository
        navController = mock(NavController::class.java)
    }

    @Test
    fun clickTask_navigateToDetailFragmentOne() {
        runBlockingTest{
            repository.saveTask(Task("TITLE1", "DESCRIPTION1", false, "id1"))
            repository.saveTask(Task("TITLE2", "DESCRIPTION2", true, "id2"))

            // GIVEN - On the home screen
            val scenario = launchFragmentInContainer<TasksFragment>(Bundle(), R.style.AppTheme)


            scenario.onFragment {
                Navigation.setViewNavController(it.view!!, navController)
            }

            // WHEN - Click on the first list item of recyclerview
            onView(withId(R.id.tasks_list))
                    .perform(RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                            hasDescendant(withText("TITLE1")), click()))


            // THEN - Verify that we navigate to the first detail screen
            verify(navController).navigate(TasksFragmentDirections.actionTasksFragmentToTaskDetailFragment("id1"))
        }
    }

    @Test
    fun clickAddTaskButton_navigateToAddEditFragment() = runBlockingTest {

        // GIVEN - On the home screen
        val scenario = launchFragmentInContainer<TasksFragment>(Bundle(), R.style.AppTheme)

        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        //WHEN - Click on the add task button
        onView(withId(R.id.add_task_fab)).perform(click())

        //THEN - Verify that we navigate to the add edit fragment screen
        verify(navController).navigate(TasksFragmentDirections.actionTasksFragmentToAddEditTaskFragment(null, getApplicationContext<Context>().getString(R.string.add_task)))
    }

    @After
    fun cleanupDb() = runBlockingTest {
        ServiceLocator.resetRepository()
    }

}