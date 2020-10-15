package com.gmail.kingarthuralagao.us.divercityandroidchallenge.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.models.User
import org.json.JSONArray
import org.json.JSONObject
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class HomeFragmentRepository {
    private val TAG = javaClass.simpleName
    val usersListLiveData = MutableLiveData<MutableList<User>>()

    // Mimic network call
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

            Log.d(TAG, str.toString())
            val jsonArray = JSONArray(str)
            Log.d(TAG, jsonArray.toString())
            inputStreamReader.close()
            usersListLiveData.postValue(buildUsers(jsonArray))

            if (fileOutputStream != null) {
                writeToFile(jsonArray, fileOutputStream)
                Log.d(TAG, "WritingToFile")
            }
        } catch (ioException: IOException) {
            Log.e(TAG, ioException.message.toString())
        }
    }

    // Save json data to a File (Fake backend server)
    private fun writeToFile(jsonArray: JSONArray, fileOutputStream: FileOutputStream) {
        val byte = jsonArray.toString().toByteArray()
        fileOutputStream.write(byte)
        fileOutputStream.close()
    }

    // Build User objects from JSON
    private fun buildUsers(jsonArray: JSONArray) : MutableList<User> {
        val usersList = mutableListOf<User>()
        val size  = jsonArray.length()
        for (i in 0 until size) {
            val jsonObject = jsonArray.getJSONObject(i)
            val user = buildUser(jsonObject, User.builder)
            usersList.add(user)
        }
        return usersList
    }

    private fun buildUser(jsonObject: JSONObject, builder: User.UserBuilder): User {
        return builder
            .withFirstName(jsonObject.getString("first_name"))
            .withLastName(jsonObject.getString("last_name"))
            .withBirthday(jsonObject.getString("dob"))
            .withEmail(jsonObject.getString("email"))
            .withGender(jsonObject.getString("gender"))
            .withYearsOfExperience(jsonObject.getInt("year_experience"))
            .withAvatar(jsonObject.getString("avatar"))
            .build()
    }
        /*
       var str: String? = ""
       try {

           val assetManager: AssetManager = assets
           val inputStream = assetManager.open("db.txt")
           val inputStreamReader = InputStreamReader(inputStream)
           val inputBuffer = CharArray(100)
           var charRead: Int
           while (inputStreamReader.read(inputBuffer).also { charRead = it } > 0) {
               val readString = String(inputBuffer, 0, charRead)
               str += readString
           }

           val jsonObject = JSONArray(str)
           Log.d(TAG, jsonObject.toString())

           val input = openFileInput("userdb.txt")
           val inputStreamReader = InputStreamReader(input)
           val inputBuffer = CharArray(100)
           var charRead: Int
           while (inputStreamReader.read(inputBuffer).also { charRead = it } > 0) {
               val readString = String(inputBuffer, 0, charRead)
               str += readString
           }

           val jsonObject = JSONArray(str)
           Log.d(TAG, jsonObject.toString())
           val fileName = "fakedb2.txt"
           val fileOutputStream = openFileOutput(fileName, MODE_PRIVATE)
           val byte = jsonObject.toString().toByteArray()
           fileOutputStream.write(byte)
           fileOutputStream.close()
       } catch (ioe: IOException) {
           ioe.printStackTrace()
       }*/
}