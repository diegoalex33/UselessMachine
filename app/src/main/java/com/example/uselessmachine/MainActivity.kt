package com.example.uselessmachine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import java.util.concurrent.CountDownLatch

class MainActivity : AppCompatActivity() {

    lateinit var uselessSwitch: Switch
    lateinit var lookBusyButton: Button
    lateinit var selfDestructButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        uselessSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            //toast for when checked
            if (isChecked) {
                Toast.makeText(this@MainActivity, "Switch is checked", Toast.LENGTH_SHORT).show()
            }
            //toast for when unchecked
            if (!isChecked) {
            Toast.makeText(this@MainActivity, "Switch is not checked", Toast.LENGTH_SHORT).show()
            }
            startSwitchTimer()

        }
    }

    private fun startSwitchTimer(){
        object : CountDownTimer(millisInFuture: 3000, countDownInterval: 500){

        }

    }

    private fun wireWidgets(){
        uselessSwitch = findViewById(R.id.switch_main_useless)
        lookBusyButton = findViewById(R.id.button_main_lookBusy)
        selfDestructButton = findViewById(R.id.button_main_selfDestruct)
    }
}