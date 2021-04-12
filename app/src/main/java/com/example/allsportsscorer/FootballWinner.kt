package com.example.allsportsscorer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FootballWinner : AppCompatActivity() {
    companion object {
        const val WINNER = "WINNER"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_football_winner)
        val winner = findViewById<TextView>(R.id.football_winner)
        winner.text = intent.getStringExtra(WINNER)
        val btn = findViewById<Button>(R.id.football_back)
        btn.setOnClickListener{
            val intent = Intent(this,MainMenu::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        val a = Intent(this,MainMenu::class.java)
        startActivity(a)
    }
}