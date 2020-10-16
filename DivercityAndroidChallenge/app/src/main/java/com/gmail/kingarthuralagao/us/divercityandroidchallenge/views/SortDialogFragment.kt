package com.gmail.kingarthuralagao.us.divercityandroidchallenge.views

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.adapters.getScreenWidth
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.databinding.DialogFragmentSortBinding
import java.lang.RuntimeException

class SortDialogFragment : DialogFragment(), View.OnClickListener {

    interface ISortByListener {
        fun onSortOptionPicked(sortBy : String, sortOption : String)
    }

    private lateinit var viewBinding: DialogFragmentSortBinding
    private var sortByListener : ISortByListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DialogFragmentSortBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.okTv.setOnClickListener(this)
        viewBinding.cancelTv.setOnClickListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ISortByListener) {
            sortByListener = context
        } else {
            throw RuntimeException("Must Implement SortByListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        sortByListener = null
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = (getScreenWidth() * 3) / 4
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            dialog.window?.setLayout(width, height)
        }
    }

    override fun onClick(v: View) {
        if (v.id == viewBinding.cancelTv.id) {
            dismiss()
        } else {
            val sortBy =
                when(viewBinding.sortByRadioGroup.checkedRadioButtonId) {
                    viewBinding.lastName.id -> viewBinding.lastName.tag.toString()
                    viewBinding.yearsExperience.id -> viewBinding.yearsExperience.tag.toString()
                    else -> viewBinding.dob.tag.toString()
                }

            val sortOption =
                if (viewBinding.sortOptionsRadioGroup.checkedRadioButtonId == viewBinding.ascending.id) {
                    viewBinding.ascending.tag.toString()
                } else {
                    viewBinding.descending.tag.toString()
                }

            sortByListener?.onSortOptionPicked(sortBy , sortOption)
            dismiss()
        }
    }
}