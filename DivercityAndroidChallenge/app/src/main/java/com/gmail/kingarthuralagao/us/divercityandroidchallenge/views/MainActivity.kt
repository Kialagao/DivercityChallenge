package com.gmail.kingarthuralagao.us.divercityandroidchallenge.views

import android.content.res.AssetManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gmail.kingarthuralagao.us.divercityandroidchallenge.R
import org.json.JSONArray
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var str: String? = ""
        try {
            /*
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
            Log.d(TAG, jsonObject.toString())*/


            val input = openFileInput("fakeUserdatabase.txt")
            val inputStreamReader = InputStreamReader(input)
            val inputBuffer = CharArray(100)
            var charRead: Int
            while (inputStreamReader.read(inputBuffer).also { charRead = it } > 0) {
                val readString = String(inputBuffer, 0, charRead)
                str += readString
            }

            val jsonObject = JSONArray(str)
            Log.d(TAG, jsonObject.toString())
            /*
            val fileName = "fakeUserdatabase.txt"
            val fileOutputStream = openFileOutput(fileName, MODE_PRIVATE)
            val byte = jsonObject.toString().toByteArray()
            fileOutputStream.write(byte)
            fileOutputStream.close()*/
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }
    }
}
