package com.darrenfinch.todolist.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.darrenfinch.todolist.CURRENT_FAKE_TIME
import com.darrenfinch.todolist.TASK_ID
import com.darrenfinch.todolist.model.TaskRepository
import com.darrenfinch.todolist.model.room.Task
import com.darrenfinch.todolist.viewmodel.data.TestTasksCreator
import io.mockk.*
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class IncompleteTasksViewModelTest
{
    //region Helper Fields -------------------------------------------------------------------------
    private val TEST_TASKS = TestTasksCreator.getIncompleteTasks()

    private val testTasksLiveData = mockk<MutableLiveData<List<Task>>>()
    private val application = mockk<Application>()
    private val repository = mockk<TaskRepository>(relaxUnitFun = true)
    //endregion Helper Fields ----------------------------------------------------------------------

    private lateinit var SUT: IncompleteTasksViewModel

    //region Set up / Tear down
    @Before
    fun setup()
    {
        SUT = IncompleteTasksViewModel(repository, application)
    }

    //endregion Set up / Tear down

    //region Tests ---------------------------------------------------------------------------------
    @Test
    fun `getTasks() invokes getIncompleteTasks() from repository`()
    {
        stubRepositoryGetIncompleteTasksToReturnEmptyLiveData()
        SUT.getTasks()
        verify { repository.getIncompleteTasks() }
    }

    @Test
    fun `getTasks() returns correct list of tasks from repository`()
    {
        stubRepositoryGetIncompleteTasksToReturnTestTasksLiveData()
        stubTestTasksLiveDataGetValueToReturnTestTasks()
        val tasksLiveData = SUT.getTasks()
        assertThat(tasksLiveData.value, `is`(TEST_TASKS))
    }

    @Test
    fun `completeTask() passes id and current time to repository`()
    {
        val taskIdCapturingSlot = CapturingSlot<Int>()
        val dateOfCompletionCapturingSlot = CapturingSlot<Long>()

        SUT.completeTask(TASK_ID, CURRENT_FAKE_TIME)
        verify { repository.completeTask(capture(taskIdCapturingSlot), capture(dateOfCompletionCapturingSlot)) }
        assertThat(taskIdCapturingSlot.captured, `is`(TASK_ID))
        assertThat(dateOfCompletionCapturingSlot.captured, `is`(CURRENT_FAKE_TIME))
    }

    @Test
    fun `deleteTask() passes id to repository`()
    {
        val taskIdCapturingSlot = CapturingSlot<Int>()
        SUT.deleteTask(TASK_ID)
        verify { repository.deleteTask(capture(taskIdCapturingSlot)) }
        assertThat(taskIdCapturingSlot.captured, `is`(TASK_ID))
    }
    //endregion Tests ------------------------------------------------------------------------------

    //region Helper Classes ------------------------------------------------------------------------

    //endregion Helper Classes ---------------------------------------------------------------------

    //region Helper Methods ------------------------------------------------------------------------
    private fun stubRepositoryGetIncompleteTasksToReturnEmptyLiveData()
    {
        every { repository.getIncompleteTasks() } returns MutableLiveData()
    }
    private fun stubRepositoryGetIncompleteTasksToReturnTestTasksLiveData()
    {
        every { repository.getIncompleteTasks() } returns testTasksLiveData
    }

    private fun stubTestTasksLiveDataGetValueToReturnTestTasks()
    {
        every { testTasksLiveData.value } returns TEST_TASKS
    }
    //endregion Helper Methods ---------------------------------------------------------------------
}