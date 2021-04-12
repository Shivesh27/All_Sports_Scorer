package com.example.allsportsscorer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class badWinner : AppCompatActivity() {
    companion object
    {
        const val WON = "WON"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bad_winner)
        val winner = findViewById<TextView>(R.id.bad_winner)
        winner.text = intent.getStringExtra(WON)
        val back = findViewById<Button>(R.id.bad_back)
        back.setOnClickListener{
            val intent = Intent(this,MainMenu::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        val a = Intent(this,MainMenu::class.java)
        startActivity(a)
    }
}