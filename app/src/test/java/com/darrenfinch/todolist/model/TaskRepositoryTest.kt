package com.darrenfinch.todolist.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.darrenfinch.todolist.model.room.Task
import com.darrenfinch.todolist.model.room.TaskDao
import com.darrenfinch.todolist.viewmodel.data.TestTasksCreator

import org.junit.Before
import org.junit.Test

import io.mockk.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule

//region Constants -----------------------------------------------------------------------------
private const val TASK_ID = 1
//endregion Constants --------------------------------------------------------------------------

@ExperimentalCoroutinesApi
class TaskRepositoryTest
{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    //region Helper Fields -------------------------------------------------------------------------
    private val incompleteTestTaskList = TestTasksCreator.getIncompleteTasks()
    private val completedTestTaskList = TestTasksCreator.getCompletedTasks()
    private val testTask = TestTasksCreator.getTask()

    private val taskLiveData = mockk<LiveData<Task>>()
    private val taskListLiveData = mockk<LiveData<List<Task>>>()

    private val taskDao = mockk<TaskDao>(relaxUnitFun = true)
    private val testIOScope = CoroutineScope(Dispatchers.Unconfined)
    //endregion Helper Fields ----------------------------------------------------------------------

    private lateinit var SUT: TaskRepository

    //region Set up / Tear down
    @Before
    fun setup()
    {
        SUT = TaskRepository(testIOScope, taskDao)
    }
    //endregion Set up / Tear down

    //region Tests ---------------------------------------------------------------------------------
    @Test
    fun `getIncompleteTasks() invokes DAO getIncompleteTasks()`()
    {
        stubDAOGetIncompleteTasksToReturnEmptyLiveData()
        SUT.getIncompleteTasks()
        verify { taskDao.getIncompleteTasks() }
        confirmVerified(taskDao)
    }

    @Test
    fun `getIncompleteTasks() returns correct list of completed tasks from DAO`()
    {
        stubTaskListLiveDataValueToReturnIncompleteTaskList()
        stubDAOGetIncompleteTasksToReturnTaskListLiveData()
        assertThat(SUT.getIncompleteTasks().value, `is`(incompleteTestTaskList))
    }

    @Test
    fun `getCompleteTasks() invokes DAO getCompleteTasks()`()
    {
        stubDAOGetCompletedTasksToReturnEmptyLiveData()
        SUT.getCompletedTasks()
        verify { taskDao.getCompletedTasks() }
        confirmVerified(taskDao)
    }

    @Test
    fun `getCompleteTasks() returns correct list of incomplete tasks from DAO`()
    {
        stubTaskListLiveDataValueToReturnCompletedTaskList()
        stubDAOGetCompletedTasksToReturnTaskListLiveData()
        assertThat(SUT.getCompletedTasks().value, `is`(completedTestTaskList))
    }

    @Test
    fun `getTask() invokes DAO getTask() and passes taskId`()
    {
        val taskIdCapturingSlot = CapturingSlot<Int>()
        every { taskDao.getTask(capture(taskIdCapturingSlot)) } returns MutableLiveData()
        SUT.getTask(TASK_ID)
        assertThat(taskIdCapturingSlot.captured, `is`(TASK_ID))
    }

    @Test
    fun `getTask() returns correct task from DAO`()
    {
        stubTaskLiveDataValueToReturnTask()
        every { taskDao.getTask(any()) } returns taskLiveData
        assertThat(SUT.getTask(TASK_ID).value, `is`(testTask))
    }

    @Test
    fun `insertTask() invokes DAO insertTask() and passes task`()
    {
        val taskCapturingSlot = CapturingSlot<Task>()
        SUT.insertTask(testTask)
        coVerify { taskDao.insertTask(capture(taskCapturingSlot)) }
        confirmVerified(taskDao)
        assertThat(taskCapturingSlot.captured, `is`(testTask))
    }

    @Test
    fun `updateTask() invokes DAO updateTask() and passes task`()
    {
        val taskCapturingSlot = CapturingSlot<Task>()
        SUT.updateTask(testTask)
        coVerify { taskDao.updateTask(capture(taskCapturingSlot)) }
        confirmVerified(taskDao)
        assertThat(taskCapturingSlot.captured, `is`(testTask))
    }

    @Test
    fun `deleteTask() invokes DAO deleteTask() and passes taskId`()
    {
        val taskIdCapturingSlot = CapturingSlot<Int>()
        SUT.deleteTask(TASK_ID)
        coVerify { taskDao.deleteTask(capture(taskIdCapturingSlot)) }
        confirmVerified(taskDao)
        assertThat(taskIdCapturingSlot.captured, `is`(TASK_ID))
    }

    @Test
    fun `completeTask() invokes DAO completeTask() and passes taskId`()
    {
        val taskIdCapturingSlot = CapturingSlot<Int>()
        SUT.completeTask(TASK_ID)
        coVerify { taskDao.completeTask(capture(taskIdCapturingSlot)) }
        confirmVerified(taskDao)
        assertThat(taskIdCapturingSlot.captured, `is`(TASK_ID))
    }

    @Test
    fun `uncompleteTask() invokes DAO uncompleteTask() and passes taskId`()
    {
        val taskIdCapturingSlot = CapturingSlot<Int>()
        SUT.uncompleteTask(TASK_ID)
        coVerify { taskDao.uncompleteTask(capture(taskIdCapturingSlot)) }
        confirmVerified(taskDao)
        assertThat(taskIdCapturingSlot.captured, `is`(TASK_ID))
    }
    //endregion Tests ------------------------------------------------------------------------------

    //region Helper Classes ------------------------------------------------------------------------

    //endregion Helper Classes ---------------------------------------------------------------------

    //region Helper Methods ------------------------------------------------------------------------
    private fun stubDAOGetIncompleteTasksToReturnEmptyLiveData()
    {
        every { taskDao.getIncompleteTasks() } returns MutableLiveData()
    }
    private fun stubDAOGetIncompleteTasksToReturnTaskListLiveData()
    {
        every { taskDao.getIncompleteTasks() } returns taskListLiveData
    }

    private fun stubDAOGetCompletedTasksToReturnEmptyLiveData()
    {
        every { taskDao.getCompletedTasks() } returns MutableLiveData()
    }
    private fun stubDAOGetCompletedTasksToReturnTaskListLiveData()
    {
        every { taskDao.getCompletedTasks() } returns taskListLiveData
    }

    private fun stubTaskLiveDataValueToReturnTask()
    {
        every { taskLiveData.value } returns testTask
    }

    private fun stubTaskListLiveDataValueToReturnIncompleteTaskList()
    {
        every { taskListLiveData.value } returns incompleteTestTaskList
    }
    private fun stubTaskListLiveDataValueToReturnCompletedTaskList()
    {
        every { taskListLiveData.value } returns completedTestTaskList
    }
    //endregion Helper Methods ---------------------------------------------------------------------
}