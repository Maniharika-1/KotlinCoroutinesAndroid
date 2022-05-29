package com.example.coroutineskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCount = findViewById<Button>(R.id.btnCount)
        val tvCount = findViewById<TextView>(R.id.tvCount)
        val btnDownloadUserData = findViewById<Button>(R.id.btnDownloadUserData)

        btnCount.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                tvCount.text = count++.toString()
                Log.i("MyTagMain", "$count executing in ${Thread.currentThread().name}")
            }
        }
        btnDownloadUserData.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {
                //downloadUserData()
                val tvUserMessage = findViewById<TextView>(R.id.tvUserMessage)
                tvUserMessage.text = UserDataManager2().getTotalUserCount().toString()
            }
        }
    }

    private suspend fun downloadUserData() {
        val tvUserMessage = findViewById<TextView>(R.id.tvUserMessage)

        for (i in 1..5000) {

            withContext(Dispatchers.Main) {
                tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
            }

        }
    }
}