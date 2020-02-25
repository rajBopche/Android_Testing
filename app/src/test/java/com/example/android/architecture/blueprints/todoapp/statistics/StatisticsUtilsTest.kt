package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import junit.framework.Assert.assertEquals


import org.junit.Test

class StatisticsUtilsTest {

    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {

        //Create an active task
        val tasks = listOf(
                Task("title", "desc", isCompleted = false)
        )

        //Call your function
        val result = getActiveAndCompletedStats(tasks)


        //Check the result
        assertEquals(result.completedTasksPercent, (0f))
        assertEquals(result.activeTasksPercent, (100f))

    }

    @Test
    fun getActiveAndCompletedStats_noActive_returnsZeroHundred() {

        //Create a completed task  (Given)
        val tasks = listOf(
                Task("title", "desc", isCompleted = true)
        )

        //Call your function (When)
        val result = getActiveAndCompletedStats(tasks)

        //Check the result (then)
        assertEquals(result.activeTasksPercent, 0f)
        assertEquals(result.completedTasksPercent, 100f)

    }

    @Test
    fun getActiveAndCompletedStats_activeAndCompleted_returnsSixtyForty() {

        //Create a completed task  (Given)
        val tasks = listOf(
                Task("title", "desc", isCompleted = true),
                Task("title", "desc", isCompleted = true),
                Task("title", "desc", isCompleted = false),
                Task("title", "desc", isCompleted = false),
                Task("title", "desc", isCompleted = false)
        )

        //Call your function (When)
        val result = getActiveAndCompletedStats(tasks)

        //Check the result (then)
        assertEquals(result.activeTasksPercent, 60f)
        assertEquals(result.completedTasksPercent, 40f)

    }

    @Test
    fun getActiveAndCompletedStats_emptyList_returnsZeroZero() {

        //Create an empty task  (Given)
        val tasks: List<Task> = listOf()

        //Call your function (When)
        val result = getActiveAndCompletedStats(tasks)

        //Check the result (then)
        assertEquals(result.activeTasksPercent, 0f)
        assertEquals(result.completedTasksPercent, 0f)

    }

    @Test
    fun getActiveAndCompletedStats_error_returnsZeroZero() {

        // Create a null task (Given)
        val tasks: List<Task>? = null

        //Call your function (When)
        val result = getActiveAndCompletedStats(tasks)

        //Check the result (Then)
        assertEquals(result.activeTasksPercent, 0f)
        assertEquals(result.completedTasksPercent, 0f)

    }

}