package com.aditya.dictionaryapp.core.util

import android.media.MediaPlayer
import android.os.AsyncTask
import java.io.IOException

class AudioPlayer(private val audioUrl: String) {

    private var mediaPlayer: MediaPlayer? = null

    fun start() {
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setOnPreparedListener {
            mediaPlayer?.start()
        }

        mediaPlayer?.setOnCompletionListener {
            mediaPlayer?.release()
        }

        // Use AsyncTask to avoid NetworkOnMainThreadException
        DownloadAudioTask().execute(audioUrl)
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private inner class DownloadAudioTask : AsyncTask<String, Void, Boolean>() {
        override fun doInBackground(vararg params: String): Boolean {
            val url = params[0]
            try {
                mediaPlayer?.setDataSource(url)
                mediaPlayer?.prepare()
            } catch (e: IOException) {
                e.printStackTrace()
                return false
            }
            return true
        }
    }
}

