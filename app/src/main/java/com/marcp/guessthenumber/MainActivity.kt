package com.marcp.guessthenumber

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val randnum = (0..100).random()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val insertNumber= findViewById<EditText>(R.id.editTextNumber)
        val btn =findViewById<Button>(R.id.button)
        var historyNumber= findViewById<TextView>(R.id.textView)
        var trynum = 0
        var text= ""

        //Ahora hacer el on click listener
        btn.setOnClickListener {
            trynum++
            if (insertNumber.text.toString().toInt()<randnum) {
                 text= "El teu numero es més petit!"
                historyNumber.text = "aa"
            }else if (insertNumber.text.toString().toInt()>randnum) {
                 text="El teu numero es més alt!"
            } else {
                 text= "Has guanyat!"
            }
            val duration= Toast.LENGTH_SHORT
            val toast = Toast.makeText(this, text, duration)
            toast.show()
        }
    }
}