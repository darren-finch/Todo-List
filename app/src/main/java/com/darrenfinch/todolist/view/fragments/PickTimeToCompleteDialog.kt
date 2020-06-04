package com.darrenfinch.todolist.view.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.darrenfinch.todolist.R
import com.darrenfinch.todolist.databinding.FragmentPickTimeToCompleteBinding
import com.darrenfinch.todolist.model.room.TimeUnit

class PickTimeToCompleteDialog : DialogFragment() {
    private lateinit var binding: FragmentPickTimeToCompleteBinding
    private var listener: Listener? = null
    private var selectedTimeUnit = TimeUnit.defaultUnit
    private var selectedTimeToComplete = 1

    companion object {
        fun newInstance(timeToComplete: Int, timeUnit: TimeUnit): PickTimeToCompleteDialog {
            val dialogFragment = PickTimeToCompleteDialog()
            dialogFragment.selectedTimeToComplete = timeToComplete
            dialogFragment.selectedTimeUnit = timeUnit
            return dialogFragment
        }
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_pick_time_to_complete,
            null,
            false
        )
//        savedInstanceState?.let { getValuesFromBundle(savedInstanceState) }
        setupSpinnerValues()
        setupSpinnerOnItemSelectedListener()
        setupTimeToCompleteEditText()

        return makeDialog()
    }

    //    //I use a bundle because using nav args will be too complex.
//    private fun getValuesFromBundle(savedInstanceState: Bundle)
//    {
//        try
//        {
//            selectedTimeUnit = savedInstanceState.getSerializable(TIME_TO_COMPLETE_UNIT_BUNDLE_STRING) as TimeUnit
//            selectedTime = savedInstanceState.getInt(TIME_TO_COMPLETE_BUNDLE_STRING)
//        }
//        catch (e: Exception)
//        {
//            e.printStackTrace()
//        }
//    }
    private fun setupSpinnerValues() {
        val timeUnitStringList = getAllTimeUnitsAsStrings()
        val timeUnitArrayAdapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                timeUnitStringList
            )
        }
        timeUnitArrayAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.estimatedTTCUnitSpinner.adapter = timeUnitArrayAdapter
        binding.estimatedTTCUnitSpinner.setSelection(selectedTimeUnit.ordinal)
    }

    private fun getAllTimeUnitsAsStrings() =
        TimeUnit.timeUnitToStringValues.keys.toList().map { it.toString() }

    private fun setupSpinnerOnItemSelectedListener() {
        binding.estimatedTTCUnitSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //This will fail if the array of strings given to the parent is incorrect.
                    selectedTimeUnit =
                        TimeUnit.fromString(parent?.getItemAtPosition(position).toString())
                }
            }
    }

    private fun makeDialog(): AlertDialog {
        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setTitle(getString(R.string.time_to_complete))
            .setPositiveButton(getString(android.R.string.ok)) { _, _ ->
                onPositiveButtonSelected()
            }
            .setNegativeButton(getString(android.R.string.cancel)) { _, _ -> }
            .show()
    }

    private fun onPositiveButtonSelected() {
        val estimatedTTC = getEstimatedTTC()
        val estimatedTTCUnit = getEstimatedTTCUnit()
        listener?.onTimeToCompleteSelected(estimatedTTC, estimatedTTCUnit)
    }

    private fun setupTimeToCompleteEditText() {
        binding.estimatedTTCEditText.setText(selectedTimeToComplete.toString())
    }

    override fun onPause() {
        super.onPause()
        dismiss()
    }

    private fun getEstimatedTTCUnit() = selectedTimeUnit
    private fun getEstimatedTTC() = binding.estimatedTTCEditText.text.toString().toInt()

    interface Listener {
        fun onTimeToCompleteSelected(estimatedTTC: Int, estimatedTTCUnit: TimeUnit)
    }
}