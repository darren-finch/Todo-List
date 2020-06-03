package com.darrenfinch.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.darrenfinch.todolist.model.TaskRepository
import com.darrenfinch.todolist.model.room.Task
import com.darrenfinch.todolist.viewmodel.data.TestTasksCreator
import org.junit.Before
import org.junit.Test

import org.hamcrest.CoreMatchers.`is`

//This is all you need to use MockK if that's what you want to use.
import io.mockk.*
import org.junit.Assert

//region Constants -----------------------------------------------------------------------------
private const val TASK_ID = 0
//endregion Constants --------------------------------------------------------------------------

class CompletedTasksViewModelTest
{

    //region Helper Fields -------------------------------------------------------------------------
    private val TEST_TASKS = TestTasksCreator.getSampleCompletedTasks()

    private val testTasksLiveData = mockk<MutableLiveData<List<Task>>>()
    private val application = mockk<Application>()
    private val repository = mockk<TaskRepository>(relaxUnitFun = true)
    //endregion Helper Fields ----------------------------------------------------------------------

    private lateinit var SUT: CompletedTasksViewModel

    //region Set up / Tear down
    @Before
    fun setup()
    {
        SUT = CompletedTasksViewModel(repository, application)
    }
    //endregion Set up / Tear down

    //region Tests ---------------------------------------------------------------------------------
    @Test
    fun `getTask() invokes getCompleteTasks() from repository`()
    {
        stubRepositoryGetCompletedTasksToReturnEmptyLiveData()
        SUT.getTasks()
        verify { repository.getIncompleteTasks() }
        confirmVerified(repository)
    }

    @Test
    fun `getTasks() returns correct list of tasks from repository`()
    {
        stubRepositoryGetCompletedTasksToReturnTestTasksLiveData()
        stubTestTasksLiveDataGetValueToReturnTestTasks()
        val tasksLiveData = SUT.getTasks()
        Assert.assertThat(tasksLiveData.value, `is`(TEST_TASKS))
    }

    @Test
    fun `completeTask() passes id to repository`()
    {
        val taskIdCapturingSlot = CapturingSlot<Int>()
        every { repository.uncompleteTask(capture(taskIdCapturingSlot)) } answers { }
        SUT.uncompleteTask(TASK_ID)
        Assert.assertThat(taskIdCapturingSlot.captured, `is`(TASK_ID))
    }
    //endregion Tests ------------------------------------------------------------------------------

    //region Helper Classes ------------------------------------------------------------------------

    //endregion Helper Classes ---------------------------------------------------------------------

    //region Helper Methods ------------------------------------------------------------------------
    private fun stubRepositoryGetCompletedTasksToReturnEmptyLiveData()
    {
        every { repository.getCompletedTasks() } returns MutableLiveData()
    }
    private fun stubRepositoryGetCompletedTasksToReturnTestTasksLiveData()
    {
        every { repository.getCompletedTasks() } returns testTasksLiveData
    }

    private fun stubTestTasksLiveDataGetValueToReturnTestTasks()
    {
        every { testTasksLiveData.value } returns TEST_TASKS
    }
    //endregion Helper Methods ---------------------------------------------------------------------
}