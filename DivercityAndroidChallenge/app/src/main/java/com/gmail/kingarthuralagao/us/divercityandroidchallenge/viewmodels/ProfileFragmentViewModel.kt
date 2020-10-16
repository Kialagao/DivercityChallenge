package com.gmail.kingarthuralagao.us.divercityandroidchallenge.viewmodels

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.repositories.ProfileFragmentRepository
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class ProfileFragmentViewModel(application: Application) : AndroidViewModel(application){
    private val profileFragmentRepository : ProfileFragmentRepository by lazy {
        ProfileFragmentRepository()
    }

    var userLiveData = profileFragmentRepository.usersListLiveData
    private val SHARED_PREFS = "sharedPreference"

    fun getUser(userId : Int) {
        try {
            var fileInputStream = getApplication<Application>().openFileInput("FakeUserDB.txt")

            profileFragmentRepository.fetchUsers(userId, fileInputStream, null)
        } catch (e : IOException) {
            val assetManager = getApplication<Application>().assets
            val inputStream = assetManager.open("db.txt")

            val fileName = "FakeUserDB.txt"
            val fileOutputStream = getApplication<Application>().openFileOutput(fileName,
                Context.MODE_PRIVATE
            )
            profileFragmentRepository.fetchUsers(userId, inputStream, fileOutputStream)
        }
    }

    fun updateUserFullName(id : Int, firstName : String, lastName : String) {
        val fileName = "FakeUserDB.txt"
        val dir = getApplication<Application>().filesDir
        val file = File(dir, fileName)
        val sharedPreferences = getApplication<Application>().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val sharedPreferencesEditor = sharedPreferences.edit()
        profileFragmentRepository.updateUserFullName(id, firstName, lastName, file, sharedPreferencesEditor)
    }
}