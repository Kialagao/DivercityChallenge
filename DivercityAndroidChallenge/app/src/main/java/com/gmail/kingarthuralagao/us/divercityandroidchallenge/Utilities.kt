package com.gmail.kingarthuralagao.us.divercityandroidchallenge

import org.json.JSONArray
import org.json.JSONObject

// Fake backend
val firstUserMap by lazy {
    mutableMapOf<String, Any>(
        "id" to 1,
        "first_name" to "Jeannette",
        "last_name" to "Pemberthy",
        "email" to "jpenddreth0@test.gov",
        "gender" to "Female",
        "year_experience" to 12,
        "avatar" to "https://www.shutterstock.com/image-vector/lion-head-logo-vector-template-illustration-743978617",
        "dob" to "1980-01-29"
    )
}

val secondUserMap by lazy {
    mutableMapOf<String, Any>(
        "id" to 2,
        "first_name" to "Giovaini",
        "last_name" to "Anderson",
        "email" to "ganderson@senate.gov",
        "gender" to "Male",
        "year_experience" to 8,
        "avatar" to "https://image.shutterstock.com/z/stock-vector-vector-tiger-head-face-for-retro-logos-emblems-badges-labels-template-and-t-shirt-vintage-736036015.jpg",
        "dob" to "1992-05-18"
    )
}

val thirdUserMap by lazy {
    mutableMapOf<String, Any>(
        "id" to 3,
        "first_name" to "Noelle",
        "last_name" to "Gonzalez",
        "email" to "ngonzo@imageshack.us",
        "gender" to "Female",
        "year_experience" to 2,
        "avatar" to "https://www.shutterstock.com/image-vector/black-mamba-uncoiled-head-three-quarter-237781042",
        "dob" to "1979-11-22"
    )
}

val lastUserMap by lazy {
    mutableMapOf<String, Any>(
        "id" to 4,
        "first_name" to "Patrick",
        "last_name" to "Valek",
        "email" to "pvalek3@vk.com",
        "gender" to "Male",
        "year_experience" to 22,
        "avatar" to "https://www.shutterstock.com/image-vector/monochrome-portrait-brown-bear-looking-ahead-1316901974",
        "dob" to "1967-12-12"
    )
}
// End of fake backend


fun getJsonData() {
    val jsonArray = JSONArray()
    for (i in 1..4) {
        jsonArray.put(getJsonObject(i))
    }
}

fun getJsonObject(id : Int): JSONObject {
    val userMap = when(id) {
        1 -> firstUserMap
        2 -> secondUserMap
        3 -> thirdUserMap
        else -> lastUserMap
    }

    val jsonObject = JSONObject()
    for (entry in userMap.entries) {
        jsonObject.put(entry.key, entry.value)
    }
    return jsonObject
}

fun postJsonData() {

}


/*
val jsonArray = "[{
"id": 1,
    "first_name": "Jeanette",
    "last_name": "Pemberthy",
    "email": "jpenddreth0@test.gov",
    "gender": "Female",
    "year_experience": 12,
    "avatar":
    "https://www.shutterstock.com/image-vector/lion-head-logo-vector-template-illustration
    -743978617",
    “dob": "1980-01-29"
}, {
    "id": 2,
    "first_name": "Giovaini",
    "last_name": “Anderson",
    "email": "ganderson@senate.gov",
    "gender": "Male",
    "year_experience": 8,
    "avatar":
    "https://image.shutterstock.com/z/stock-vector-vector-tiger-head-face-for-retro-logos-
    emblems-badges-labels-template-and-t-shirt-vintage-736036015.jpg",

    "dob": "1992-05-18"
}, {
    "id": 3,
    "first_name": "Noelle",
    "last_name": “Gonzalez",
    "email": "ngonzo@imageshack.us",
    "gender": "Female",
    "year_experience": 2,
    "avatar":
    "https://www.shutterstock.com/image-vector/black-mamba-uncoiled-head-three-quarter-237
    781042",
    "dob": "1979-11-22"
}, {
    "id": 4,
    "first_name": "Patrick",
    "last_name": "Valek",
    "email": "pvalek3@vk.com",
    "gender": "Male",
    "year_experience": 22,
    "avatar":
    "https://www.shutterstock.com/image-vector/monochrome-portrait-brown-bear-looking-ahea
    d-1316901974",
    "dob": "1967-12-12"
}]"*/