package com.gmail.kingarthuralagao.us.divercityandroidchallenge.models

class User {
    private lateinit var fullName : String
    private lateinit var dateOfBirth : String
    private lateinit var email : String
    private lateinit var gender : String
    private lateinit var avatar : String
    private lateinit var yearsOfExperience : String

    fun getFullName() : String {
        return fullName
    }

    fun getDateOfBirth() : String {
        return dateOfBirth
    }

    fun getEmail() : String {
        return email
    }

    fun getGender() : String {
        return gender
    }

    fun getYearsOfExperience() : String {
        return yearsOfExperience
    }

    fun getAvatar() : String {
        return avatar
    }

    override fun toString(): String {
        return ""
    }

    class UserBuilder {
        private lateinit var firstName : String
        private lateinit var lastName: String
        private lateinit var dateOfBirth : String
        private lateinit var email : String
        private lateinit var gender : String
        private lateinit var yearsOfExperience : String
        private lateinit var avatar : String
        private lateinit var user: User

        fun withFirstName(fname: String): UserBuilder {
            firstName = fname
            return this
        }

        fun withLastName(lname: String): UserBuilder {
            lastName = lname
            return this
        }

        fun withBirthday(date: String): UserBuilder {
            val month = getMonth(date.substring(5, 7))
            dateOfBirth = "Born on $month ${date.substring(8, date.length)}, ${date.substring(0, 4)}"
            return this
        }

        fun withEmail(email : String): UserBuilder {
            this.email = email
            return this
        }

        fun withGender(gender : String) : UserBuilder {
            this.gender = gender
            return this
        }

        fun withYearsOfExperience(years : Int) : UserBuilder {
            yearsOfExperience = "$years ${if (years > 1) "years" else "year"} of experience"
            return this
        }

        fun withAvatar(imageUrl : String) : UserBuilder {
            avatar = imageUrl
            return this
        }

        fun build(): User {
            user = User()
            user.fullName = ("$firstName $lastName")
            user.email = email
            user.gender = gender
            user.yearsOfExperience = yearsOfExperience
            user.dateOfBirth = dateOfBirth
            user.avatar = avatar
            return user
        }

        private fun getMonth(substring: String): String {
            return when (substring) {
                "01" -> "January"
                "02" -> "February"
                "03" -> "March"
                "04" -> "April"
                "05" -> "May"
                "06" -> "June"
                "07" -> "July"
                "08" -> "August"
                "09" -> "September"
                "10" -> "October"
                "11" -> "November"
                else -> "December"
            }
        }
    }

    companion object {
        //Get builder instance
        val builder: UserBuilder get() = UserBuilder()
    }
}