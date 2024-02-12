package com.equalizer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.equalizer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val volumeBoosterManager = VolumeBoosterManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        volumeBoostControl()
    }

    private fun volumeBoostControl() {
        var volumeBoost = 0
        binding.btnIncrease.setOnClickListener {
            volumeBoost += 5
            binding.tvLevel.text = "$volumeBoost"
            volumeBoosterManager.applyBoost(volumeBoost)
        }
        binding.btnDecrease.setOnClickListener {
            volumeBoost -= 5
            binding.tvLevel.text = "$volumeBoost"
            volumeBoosterManager.applyBoost(volumeBoost)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        volumeBoosterManager.release()
    }
}