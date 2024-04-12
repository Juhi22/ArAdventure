package hu.dj.aradventure.controller

import android.content.res.AssetManager
import android.media.MediaPlayer

class SoundController(private val assetManager: AssetManager) {

    var mediaPlayer: MediaPlayer? = null

    fun start(soundPath: String) {
        val assetFileDescriptor = assetManager.openFd(soundPath)
        mediaPlayer = MediaPlayer().apply {
            setDataSource(
                assetFileDescriptor.fileDescriptor,
                assetFileDescriptor.startOffset,
                assetFileDescriptor.length
            )
            prepare()
            start()
        }
    }

    fun shutdown() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}