package com.gmail.kingarthuralagao.us.divercityandroidchallenge.repositories

import androidx.lifecycle.MutableLiveData
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.models.User
import org.json.JSONArray
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class HomeFragmentRepository : Repository() {
    val usersListLiveData = MutableLiveData<MutableList<User>>()

    // Mimic a network call
    fun fetchUsers(inputStream: InputStream, outputStream: FileOutputStream?) {
        Thread {
            getUsersInfoFromFile(inputStream, outputStream)
        }.start()
    }

    private fun getUsersInfoFromFile(inputStream: InputStream, fileOutputStream: FileOutputStream?) {
        var str: String? = ""
        try {
            val inputStreamReader = InputStreamReader(inputStream)
            val inputBuffer = CharArray(100)
            var charRead: Int
            while (inputStreamReader.read(inputBuffer).also { charRead = it } > 0) {
                val readString = String(inputBuffer, 0, charRead)
                str += readString
            }

            val jsonArray = JSONArray(str)
            inputStreamReader.close()
            usersListLiveData.postValue(buildUsers(jsonArray))

            if (fileOutputStream != null) {
                writeToFile(jsonArray, fileOutputStream)
            }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    // Build User objects from JSON
    private fun buildUsers(jsonArray: JSONArray) : MutableList<User> {
        val usersList = mutableListOf<User>()
        val size  = jsonArray.length()
        for (i in 0 until size) {
            val jsonObject = jsonArray.getJSONObject(i)
            val user = directUserBuilder(jsonObject, User.builder)
            usersList.add(user)
        }
        return usersList
    }
}