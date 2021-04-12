package com.example.allsportsscorer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class FootballMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_football_main)
        val btn : Button = findViewById(R.id.football_btn)
        val edittext1 : EditText = findViewById(R.id.football_team_a)
        val edittext2 : EditText = findViewById(R.id.football_team_b)
        val time = findViewById<EditText>(R.id.football_time)


        btn.setOnClickListener{
            val team_a  = edittext1.text.toString()
            val team_b = edittext2.text.toString()
            val intent = Intent(this,FootballScoring::class.java)
            intent.putExtra(FootballScoring.TEAMA,team_a)
            intent.putExtra(FootballScoring.TEAMB,team_b)
            intent.putExtra(FootballScoring.TIME,time.text.toString())
            startActivity(intent)

        }
    }
}