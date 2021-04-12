package com.example.allsportsscorer

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class BasketballWinner : AppCompatActivity() {
    companion object
    {
        const val WON = "WON"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basketball_winner)
        val winner = findViewById<TextView>(R.id.bas_winner)
        winner.text = intent.getStringExtra(WON)

        val btn = findViewById<Button>(R.id.bas_back)
        btn.setOnClickListener {
            val mintent = Intent(this,MainMenu::class.java)
            startActivity(mintent)
        }
    }

    override fun onBackPressed() {
        val a = Intent(this,MainMenu::class.java)
        startActivity(a)
    }
}