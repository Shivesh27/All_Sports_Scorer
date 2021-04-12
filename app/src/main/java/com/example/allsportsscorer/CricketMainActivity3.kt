package com.example.allsportsscorer

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog

class CricketMainActivity3 : AppCompatActivity() {
    private var cricket_TeamName : String =""
    private var opposite : String =""
    lateinit var cricket_btnTeamA: Button
    lateinit var cricket_btnTeamB: Button
    lateinit var cricket_btnBat:Button
    lateinit var cricket_btnBowl:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cricket_main3)
        val str = intent.getStringExtra("firstTeam")
        val str1 =intent.getStringExtra("secondTeam")
        val str2 =intent.getStringExtra("overs")

        cricket_btnTeamA = findViewById(R.id.cricket_btn_team_a)
        cricket_btnTeamB = findViewById(R.id.cricket_btn_team_B)
        cricket_btnBat = findViewById(R.id.cricket_btn_bat)
        cricket_btnBowl = findViewById(R.id.cricket_btn_bowl)
        cricket_btnBat.isEnabled=false
        cricket_btnBowl.isEnabled=false

        cricket_btnTeamA.text=str
        cricket_btnTeamB.text=str1
        cricket_btnTeamB.setOnClickListener{
            cricket_TeamName = cricket_btnTeamB.text.toString()
            opposite = cricket_btnTeamA.text.toString()
            cricket_btnTeamB.isEnabled = false
            cricket_btnTeamA.isEnabled = false
            cricket_btnBat.isEnabled=true
            cricket_btnBowl.isEnabled=true
        }
        cricket_btnTeamA.setOnClickListener{
            cricket_TeamName = cricket_btnTeamA.text.toString()
            opposite= cricket_btnTeamB.text.toString()
            cricket_btnTeamA.isEnabled = false
            cricket_btnTeamB.isEnabled = false
            cricket_btnBat.isEnabled=true
            cricket_btnBowl.isEnabled=true
        }
        //cricket_btnBat.isEnabled=false
        //cricket_btnBowl.isEnabled=false
    }
    override fun onBackPressed() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setMessage("Do you want to Exit?")
        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ -> //if user pressed "yes", then he is allowed to exit from application
            val a: Intent = Intent(this,MainMenu::class.java)
            startActivity(a)
        })
        builder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, _ -> //if user select "No", just cancel this dialog and continue with app
            dialog.cancel()
        })
        val alert: AlertDialog = builder.create()
        alert.show()
    }

    fun performBat(view: View){
        val str = intent.getStringExtra("overs")
        intent =Intent(this,CricketMainActivity4::class.java)
        intent.putExtra("str",cricket_TeamName)
        intent.putExtra("overs",str)
        intent.putExtra("opposite",opposite)
        startActivity(intent)
    }
    fun performBowl(view: View){
        val str = intent.getStringExtra("overs")
        intent= Intent(this,CricketMainActivity4::class.java)
        intent.putExtra("str",opposite)
        intent.putExtra("overs",str)
        intent.putExtra("opposite",cricket_TeamName)
        startActivity(intent)
    }
}