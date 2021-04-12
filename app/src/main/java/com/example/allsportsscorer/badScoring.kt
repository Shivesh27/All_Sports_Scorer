package com.example.allsportsscorer

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class badScoring : AppCompatActivity() {
    companion object{
        const val TEAMA = "TEAMA"
        const val TEAMB = "TEAMB"
        const val SETS = "SETS"
    }
    lateinit var m_set: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bad_scoring)

        val text1: TextView = findViewById(R.id.bad_team_a)
        val text2: TextView = findViewById(R.id.bad_team_b)
        val name1 = intent.getStringExtra(TEAMA)
        val name2 = intent.getStringExtra(TEAMB)
        m_set = intent.getStringExtra(SETS)
        text1.text=name1
        text2.text=name2
        val adda: Button = findViewById(R.id.bad_add_a)
        val addb: Button = findViewById(R.id.bad_add_b)
        val scorea: TextView = findViewById(R.id.bad_score_a)
        val scoreb: TextView = findViewById(R.id.bad_score_b)
        val seta: TextView = findViewById(R.id.bad_sets_a)
        val setb: TextView = findViewById(R.id.bad_sets_b)
        val end: Button = findViewById(R.id.bad_end)
        end.setOnClickListener{
            if(seta.text.toString() == setb.text.toString())
            {
                if(scorea.text.toString().toInt() == scoreb.text.toString().toInt())
                {
                    val builder1 = AlertDialog.Builder(this)
                    builder1.setTitle("Match Ended")
                    builder1.setMessage("The Match is Ended and the scores are level")
                    builder1.setPositiveButton("ok") { dialog: DialogInterface?, which: Int ->
                        intent = Intent(this, MainMenu::class.java)
                        startActivity(intent)
                    }
                    val alertDialog: AlertDialog = builder1.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                }
                else if(scorea.text.toString().toInt() > scoreb.text.toString().toInt())
                {
                    winner(name1)
                }
                else
                {
                    winner(name2)
                }
            }
            else if(seta.text.toString() > setb.text.toString())
            {
                winner(name1)
            }
            else
            {
                winner(name2)
            }
        }
        adda.setOnClickListener()
        {
            update_score(name1,scorea,seta,scoreb)
        }
        addb.setOnClickListener()
        {
            update_score(name2,scoreb,setb,scorea)
        }
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

    private fun update_score(name:String, scorea: TextView,seta: TextView,scoreb: TextView) {
        val score= scorea.text.toString()
        val set = seta.text.toString()
        val score_opp= scoreb.text.toString()
        if (score.toInt()>=20 && score.toInt()-score_opp.toInt()>=2 || score.toInt()==29)
        {
            if(set.toInt()<m_set.toInt()-1) {
                seta.text = (set.toInt() + 1).toString()
                scoreb.text = (0).toString()
                scorea.text = (0).toString()
            }
            else
            {
                winner(name)
            }
        }
        else
        {
            scorea.text= (score.toInt()+1).toString()
        }
    }

    private fun winner(score:String) {
        val intent = Intent(this,badWinner::class.java)
                intent.putExtra(badWinner.WON,score)
                startActivity(intent)

    }

}