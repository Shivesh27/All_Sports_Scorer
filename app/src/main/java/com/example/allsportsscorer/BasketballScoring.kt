package com.example.allsportsscorer

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AlertDialog.*

class BasketballScoring : AppCompatActivity() {
    companion object
    {
        const val TEAMA = "TEAMA"
        const val TEAMB = "TEAMB"
    }
    lateinit var name1 :TextView
    lateinit var name2 :TextView


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basketball_scoring)

        val adda1: Button = findViewById(R.id.basketball_team_a_plus1)
        adda1.setOnClickListener{adda1()}

        val adda2: Button = findViewById(R.id.basketball_team_a_plus2)
        adda2.setOnClickListener{adda2()}

        val adda3: Button = findViewById(R.id.basketball_team_a_plus3)
        adda3.setOnClickListener{adda3()}

        val addb1: Button = findViewById(R.id.basketball_team_b_plus1)
        addb1.setOnClickListener{addb1()}

        val addb2: Button = findViewById(R.id.basketball_team_b_plus2)
        addb2.setOnClickListener{addb2()}

        val addb3: Button = findViewById(R.id.basketball_team_b_plus3)
        addb3.setOnClickListener{addb3()}

        val sub1: Button = findViewById(R.id.basketball_team_a_minus_1)
        sub1.setOnClickListener{sub1()}

        val sub2: Button = findViewById(R.id.basketball_team_b_minus_1)
        sub2.setOnClickListener{sub2()}

        val end:Button = findViewById(R.id.basketball_end)
        end.setOnClickListener{endgame()}
        name1 = findViewById<TextView>(R.id.basketball_team_a)
        name2 = findViewById<TextView>(R.id.basketball_team_b)
        name1.text = intent.getStringExtra(TEAMA)
        name2.text = intent.getStringExtra(TEAMB)
    }
    var score1 : Int = 0
    var score2 : Int = 0

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

    private fun adda1()
    {
        val score: TextView = findViewById(R.id.basketball_score_team_a)
        score1+=1
        score.text = score1.toString()
    }

    private fun adda2()
    {
        val score: TextView = findViewById(R.id.basketball_score_team_a)
        score1+=2
        score.text = score1.toString()
    }

    private fun adda3()
    {
        val score: TextView = findViewById(R.id.basketball_score_team_a)
        score1+=3
        score.text = score1.toString()
    }

    private fun addb1()
    {
        val score: TextView = findViewById(R.id.basketball_score_team_b)
        score2+=1
        score.text = score2.toString()
    }

    private fun addb2()
    {
        val score: TextView = findViewById(R.id.basketball_score_team_b)
        score2+=2
        score.text = score2.toString()
    }

    private fun addb3()
    {
        val score: TextView = findViewById(R.id.basketball_score_team_b)
        score2+=3
        score.text = score2.toString()
    }

    private fun sub1()
    {
        val score: TextView = findViewById(R.id.basketball_score_team_a)
        score1-=1
        if (score1<0)
            score1 = 0
        score.text = score1.toString()
    }

    private fun sub2()
    {
        val score: TextView = findViewById(R.id.basketball_score_team_b)
        score2-=1
        if(score2<0)
            score2 = 0
        score.text = score2.toString()
    }

    private fun endgame()
    {
        if(score1 == score2)
        {
            val builder1 = Builder(this)
            builder1.setTitle("Match Ended")
            builder1.setMessage("The Match has been tied...")
            builder1.setPositiveButton("ok") { dialog: DialogInterface?, which: Int ->
                intent = Intent(this, MainMenu::class.java)
                startActivity(intent)
            }
            val alertDialog: AlertDialog = builder1.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
        else {
            val intent = Intent(this, BasketballWinner::class.java)

            if (score1 > score2) {
                intent.putExtra(BasketballWinner.WON, name1.text.toString())
            } else {
                intent.putExtra(BasketballWinner.WON, name2.text.toString())
            }
            startActivity(intent)
        }
    }
}
