package com.gmail.kingarthuralagao.us.divercityandroidchallenge.views

import android.app.Activity
import android.content.Intent
import android.content.res.AssetManager
import android.graphics.Color
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.R
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    private lateinit var mainActivityBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

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

            val jsonArray= JSONArray(str)
            Log.d(TAG, jsonArray.toString())

            /*
            val input = openFileInput("testDB3.txt")
            val inputStreamReader = InputStreamReader(input)
            val inputBuffer = CharArray(100)
            var charRead: Int
            while (inputStreamReader.read(inputBuffer).also { charRead = it } > 0) {
                val readString = String(inputBuffer, 0, charRead)
                str += readString
            }

            val jsonArray = JSONArray(str)
            Log.d(TAG, jsonArray.toString())


            val jsonObject = JSONObject(jsonArray.get(0).toString())
            jsonObject.put("first_name", "King")
            jsonObject.put("last_name", "Alagao")
            jsonArray.put(0, jsonObject)

            Log.d(TAG, jsonArray.toString())
            */
            val dir = filesDir
            val file = File(dir, "FakeUserDB.txt")
            PrintWriter(file).print("")

            val fileName = "FakeUserDB.txt"
            val fileOutputStream = openFileOutput(fileName, MODE_PRIVATE)
            val byte = jsonArray.toString().toByteArray()
            fileOutputStream.write(byte)
            fileOutputStream.close()
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }*/
    }

    fun login(v : View) {
        if (v.id == mainActivityBinding.loginBtn.id) {
            closeKeyboard(v)
            var id = when (mainActivityBinding.emailTv.text.toString()) {
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
            Log.d("TAG", "Unknown view clicked")
        }
    }

    fun closeKeyboard(v : View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
    }
}
