package com.example.uselessmachine

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import org.w3c.dom.Text
import java.security.acl.Group
import java.util.concurrent.CountDownLatch
import kotlin.system.exitProcess

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
            var swap = 0
            val destructTimer = object : CountDownTimer(10000, 500) {
                override fun onTick(p0: Long) {
                    swap++
                    selfDestructButton.text = "" + p0/1000
                    if(swap%2==0) {
                        lightImageView.setColorFilter(Color.RED, PorterDuff.Mode.ADD)
                        background.setBackgroundColor(Color.WHITE)
                    }
                    else{
                        lightImageView.setColorFilter(Color.WHITE, PorterDuff.Mode.ADD)
                        background.setBackgroundColor(Color.RED)
                    }
                }

                override fun onFinish() {
                    finish()
                }
            }
            destructTimer.start()
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
        background = findViewById(R.id.frameLayout)
        groupMainUi.visibility = View.VISIBLE
        groupBusyUi.visibility = View.INVISIBLE
        fileLoaderProgressBar = findViewById(R.id.progressBar_main_files)
    }
}

