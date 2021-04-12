package com.example.allsportsscorer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        val btn1 = findViewById<Button>(R.id.btn_cricket)
        val btn2 = findViewById<Button>(R.id.btn_football)
        val btn3 = findViewById<Button>(R.id.btn_basketball)
        val btn4 = findViewById<Button>(R.id.btn_badminton)

        // cricket
        btn1.setOnClickListener{
            val cintent = Intent(this,CricketMain::class.java)
            startActivity(cintent)

        }
        //Football
        btn2.setOnClickListener{
            val fintent = Intent(this,FootballMain::class.java)
            startActivity(fintent)
        }
        //basketball
        btn3.setOnClickListener{
            val bintent = Intent(this,BasketballMain::class.java)
            startActivity(bintent)

        }
        //badminton
        btn4.setOnClickListener{
            val badintent = Intent(this,badMain::class.java)
            startActivity(badintent)

        }

    }


    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }
}