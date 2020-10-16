package com.gmail.kingarthuralagao.us.divercityandroidchallenge.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.models.User
import org.json.JSONArray
import org.json.JSONObject
import java.io.*

class ProfileFragmentRepository {
    private val TAG = javaClass.simpleName
    val usersListLiveData = MutableLiveData<User>()

    // Mimic network call
    fun fetchUsers(id : Int, inputStream: InputStream, outputStream: FileOutputStream?) {
        Thread {
            getUserInfoFromFile(id, inputStream, outputStream)
        }.start()
    }

    // Mimic network call
    fun updateUserFullName(userId : Int, firstName : String, lastName : String, file : File) {
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
            usersListLiveData.postValue(buildUser(usersJsonArray, userId))
            PrintWriter(file).print("") // Erase contents of file
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

            Log.d(TAG, str.toString())
            val jsonArray = JSONArray(str)
            Log.d(TAG, jsonArray.toString())
            inputStreamReader.close()
            return jsonArray
        } catch (e :IOException) {
            e.printStackTrace()
            return JSONArray()
        }
    }

    private fun getUserInfoFromFile(id : Int, inputStream: InputStream, fileOutputStream: FileOutputStream?) {
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
            usersListLiveData.postValue(buildUser(jsonArray, id))

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
    private fun buildUser(jsonArray: JSONArray, id : Int) : User? {
        val size  = jsonArray.length()
        for (i in 0 until size) {
            val jsonObject = jsonArray.getJSONObject(i)
            if (jsonObject.getInt("id") == id) {
               return buildUser(jsonObject, User.builder)
            }
        }
        return null
    }

    private fun buildUser(jsonObject: JSONObject, builder: User.UserBuilder): User {
        return builder
            .withId(jsonObject.getInt("id"))
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