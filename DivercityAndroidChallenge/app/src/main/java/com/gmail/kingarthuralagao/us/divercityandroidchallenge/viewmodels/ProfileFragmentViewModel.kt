package com.gmail.kingarthuralagao.us.divercityandroidchallenge.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.R
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.repositories.ProfileFragmentRepository
import java.io.File
import java.io.IOException

class ProfileFragmentViewModel(application: Application) : AndroidViewModel(application){
    private val profileFragmentRepository : ProfileFragmentRepository by lazy {
        ProfileFragmentRepository()
    }

    var userLiveData = profileFragmentRepository.usersListLiveData
    private val SHARED_PREFS = "sharedPreference"

    fun getUser(userId : Int) {
        try {
            val fileName = getApplication<Application>().resources.getString(R.string.filename)
            val dir = getApplication<Application>().filesDir
            val file = File(dir, fileName)

            profileFragmentRepository.fetchUser(userId, file)
        } catch (e : IOException) {
            e.printStackTrace()
        }
    }

    fun updateUserFullName(id : Int, firstName : String, lastName : String) {
        val fileName = getApplication<Application>().resources.getString(R.string.filename)
        val dir = getApplication<Application>().filesDir
        val file = File(dir, fileName)
        val sharedPreferences = getApplication<Application>().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val sharedPreferencesEditor = sharedPreferences.edit()
        profileFragmentRepository.updateUserFullName(id, firstName, lastName, file, sharedPreferencesEditor)
    }
}