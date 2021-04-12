package com.example.allsportsscorer

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AlertDialog.*
import java.lang.Boolean

class FootballScoring : AppCompatActivity() {
    companion object {
        const val TEAMA = "TEAMA"
        const val TEAMB = "TEAMB"
        const val TIME = "TIME"
    }
    lateinit var Ctimer: CountDownTimer
    var  Ttime:Long = 5400000
    var running = Boolean.FALSE
    lateinit var start_pause: Button
    lateinit var timer_text: TextView
    lateinit var score1: TextView
    lateinit var score2: TextView
    lateinit var name1: String
    lateinit var name2: String
    var timeLeft:Long = Ttime
    lateinit var btn1:Button
    lateinit var btn2:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_football_scoring)
        start_pause = findViewById<Button>(R.id.football_start_pause)

        timer_text = findViewById<TextView>(R.id.football_timer)
        val text1: TextView = findViewById(R.id.football_team_a_1)
        val text2: TextView = findViewById(R.id.football_team_b_1)

        score1 = findViewById(R.id.football_team_a_score)
        score2= findViewById(R.id.football_team_b_score)

        btn1  = findViewById<Button>(R.id.football_team_a_goal)
        btn2 = findViewById<Button>(R.id.football_team_b_goal)

        val end_btn = findViewById<Button>(R.id.football_end)

        name1 = intent.getStringExtra(TEAMA)
        name2 = intent.getStringExtra(TEAMB)
        Ttime = (intent.getStringExtra(TIME).toInt()*60000).toLong()
        timeLeft = Ttime

        text1.text = name1;
        text2.text = name2;
        btn1.isEnabled=false
        btn2.isEnabled=false

        btn1.setOnClickListener {
            goal_add(score1)
        }

        btn2.setOnClickListener {
            goal_add(score2)
        }

        end_btn.setOnClickListener {
            winner()
        }

        start_pause.setOnClickListener{
            if(running)
            {
                pauseTimer();
            }
            else
            {
                startTimer();
            }
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

    private fun pauseTimer()
    {
        btn1.isEnabled=false
        btn2.isEnabled=false
        running = Boolean.FALSE
        Ctimer.cancel()
        start_pause.text = "Resume"

    }

    private fun startTimer()
    {
        btn1.isEnabled=true
        btn2.isEnabled=true
        Ctimer = object : CountDownTimer(timeLeft,1000)
        {
            override fun onTick(millisUntilFinished: Long)
            {
                timeLeft = millisUntilFinished
                timer_update(timer_text,millisUntilFinished)
            }
            override fun onFinish() {
                winner()
            }
        }.start()
        running = Boolean.TRUE
        start_pause.text = "Pause"

    }

    private fun winner()
    {
        val ascore = score1.text.toString().toInt()
        val bscore = score2.text.toString().toInt()
        if(ascore == bscore)
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
            val intent = Intent(this, FootballWinner::class.java)
            var won = name1
            if (ascore > bscore) {
                won = name1
                Toast.makeText(applicationContext, "$name1 won the match", Toast.LENGTH_LONG).show()
            } else {
                won = name2
            }
            intent.putExtra(FootballWinner.WINNER, won)
            startActivity(intent)
        }
    }

    private fun timer_update(timer_text: TextView, millis :Long)
    {
        val min = (millis/1000)/60
        val sec = (millis/1000)%60
        timer_text.text = "$min:$sec"
    }

    private fun goal_add(score : TextView)
    {
        val score_val = score.text.toString()
        score.text = (score_val.toInt() + 1).toString()
    }
}