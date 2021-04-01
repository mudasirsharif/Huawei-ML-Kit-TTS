package com.dtse.ml_text_to_speech_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var onCloudTTSManager: OnCloudTTSManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onCloudTTSManager = OnCloudTTSManager()

        disableSpeakerButton()

        speaker_button.setOnClickListener {

            if (!onCloudTTSManager!!.isPlaying) {

                enableSpeakerButton()
                val news = resources.getStringArray(R.array.News)
                for (paragraph in news)
                    onCloudTTSManager?.playTTSOnDevice(paragraph)
            } else {

                disableSpeakerButton()
                onCloudTTSManager?.controlTTSEngine("stop")
            }
        }
    }

    override fun onPause() {
        super.onPause()
        onCloudTTSManager?.controlTTSEngine("stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        onCloudTTSManager?.controlTTSEngine("shutdown")
    }

    private fun disableSpeakerButton() {
        speaker_button.setColorFilter(
            ContextCompat.getColor(this, R.color.grey),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }

    private fun enableSpeakerButton() {
        speaker_button.clearColorFilter()
    }
}
