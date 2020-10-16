package com.gmail.kingarthuralagao.us.divercityandroidchallenge.repositories

import com.gmail.kingarthuralagao.us.divercityandroidchallenge.models.User
import org.json.JSONArray
import org.json.JSONObject
import java.io.FileOutputStream

open class Repository {
    // Save json data to a File (Fake backend server)
    fun writeToFile(jsonArray: JSONArray, fileOutputStream: FileOutputStream) {
        val byte = jsonArray.toString().toByteArray()
        fileOutputStream.write(byte)
        fileOutputStream.close()
    }

    fun directUserBuilder(jsonObject: JSONObject, builder: User.UserBuilder): User {
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
}