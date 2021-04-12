package com.example.allsportsscorer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class BasketballMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basketball_main)
        val btn = findViewById<Button>(R.id.basketball_btn)
        val name1 = findViewById<EditText>(R.id.basketball_team_a)
        val name2 = findViewById<EditText>(R.id.basketball_team_b)
        btn.setOnClickListener {
            val intent = Intent(this,BasketballScoring::class.java)
            intent.putExtra(FootballScoring.TEAMA,name1.text.toString())
            intent.putExtra(FootballScoring.TEAMB,name2.text.toString())
            startActivity(intent)

        }
    }
}