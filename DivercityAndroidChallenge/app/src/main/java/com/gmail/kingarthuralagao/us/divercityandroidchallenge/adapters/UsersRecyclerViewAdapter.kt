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
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.R
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.databinding.ViewholderUserBinding
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso

class UsersRecyclerViewAdapter(private var myDataSet: MutableList<String>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = javaClass.simpleName
    class UserViewHolder(binding: ViewholderUserBinding) : RecyclerView.ViewHolder(binding.root) {
        var avatarImageView = binding.userIv
        var fullNameTextView  = binding.nameAndEmailLayout.nameTv
        var emailTextView = binding.nameAndEmailLayout.emailTv
        var genderTextView = binding.userPersonalLayout.genderTv
        var yearsOfExperienceTextView = binding.userPersonalLayout.yearsOfExperienceTv
        var dateOfBirthTextView = binding.userPersonalLayout.dobTv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var viewHolderUserBinding = ViewholderUserBinding.inflate(layoutInflater, parent, false)
        Log.i(TAG, "onCreateViewHolder")

        return UserViewHolder(viewHolderUserBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder")
        (holder as UserViewHolder).fullNameTextView.text = "King Arthur Alagao"
        holder.emailTextView.text = "kingarthuralagao@gmail.com"
        holder.genderTextView.text = "Male"
        holder.yearsOfExperienceTextView.text = "5"
        holder.dateOfBirthTextView.text = "Born on September 20, 1998"
        holder.avatarImageView.layoutParams = ConstraintLayout.LayoutParams(
            getScreenWidth() / 4,
            getScreenWidth() / 4
        )
    }

    override fun getItemCount(): Int {
        return 2
    }

    fun setData(newData: MutableList<String>) {
        this.myDataSet = newData
        notifyDataSetChanged()
    }
}

fun getScreenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}

fun getScreenHeight(): Int {
    return Resources.getSystem().displayMetrics.heightPixels
}

fun isPortrait() : Boolean {
    return Resources.getSystem().configuration.orientation == Configuration.ORIENTATION_PORTRAIT
}
