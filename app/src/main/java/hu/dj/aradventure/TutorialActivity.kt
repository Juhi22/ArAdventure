package hu.dj.aradventure

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class TutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        /*val videoView: VideoView = findViewById(R.id.tutorialVideoView)
        val videoPath = "android.resource://" + packageName + "/" + R.raw.tutorial_video

        val uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)

        val mediaController = MediaController(this)
        videoView.setMediaController(mediaController)
        mediaController.setAnchorView(videoView)

        videoView.setOnCompletionListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }

        videoView.start()*/
    }
}