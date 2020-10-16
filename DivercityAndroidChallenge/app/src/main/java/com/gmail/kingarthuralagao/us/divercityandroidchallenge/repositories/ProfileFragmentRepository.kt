package com.gmail.kingarthuralagao.us.divercityandroidchallenge.repositories

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.models.User
import org.json.JSONArray
import org.json.JSONObject
import java.io.*

class ProfileFragmentRepository : Repository() {
    val usersListLiveData = MutableLiveData<User>()

    // Mimic a GET call
    fun fetchUser(id : Int, file : File) {
        Thread {
            getUserInfoFromFile(id, FileInputStream(file))
        }.start()
    }

    // Mimic a POST call
    fun updateUserFullName(userId : Int, firstName : String, lastName : String, file : File, editor: SharedPreferences.Editor) {
        Thread {
            val inputStream = FileInputStream(file)
            val usersJsonArray = getUsersInfoFromFile(inputStream)
            var jsonObject = JSONObject()
            var index = 0
            while (index < usersJsonArray.length()) {
                jsonObject = JSONObject(usersJsonArray.get(index).toString())
                if (jsonObject.getInt("id") == userId) {
                    jsonObject.put("first_name", firstName)
                    jsonObject.put("last_name", lastName)
                    break
                }
                index += 1
            }
            usersJsonArray.put(index, jsonObject)
            val user = buildUser(usersJsonArray, userId)
            usersListLiveData.postValue(user)

            // Store full name in local storage
            if (user != null) {
                editor.putString("fullName", "${user.getFirstName()} ${user.getLastName()}")
            }

            // Fake post request to server
            PrintWriter(file).print("")
            writeToFile(usersJsonArray, FileOutputStream(file))
        }.start()
    }

    private fun getUsersInfoFromFile(inputStream: InputStream) : JSONArray {
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
            return jsonArray
        } catch (e :IOException) {
            e.printStackTrace()
            return JSONArray()
        }
    }

    private fun getUserInfoFromFile(id : Int, inputStream: InputStream) {
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
            usersListLiveData.postValue(buildUser(jsonArray, id))
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    // Build User objects from JSON
    private fun buildUser(jsonArray: JSONArray, id : Int) : User? {
        val size  = jsonArray.length()
        for (i in 0 until size) {
            val jsonObject = jsonArray.getJSONObject(i)
            if (jsonObject.getInt("id") == id) {
               return directUserBuilder(jsonObject, User.builder)
            }
        }
        return null
    }
}