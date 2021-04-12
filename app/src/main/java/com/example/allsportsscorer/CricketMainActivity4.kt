package com.example.allsportsscorer

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.properties.Delegates

class CricketMainActivity4 : AppCompatActivity() {
    lateinit var radioButton: RadioButton
    var lastCheck = true
    var innings = true
    var teamScore = "0"
    var first = true
    var ballsRemaining by Delegates.notNull<Int>()
    lateinit var cricket_txtOverComplete:TextView
    lateinit var cricket_txtTa: TextView
    lateinit var cricket_txtScore: TextView
    lateinit var cricket_rgScore: RadioGroup
    lateinit var cricket_txtWicket: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cricket_main4)
        cricket_txtOverComplete = findViewById(R.id.cricket_txtOverComplete)
        cricket_txtTa = findViewById(R.id.cricket_txtTa)
        cricket_txtScore = findViewById(R.id.cricket_txtScore)
        cricket_rgScore = findViewById(R.id.cricket_rgScore)
        cricket_txtWicket = findViewById(R.id.cricket_txtWicket)
        cricket_txtOverComplete.text = "0.0"
        val str = intent.getStringExtra("str")
        cricket_txtTa.text = str
    }
    override fun onBackPressed() {
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setMessage("Do you want to Exit?")
        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ -> //if user pressed "yes", then he is allowed to exit from application
            val a: Intent = Intent(this,MainMenu::class.java)
            startActivity(a)
        })
        builder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which -> //if user select "No", just cancel this dialog and continue with app
            dialog.cancel()
        })
        val alert: android.app.AlertDialog = builder.create()
        alert.show()
    }
    fun onScore(view: View) {
        var input = cricket_txtScore.text.toString()
        var selected = cricket_rgScore.checkedRadioButtonId
        radioButton = findViewById(selected)
        var newInput = radioButton.text.toString()
        cricket_txtScore.text = (input.toInt() + newInput.toInt()).toString()
        addBall()
        if (!innings && (cricket_txtScore.text as String).toInt() > teamScore.toInt()) {
            var inpt = intent.getStringExtra("opposite")
            val str = "$inpt won the game"
            val builder1 = AlertDialog.Builder(this)
            builder1.setTitle("game cricket_over")
            builder1.setMessage(str)
            builder1.setPositiveButton("ok") { dialog: DialogInterface?, which: Int ->
                intent = Intent(this, MainMenu::class.java)
                startActivity(intent)
            }
            val alertDialog: AlertDialog = builder1.create()
            alertDialog.setCancelable(false)
            alertDialog.show()

        } else if (!innings) {
            var inpt = intent.getStringExtra("opposite")
            val str = getString(R.string.cricket_welcome, inpt, teamScore, ballsRemaining)
            val builder = AlertDialog.Builder(this)
            builder.setTitle("after first innings")
            builder.setMessage(str)
            builder.setPositiveButton("ok") { dialog: DialogInterface?, which: Int ->
                Toast.makeText(this, "second innings begins", Toast.LENGTH_SHORT).show()
            }
            if (first) {
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
                first = false
            }
        }
        lastCheck = true
        cricket_rgScore.clearCheck()
    }


    fun wide(view: View) {
        var input = cricket_txtScore.text.toString()
        cricket_txtScore.text = (input.toInt() + 1).toString()
        lastCheck = false
    }

    fun noBall(view: View) {
        var input = cricket_txtScore.text.toString()
        cricket_txtScore.text = (input.toInt() + 1).toString()
        lastCheck = false
    }

    fun out(view: View) {
        var input = cricket_txtWicket.text.toString()
        cricket_txtWicket.text = (input.toInt() + 1).toString()
        addBall()
        if (innings && (cricket_txtWicket.text as String).toInt() == 10) {
            var inp = intent.getStringExtra("str")
            teamScore = cricket_txtScore.text.toString()
            val str2 = intent.getStringExtra("opposite")
            cricket_txtScore.text = "0"
            cricket_txtWicket.text = "0"
            cricket_txtTa.text = str2
            cricket_txtOverComplete.text = "0.0"
            val str = getString(R.string.cricket_welcome, str2, teamScore, ballsRemaining)
            val builder = AlertDialog.Builder(this)
            builder.setTitle("after first innings")
            builder.setMessage(str)
            builder.setPositiveButton("ok") { _: DialogInterface?, _: Int ->
                Toast.makeText(this, "second innings begins", Toast.LENGTH_SHORT).show()
            }
            if (first) {
                val alertDialog: AlertDialog = builder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
                first = false
            }
            innings = false
        } else if (!innings && (cricket_txtWicket.text as String).toInt() == 10) {
            var inp = intent.getStringExtra("str")
            val str = "$inp won the game"
            val builder1 = AlertDialog.Builder(this)
            builder1.setTitle("game cricket_over")
            builder1.setMessage(str)
            builder1.setPositiveButton("ok") { dialog: DialogInterface?, which: Int ->
                intent = Intent(this, MainMenu::class.java)
                startActivity(intent)
            }
            val alertDialog: AlertDialog = builder1.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

    private fun addBall() {
        if (lastCheck) {
            var input1 = cricket_txtOverComplete.text.toString()
            val str1 = intent.getStringExtra("overs")
            val splitValue = input1.split(".")
            var one = splitValue[0].toInt()
            var cricket_two = splitValue[1].toInt()
            if (cricket_two == 5) {
                one++
                cricket_two = 0
            } else {
                cricket_two++
            }
            input1 = "$one.$cricket_two"
            cricket_txtOverComplete.text = input1
            if (str1 != null) {
                ballsRemaining = str1.toInt() * 6
            }
            if (str1 == one.toString() && innings) {
                teamScore = cricket_txtScore.text.toString()
                val str2 = intent.getStringExtra("opposite")
                cricket_txtScore.text = "0"
                cricket_txtWicket.text = "0"
                cricket_txtTa.text = str2
                cricket_txtOverComplete.text = "0.0"
                innings = false
                val str = getString(R.string.cricket_welcome, str2, teamScore, ballsRemaining)
                val builder = AlertDialog.Builder(this)
                builder.setTitle("after first innings")
                builder.setMessage(str)
                builder.setPositiveButton("ok") { dialog: DialogInterface?, which: Int ->
                    Toast.makeText(this, "second innings begins", Toast.LENGTH_SHORT).show()
                }
                if (first) {
                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                    first = false
                }
            } else if (str1 == one.toString() && !innings) {
                val str2 = intent.getStringExtra("str")
                val str = "$str2 won the game"
                val builder1 = AlertDialog.Builder(this)
                builder1.setTitle("game cricket_over")
                builder1.setMessage(str)
                builder1.setPositiveButton("ok") { dialog: DialogInterface?, which: Int ->
                    intent = Intent(this, MainMenu::class.java)
                    startActivity(intent)
                }
                val alertDialog: AlertDialog = builder1.create()
                alertDialog.setCancelable(false)
                alertDialog.show()

            } else if (!innings) {
                val str2 = intent.getStringExtra("opposite")
                val str = getString(R.string.cricket_welcome, str2, teamScore, ballsRemaining)
                val builder = AlertDialog.Builder(this)
                builder.setTitle("after first innings")
                builder.setMessage(str)
                builder.setPositiveButton("ok") { dialog: DialogInterface?, which: Int ->
                    Toast.makeText(this, "they clicked okay", Toast.LENGTH_SHORT).show()
                }
                if (first) {
                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()
                    first = false
                }
            }
        }
    }
}