package com.equalizer

import android.media.audiofx.Equalizer
import android.media.audiofx.LoudnessEnhancer

class VolumeBoosterManager {

    private var equalizer: Equalizer = Equalizer(0, 0)
    private var loudnessEnhancer: LoudnessEnhancer = LoudnessEnhancer(0)

    fun applyBoost(boostLevel: Int) {
        val maxGain = equalizer.bandLevelRange[1]
        val boostRatio = boostLevel.toFloat() / 100
        val boostGain = (maxGain * boostRatio).toInt()

        val numBands = equalizer.numberOfBands
        for (i in 0 until numBands) {
            equalizer.setBandLevel(i.toShort(), boostGain.toShort())
        }
        equalizer.enabled = boostLevel > 0

        val loudnessGain = boostLevel * 50
        loudnessEnhancer.setTargetGain(loudnessGain)
        loudnessEnhancer.enabled = boostLevel > 0
    }

    fun release() {
        equalizer.release()
        loudnessEnhancer.release()
    }
}

