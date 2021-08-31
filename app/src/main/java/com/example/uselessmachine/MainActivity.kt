package com.example.uselessmachine

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import java.util.concurrent.CountDownLatch
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    lateinit var uselessSwitch: Switch
    lateinit var lookBusyButton: Button
    lateinit var selfDestructButton: Button
    lateinit var lightImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        uselessSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            //toast for when checked
            if (isChecked) {
                Toast.makeText(this@MainActivity, "Switch is checked", Toast.LENGTH_SHORT).show()
                startSwitchTimer()
            }
            //toast for when unchecked
            if (!isChecked) {
                Toast.makeText(this@MainActivity, "Switch is not checked", Toast.LENGTH_SHORT).show()
            }
        }

        selfDestructButton.setOnClickListener {
            var swap = 100
            val destructTimer = object : CountDownTimer(10000, 500) {
                override fun onTick(p0: Long) {
                    swap--
                    selfDestructButton.text = "" + p0/1000
                    if(swap%2==0) {
                        lightImageView.setColorFilter(Color.RED, PorterDuff.Mode.ADD)
                    }
                    else{
                        lightImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.ADD)
                    }
                }

                override fun onFinish() {
                    finish()
                }
            }
            destructTimer.start()
        }
    }

    private fun startSwitchTimer() {
        //make an anonymous inner class to create a CountDownTimer object
        val uselessTimer = object : CountDownTimer(3000, 500) {
            //callback functions will be called later, sometime in the future
            override fun onTick(p0: Long) {
                if (!uselessSwitch.isChecked) {
                    cancel()
                }
            }

            override fun onFinish() {
                //turn switch off
                uselessSwitch.isChecked = false
            }
        }
        uselessTimer.start()
    }

    private fun wireWidgets() {
        uselessSwitch = findViewById(R.id.switch_main_useless)
        lookBusyButton = findViewById(R.id.button_main_lookBusy)
        selfDestructButton = findViewById(R.id.button_main_selfDestruct)
        lightImageView = findViewById(R.id.imageView_main_light)
    }
}

