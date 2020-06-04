package com.darrenfinch.todolist.view.helpers

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import com.darrenfinch.todolist.BR
import com.darrenfinch.todolist.model.room.TimeUnit
import com.darrenfinch.todolist.model.room.Task

class ObservableTask : BaseObservable()
{
    var dirty = false
        private set

    init
    {
        addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback()
        {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int)
            {
                dirty = true
            }
        })
    }

    var id: Int = 0
    @get:Bindable
    var name: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }
    @get:Bindable
    var estimatedTTC: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.estimatedTTC)
        }
    @get:Bindable
    var estimatedTTCUnit: TimeUnit = TimeUnit.defaultUnit
        set(value) {
            field = value
            notifyPropertyChanged(BR.estimatedTTCUnit)
        }
    @get:Bindable
    var scheduledDate: Long = System.currentTimeMillis()
        set(value) {
            field = value
            notifyPropertyChanged(BR.scheduledDate)
        }
    @get:Bindable
    var dateOfCompletion: Long = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.dateOfCompletion)
        }
    @get:Bindable
    var description: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }

    fun set(task: Task)
    {
        id = task.id
        name = task.name
        estimatedTTC = task.estimatedTTC
        estimatedTTCUnit = task.estimatedTTCUnit
        scheduledDate = task.scheduledDate
        dateOfCompletion = task.dateOfCompletion
        description = task.description
    }
    fun get() : Task
    {
        return Task(
            id = id,
            name = name,
            estimatedTTC = estimatedTTC,
            estimatedTTCUnit = estimatedTTCUnit,
            scheduledDate = scheduledDate,
            dateOfCompletion = dateOfCompletion,
            description = description
        )
    }
}