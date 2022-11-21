package com.example.servicetest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.servicetest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.simpleService.setOnClickListener {
            startService(MyService.newIntent(this))
        }
        binding.foregroundService.setOnClickListener {
            ContextCompat.startForegroundService(
                this,
                MyForegroundService.newIntent(this))
        }
        binding.intentService.setOnClickListener {
            startService(MyIntentService.newIntent(this))
        }
    }

}