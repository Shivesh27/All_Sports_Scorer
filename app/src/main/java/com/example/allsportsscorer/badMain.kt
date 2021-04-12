package com.example.allsportsscorer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class badMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bad_main)
        val continuebutton : Button = findViewById(R.id.bad_btn)

        continuebutton.setOnClickListener()
        {
            val edittext1: EditText = findViewById(R.id.bad_team_a)
            val editext2 : EditText = findViewById(R.id.bad_team_b)
            val sets = findViewById<EditText>(R.id.bad_sets)
            val teama = edittext1.text.toString()
            val teamb = editext2.text.toString()

            val intent = Intent(this,badScoring::class.java)
            intent.putExtra(badScoring.TEAMA,teama)
            intent.putExtra(badScoring.TEAMB,teamb)
            intent.putExtra(badScoring.SETS,sets.text.toString())

            startActivity(intent)
        }
    }
}