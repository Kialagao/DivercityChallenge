package com.gmail.kingarthuralagao.us.divercityandroidchallenge.viewmodels

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.res.AssetManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.models.User
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.repositories.HomeFragmentRepository
import java.io.IOException

class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val homeFragmentRepository : HomeFragmentRepository by lazy {
        HomeFragmentRepository()
    }

    var userListLiveData = homeFragmentRepository.usersListLiveData

    fun getUsers() {
       try {
           var fileInputStream = getApplication<Application>().openFileInput("FakeUserDB.txt")

           homeFragmentRepository.fetchUsers(fileInputStream, null)
       } catch (e : IOException) {
           val assetManager = getApplication<Application>().assets
           val inputStream = assetManager.open("db.txt")

           val fileName = "FakeUserDB.txt"
           val fileOutputStream = getApplication<Application>().openFileOutput(fileName, MODE_PRIVATE)
           homeFragmentRepository.fetchUsers(inputStream, fileOutputStream)
       }
    }
}