package com.gmail.kingarthuralagao.us.divercityandroidchallenge.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.R
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.databinding.FragmentProfileBinding
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.models.User
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.viewmodels.ProfileFragmentViewModel
import java.lang.RuntimeException
import java.util.*
import kotlin.properties.Delegates

class ProfileFragment : Fragment() {

    interface IEditNameListener {
        fun onEditName(firstName: String, lastName: String)
    }

    companion object {
        fun newInstance(id: Int) : ProfileFragment {
            val profileFragment = ProfileFragment()
            val args = Bundle()
            args.putInt("id", id)
            profileFragment.arguments = args
            return profileFragment
        }
    }

    private lateinit var profileBinding : FragmentProfileBinding
    private var userId by Delegates.notNull<Int>()
    private lateinit var profileFragmentViewModel : ProfileFragmentViewModel
    private var editNameListener : IEditNameListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileBinding = FragmentProfileBinding.inflate(layoutInflater)
        userId = arguments?.getInt(resources.getString(R.string.id)) ?: 1
        profileFragmentViewModel = ViewModelProvider(this).get(ProfileFragmentViewModel::class.java)
        profileFragmentViewModel.userLiveData.observe(this, userObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileFragmentViewModel.getUser(userId)
        return profileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileBinding.editNameBtn.setOnClickListener {
            editNameListener?.onEditName(profileFragmentViewModel.userLiveData.value!!.getFirstName(),
                profileFragmentViewModel.userLiveData.value!!.getLastName())
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IEditNameListener) {
            editNameListener = context
        } else {
            throw RuntimeException("Must implement IEditNameListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        editNameListener = null
    }

    private val userObserver = Observer<User> { it ->
        if (it != null) {
            setViewContent(it)
        } else {
            Log.d(javaClass.simpleName, "Empty List")
        }
    }

    private fun setViewContent(it: User) {
        profileBinding.userNameAndEmail.emailTv.text = it.getEmail()
        profileBinding.userNameAndEmail.nameTv.text = "${it.getFirstName()} ${it.getLastName()}"
        profileBinding.userPersonalLayout.dobTv.text = setDateOfBirth(it.getDateOfBirth())
        profileBinding.userPersonalLayout.genderTv.text = it.getGender()
        profileBinding.userPersonalLayout.yearsOfExperienceTv.text =
            "${it.getYearsOfExperience()} ${if (it.getYearsOfExperience() > 1) "years" else "year"} of experience"
        Glide
            .with(profileBinding.userIv.context)
            .load(it.getAvatar())
            .circleCrop()
            .into(profileBinding.userIv)

    }

    fun updateUserFullName(firstName : String, lastName : String) {
        profileFragmentViewModel.updateUserFullName(userId, firstName, lastName)
    }

    private fun setDateOfBirth(dateOfBirth: Date): CharSequence? {
        val calendar = Calendar.getInstance()
        calendar.time = dateOfBirth
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val year = calendar.get(Calendar.YEAR)
        val monthName = getMonthName(month)
        return "Born on $monthName $day, $year"
    }

    private fun getMonthName(month: Int): String {
        return when (month) {
            1 -> "January"
            2 -> "February"
            3 -> "March"
            4 -> "April"
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "August"
            9 -> "September"
            10 -> "October"
            11 -> "November"
            else -> "December"
        }
    }
}