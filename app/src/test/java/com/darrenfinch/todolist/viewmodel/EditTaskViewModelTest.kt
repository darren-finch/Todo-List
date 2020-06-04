package com.darrenfinch.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.darrenfinch.todolist.model.TaskRepository
import com.darrenfinch.todolist.model.room.Task
import com.darrenfinch.todolist.viewmodel.data.TestTasksCreator
import org.junit.Before
import org.junit.Test

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat

import io.mockk.*

//region Constants -----------------------------------------------------------------------------
private const val TASK_ID = 1
//endregion Constants --------------------------------------------------------------------------

class EditTaskViewModelTest
{
    //region Helper Fields -------------------------------------------------------------------------
    private val testTask = TestTasksCreator.getTask()
    private val testTaskLiveData = mockk<LiveData<Task>>()

    private val repository = mockk<TaskRepository>(relaxUnitFun = true)
    private val application = mockk<Application>(relaxUnitFun = true)
    //endregion Helper Fields ----------------------------------------------------------------------

    private lateinit var SUT: EditTaskViewModel

    //region Set up / Tear down
    @Before
    fun setup()
    {
        SUT = EditTaskViewModel(repository, application)
    }
    //endregion Set up / Tear down

    //region Tests ---------------------------------------------------------------------------------
    @Test
    fun `getTask() passes taskId to repository`()
    {
        val taskIdCapturingSlot = CapturingSlot<Int>()
        every { repository.getTask(capture(taskIdCapturingSlot)) } returns MutableLiveData()
        SUT.getTaskFromRepository(TASK_ID)
        assertThat(taskIdCapturingSlot.captured, `is`(TASK_ID))
    }

    @Test
    fun `getTask() returns correct list of tasks`()
    {
        stubTestTaskLiveDataToReturnTestTask()
        every { repository.getTask(any()) } returns testTaskLiveData
        assertThat(SUT.getTaskFromRepository(TASK_ID).value, `is`(testTask))
    }

    @Test
    fun `insertTask() passes task to repository`()
    {
        val taskCapturingSlot = CapturingSlot<Task>()
        every { repository.insertTask(capture(taskCapturingSlot)) } answers { }
        SUT.insertTask(testTask)
        assertThat(taskCapturingSlot.captured, `is`(testTask))
    }

    @Test
    fun `updateTask() passes task to repository`()
    {
        val taskCapturingSlot = CapturingSlot<Task>()
        every { repository.updateTask(capture(taskCapturingSlot)) } answers { }
        SUT.updateTask(testTask)
        assertThat(taskCapturingSlot.captured, `is`(testTask))
    }
    //endregion Tests ------------------------------------------------------------------------------

    //region Helper Classes ------------------------------------------------------------------------

    //endregion Helper Classes ---------------------------------------------------------------------

    //region Helper Methods ------------------------------------------------------------------------
    private fun stubTestTaskLiveDataToReturnTestTask()
    {
        every { testTaskLiveData.value } returns testTask
    }
    //endregion Helper Methods ---------------------------------------------------------------------
}