package com.gmail.kingarthuralagao.us.divercityandroidchallenge.models

import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class User {
    private var id by Delegates.notNull<Int>()
    private lateinit var firstName : String
    private lateinit var lastName : String
    private lateinit var dateOfBirth : Date
    private lateinit var email : String
    private lateinit var gender : String
    private lateinit var avatar : String
    private var yearsOfExperience by Delegates.notNull<Int>()

    fun getUserId() : Int {
        return id
    }

    fun getFirstName() : String {
        return firstName
    }

    fun getLastName() : String {
        return lastName
    }

    fun getDateOfBirth() : Date {
        return dateOfBirth
    }

    fun getEmail() : String {
        return email
    }

    fun getGender() : String {
        return gender
    }

    @JvmName("getYearsOfExperience1")
    fun getYearsOfExperience() : Int {
        return yearsOfExperience
    }

    fun getAvatar() : String {
        return avatar
    }

    class UserBuilder {
        private var id by Delegates.notNull<Int>()
        private lateinit var firstName : String
        private lateinit var lastName: String
        private lateinit var dateOfBirth : Date
        private lateinit var email : String
        private lateinit var gender : String
        private var yearsOfExperience by Delegates.notNull<Int>()
        private lateinit var avatar : String
        private lateinit var user: User

        fun withId(id : Int) : UserBuilder {
            this.id = id
            return this
        }

        fun withFirstName(fName: String): UserBuilder {
            firstName = fName
            return this
        }

        fun withLastName(lName: String): UserBuilder {
            lastName = lName
            return this
        }

        fun withBirthday(date: String): UserBuilder {
            val sdf = SimpleDateFormat("yyy/dd/yyyy")
            dateOfBirth = sdf.parse("${date.substring(5, 7)}/${date.substring(8, date.length)}/${date.substring(0, 4)}")
            return this
        }

        fun withEmail(email: String): UserBuilder {
            this.email = email
            return this
        }

        fun withGender(gender: String) : UserBuilder {
            this.gender = gender
            return this
        }

        fun withYearsOfExperience(years: Int) : UserBuilder {
            yearsOfExperience = years
            return this
        }

        fun withAvatar(imageUrl: String) : UserBuilder {
            avatar = imageUrl
            return this
        }

        fun build(): User {
            user = User()
            user.firstName = firstName
            user.lastName = lastName
            user.email = email
            user.gender = gender
            user.yearsOfExperience = yearsOfExperience
            user.dateOfBirth = dateOfBirth
            user.avatar = avatar
            return user
        }
    }

    companion object {
        //Get builder instance
        val builder: UserBuilder get() = UserBuilder()
    }
}