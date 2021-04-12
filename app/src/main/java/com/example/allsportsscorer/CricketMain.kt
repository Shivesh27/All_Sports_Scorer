package com.example.allsportsscorer

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class CricketMain : AppCompatActivity() {
    lateinit var cricket_edtTxtOvers :EditText
    lateinit var cricket_edtTxtTeamA :EditText
    lateinit var cricket_edtTxtTeamB :EditText
    lateinit var cricket_txtA :TextView
    lateinit var cricket_txtAus :TextView
    lateinit var cricket_btnB :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cricket_main)
        cricket_edtTxtOvers = findViewById<EditText>(R.id.cricket_overs)
        cricket_edtTxtTeamA = findViewById<EditText>(R.id.cricket_team_a)
        cricket_edtTxtTeamB = findViewById<EditText>(R.id.cricket_team_b)
        cricket_txtA = findViewById<TextView>(R.id.cricket_team_a_title)
        cricket_txtAus = findViewById<TextView>(R.id.cricket_team_b_title)
        cricket_btnB = findViewById<Button>(R.id.cricket_btn)

        cricket_edtTxtTeamA.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cricket_txtA.text = s
            }
        })
        cricket_edtTxtTeamB.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                cricket_txtAus.text = s
            }
        })
        cricket_btnB.setOnClickListener {
            if (validate()) {
                intent = Intent(this, CricketMainActivity3::class.java)
                val firstTeam = cricket_edtTxtTeamA.text.toString()
                intent.putExtra("firstTeam", firstTeam)
                val overs = cricket_edtTxtOvers.text.toString()
                val secondTeam = cricket_edtTxtTeamB.text.toString()
                intent.putExtra("secondTeam", secondTeam)
                intent.putExtra("overs", overs)
                startActivity(intent)
            }
        }
    }
    private fun validate(): Boolean {
        val cricket_TeamA = cricket_edtTxtTeamA.text.toString()
        val cricket_TeamB = cricket_edtTxtTeamB.text.toString()
        val cricket_over = cricket_edtTxtOvers.text.toString()
        if (cricket_TeamA.isEmpty()) {
            cricket_edtTxtTeamA.error = "enter the team name"
            return false
        } else if (cricket_TeamB.isEmpty()) {
            cricket_edtTxtTeamB.error = "enter the team name"
            return false
        } else if (cricket_over.isEmpty()) {
            cricket_edtTxtOvers.error = "enter the overs"
            return false
        } else {
            return true
        }
    }
}