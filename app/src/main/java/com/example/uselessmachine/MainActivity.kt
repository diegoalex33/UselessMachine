package com.example.uselessmachine

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    lateinit var uselessSwitch: Switch
    lateinit var lookBusyButton: Button
    lateinit var selfDestructButton: Button
    lateinit var lightImageView: ImageView
    lateinit var groupMainUi : androidx.constraintlayout.widget.Group
    lateinit var fileLoaderProgressBar : ProgressBar
    lateinit var groupBusyUi : androidx.constraintlayout.widget.Group
    lateinit var progressText : TextView
    lateinit var background : FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        //groupMainUi.visibility = View.INVISIBLE

        //3 types of visibility:
        //View.VISIBLE
        //View.INVISIBLE
        //View.GONE
        //hide the lookbusy group (mainUI group is visible by default)
        //progressBar (0% to 100%) and TextView should be changed on tick
        //mainUI restored onFinish




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
            var countDownInterval = 1000
            var time = 10000
            var swap = 0
            var frequency = 4
            val destructTimer = object : CountDownTimer(time.toLong(), countDownInterval.toLong()) {
                override fun onTick(p0: Long) {
                    swap++
                    if(p0<=5000){
                        frequency = 2
                    }
                    selfDestructButton.text = "Count down to extinction: " + (p0/1000 + 1)
                    if(swap%frequency==0) {
                        background.visibility = View.VISIBLE
                        background.setBackgroundColor(Color.RED)
                    }
                    else{
                        background.visibility = View.INVISIBLE
                        lightImageView.setColorFilter(Color.RED, PorterDuff.Mode.ADD)

                    }
                }

                override fun onFinish() {
                    finish()
                    lookBusyButton.visibility = View.VISIBLE
                    uselessSwitch.visibility = View.VISIBLE
                }
            }
            lookBusyButton.visibility = View.INVISIBLE
            uselessSwitch.visibility = View.INVISIBLE
            destructTimer.start()
            selfDestructButton.isEnabled = false
        }


        lookBusyButton.setOnClickListener {
            groupMainUi.visibility = View.INVISIBLE
            groupBusyUi.visibility = View.VISIBLE
            startBusyTimer()
        }
    }

    private fun startBusyTimer(){
        var progress = 0
        val busyTimer = object : CountDownTimer(10000,100){
            override fun onTick(p0: Long) {
                progress++
                fileLoaderProgressBar.max = 100
                fileLoaderProgressBar.setProgress(progress)
                progressText.text = "Loading Files: " + progress + "%"
            }

            override fun onFinish() {
                groupMainUi.visibility = View.VISIBLE
                groupBusyUi.visibility = View.INVISIBLE
            }
        }
        busyTimer.start()
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
        progressText = findViewById(R.id.textView_main_progress)
        groupMainUi = findViewById(R.id.group_main_ui)
        groupBusyUi = findViewById(R.id.group_main_lookBusyUi)
        background = findViewById(R.id.layout_main_flash)
        groupMainUi.visibility = View.VISIBLE
        groupBusyUi.visibility = View.INVISIBLE
        fileLoaderProgressBar = findViewById(R.id.progressBar_main_files)
    }
}

