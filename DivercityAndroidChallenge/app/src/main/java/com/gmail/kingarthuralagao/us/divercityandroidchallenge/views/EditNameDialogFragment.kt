package com.gmail.kingarthuralagao.us.divercityandroidchallenge.views

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.R
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.databinding.DialogFragmentEditNameBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class EditNameDialogFragment : BottomSheetDialogFragment(), View.OnClickListener {

    interface IFinishEditListener {
        fun onFinishEdit(firstName: String, lastName: String)
    }

    companion object {
        fun newInstance(firstName: String, lastName: String) : EditNameDialogFragment {
            val editNameDialogFragment = EditNameDialogFragment()
            val args = Bundle()
            args.putString("firstName", firstName)
            args.putString("lastName", lastName)
            editNameDialogFragment.arguments = args
            return editNameDialogFragment
        }
    }

    private var firstName = ""
    private var lastName = ""
    private var dismissListener : IFinishEditListener? = null
    private lateinit var dialogFragmentEditNameBinding: DialogFragmentEditNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogFragmentEditNameBinding = DialogFragmentEditNameBinding.inflate(layoutInflater)
        dialogFragmentEditNameBinding.editNameToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        firstName = requireArguments().getString("firstName").toString()
        lastName = requireArguments().getString("lastName").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialogFragmentEditNameBinding.firstNameTv.setText(firstName)
        dialogFragmentEditNameBinding.lastNameTv.setText(lastName)
        return dialogFragmentEditNameBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogFragmentEditNameBinding.saveTv.setOnClickListener(this)
        dialogFragmentEditNameBinding.editNameToolbar.setNavigationOnClickListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IFinishEditListener) {
            dismissListener = context
        } else {
            throw RuntimeException("Must implement IDismissListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        dismissListener = null
    }

    override fun onClick(view: View) {
        if (view.id == dialogFragmentEditNameBinding.saveTv.id) {
            dismissListener?.onFinishEdit(
                dialogFragmentEditNameBinding.firstNameTv.text.toString(),
                dialogFragmentEditNameBinding.lastNameTv.text.toString()
            )
        } else {
            dismissListener?.onFinishEdit("", "")
        }
        dismiss()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupFullHeight(bottomSheetDialog)
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet =
            bottomSheetDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
        val bottomSheetBehavior: BottomSheetBehavior<*> =
            BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet.layoutParams
        val windowHeight = getWindowHeight()
        if (layoutParams != null) {
            layoutParams.height = windowHeight
        }
        bottomSheet.layoutParams = layoutParams
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = Resources.getSystem().displayMetrics
        return displayMetrics.heightPixels
    }
}