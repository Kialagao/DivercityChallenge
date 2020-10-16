package com.gmail.kingarthuralagao.us.divercityandroidchallenge.viewmodels

import android.app.Application
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.AndroidViewModel
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.R
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.repositories.HomeFragmentRepository
import java.io.IOException

class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val homeFragmentRepository : HomeFragmentRepository by lazy {
        HomeFragmentRepository()
    }

    var userListLiveData = homeFragmentRepository.usersListLiveData

    fun getUsers() {
       try {
           var fileInputStream = getApplication<Application>().openFileInput(getApplication<Application>()
               .resources.getString(R.string.filename))
           homeFragmentRepository.fetchUsers(fileInputStream, null)
       } catch (e : IOException) {
           val assetManager = getApplication<Application>().assets
           val inputStream = assetManager.open("db.txt")

           val fileName = getApplication<Application>().resources.getString(R.string.filename)
           val fileOutputStream = getApplication<Application>().openFileOutput(fileName, MODE_PRIVATE)

           homeFragmentRepository.fetchUsers(inputStream, fileOutputStream)
       }
    }

    fun sortData(sortBy : String, sortOption : String) {
        when (sortBy) {
            getApplication<Application>().resources.getString(R.string.last_name) -> {
                if (sortOption == getApplication<Application>().resources.getString(R.string.ascending)) {
                    userListLiveData.value!!.sortBy { it.getLastName() }
                } else {
                    userListLiveData.value!!.sortByDescending { it.getLastName() }
                }
            }

            getApplication<Application>().getString(R.string.years_of_experience) -> {
                if (sortOption == getApplication<Application>().resources.getString(R.string.ascending)) {
                    userListLiveData.value!!.sortBy { it.getYearsOfExperience() }
                } else {
                    userListLiveData.value!!.sortByDescending { it.getYearsOfExperience() }
                }
            }

            else -> {
                if (sortOption == getApplication<Application>().resources.getString(R.string.ascending)) {
                    userListLiveData.value!!.sortBy { it.getDateOfBirth() }
                } else {
                    userListLiveData.value!!.sortByDescending { it.getDateOfBirth() }
                }
            }
        }
    }
}