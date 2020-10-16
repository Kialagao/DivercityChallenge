package com.gmail.kingarthuralagao.us.divercityandroidchallenge.views

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.R
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
    }

    fun login(v : View) {
        if (v.id == mainActivityBinding.loginBtn.id) {
            closeKeyboard(v)
            val id = when (mainActivityBinding.emailTv.text.toString()) {
                "jpenddreth0@test.gov" -> 1
                "ganderson@senate.gov" -> 2
                "ngonzo@imageshack.us" -> 3
                "pvalek3@vk.com" -> 4
                else -> {
                    val toast = Toast.makeText(this, "Incorrect email/password", Toast.LENGTH_LONG)
                    toast.show()
                    return
                }
            }
            val intent = Intent(this, UserDashboardActivity::class.java)
            intent.putExtra(resources.getString(R.string.id), id)
            startActivity(intent)
        } else {
            Log.d(javaClass.simpleName, "Unknown view clicked")
        }
    }

    private fun closeKeyboard(v : View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
    }
}
