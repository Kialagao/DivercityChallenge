package com.gmail.kingarthuralagao.us.divercityandroidchallenge.adapters

import android.content.res.Configuration
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.R
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.databinding.ViewholderUserBinding
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.models.User
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso
import java.util.*

class UsersRecyclerViewAdapter(private var usersDataSet: MutableList<User>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class UserViewHolder(binding: ViewholderUserBinding) : RecyclerView.ViewHolder(binding.root) {
        var avatarImageView = binding.userIv
        var fullNameTextView  = binding.nameAndEmailLayout.nameTv
        var emailTextView = binding.nameAndEmailLayout.emailTv
        var genderTextView = binding.userPersonalLayout.genderTv
        var yearsOfExperienceTextView = binding.userPersonalLayout.yearsOfExperienceTv
        var dateOfBirthTextView = binding.userPersonalLayout.dobTv
    }

    private val TAG = javaClass.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var viewHolderUserBinding = ViewholderUserBinding.inflate(layoutInflater, parent, false)

        return UserViewHolder(viewHolderUserBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = usersDataSet[position]
        (holder as UserViewHolder).fullNameTextView.text = "${user.getFirstName()} ${user.getLastName()}"
        holder.emailTextView.text = user.getEmail()
        holder.genderTextView.text = user.getGender()
        holder.yearsOfExperienceTextView.text =
            "${user.getYearsOfExperience()} ${if (user.getYearsOfExperience() > 1) "years" else "year"} of experience"
        holder.dateOfBirthTextView.text = setDateOfBirth(user.getDateOfBirth())
        holder.avatarImageView.layoutParams = ConstraintLayout.LayoutParams(
            getScreenWidth() / 4,
            getScreenWidth() / 4
        )
        Glide
            .with(holder.avatarImageView.context)
            .load(user.getAvatar())
            .circleCrop()
            .into(holder.avatarImageView)
    }

    override fun getItemCount(): Int {
        return usersDataSet.size
    }

    private fun setDateOfBirth(dateOfBirth: Date): CharSequence? {
        val calendar = Calendar.getInstance()
        calendar.time = dateOfBirth
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val year = calendar.get(Calendar.YEAR)
        val monthName = getMonthName(month)
        return "Born on $monthName $day, $year"
    }

    fun setData(newData: MutableList<User>) {
        this.usersDataSet = newData
        notifyDataSetChanged()
    }

    private fun getMonthName(month: Int): String {
        return when (month) {
            1 -> "January"
            2 -> "February"
            3 -> "March"
            4 -> "April"
            5 -> "May"
            6 -> "June"
            7 -> "July"
            8 -> "August"
            9 -> "September"
            10 -> "October"
            11 -> "November"
            else -> "December"
        }
    }
}

fun getScreenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}