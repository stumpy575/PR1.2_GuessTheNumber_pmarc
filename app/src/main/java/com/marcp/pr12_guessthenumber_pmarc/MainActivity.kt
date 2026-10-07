package com.marcp.pr12_guessthenumber_pmarc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var randnum = 50
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
        var historyNumber= findViewById<TextView>(R.id.historyNumber)
        var trynum = 0
        var text= ""

        //Ahora hacer el on click listener
        btn.setOnClickListener {
            val numberguess = insertNumber.text.toString().toIntOrNull()
            insertNumber.setText("")

            if (numberguess == null || numberguess !in 0..100) {
                Toast.makeText(this, "Afegeix un numero vàlid!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            trynum++

            if (numberguess<randnum) {
                text= "El teu numero es més petit!"
                historyNumber.append("$trynum: $numberguess < ? \n")
            }else if (numberguess>randnum) {
                text="El teu numero es més alt!"
                historyNumber.append("$trynum: $numberguess > ? \n")
            } else {
                text= "Has guanyat!"
                historyNumber.append("Numero correcte: $randnum \n")
                AlertDialog.Builder(this)
                    .setTitle("Has guanyat!")
                    .setMessage("Has encertat el número en $trynum intents. Vols veure el rànquing o intentar-ho un altre vegada")
                    .setCancelable(false)
                    .setPositiveButton("Ranking") { dialog, _ ->
                        val intent = Intent(this, ActivityB::class.java)
                        intent.putExtra("INTENTS", trynum)
                        startActivity(intent)
                        dialog.dismiss()
                    }
                    .setNegativeButton("Try again") { dialog, _ ->
                        dialog.dismiss()
                        trynum=0
                        randnum = (0..100).random()
                        historyNumber.setText("")
                    }
                    .show()
            }
            val duration= Toast.LENGTH_SHORT
            val toast = Toast.makeText(this, text, duration)
            toast.show()
        }
    }
}

