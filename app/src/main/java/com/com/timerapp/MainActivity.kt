package com.com.timerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.com.timerapp.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnStart.setOnClickListener {
                val totalSeconds = etSeconds.text.toString()
                if (totalSeconds.isNotEmpty()){

                    val timer = object: CountDownTimer(TimeUnit.SECONDS.toMillis(totalSeconds.toLong()), 1000) {
                        override fun onTick(millisUntilFinished: Long) {

                            val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % TimeUnit.MINUTES.toSeconds(1)
                            val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % TimeUnit.HOURS.toMinutes(1)
                            val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % TimeUnit.DAYS.toHours(1)
                            val days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
                            tvTimer.text = "$days:$hours:$minutes:$seconds"
                        }

                        override fun onFinish() {
                            btnStart.isEnabled = true
                        }
                    }
                    timer.start()
                    btnStart.isEnabled = false
                }
                else{
                    Toast.makeText(this@MainActivity, "please enter time", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}