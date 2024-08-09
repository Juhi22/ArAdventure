package hu.dj.aradventure

import GameDataManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var gameDataManager: GameDataManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        gameDataManager = GameDataManager(this)

        val videoView: VideoView = findViewById(R.id.backgroundVideoView)
        val videoPath = "android.resource://${packageName}/${R.raw.menu_background}"
        val uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)

        videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
            mediaPlayer.start()
        }

        val startButton: Button = findViewById(R.id.startButton)
        val tutorialButton: Button = findViewById(R.id.tutorialButton)
        val resetButton: Button = findViewById(R.id.resetButton)

        startButton.setOnClickListener {
            if (!gameDataManager.getBooleanValue(GameDataManager.Key.INTRO_WATCHED.name)) {
                gameDataManager.saveBooleanValue(GameDataManager.Key.INTRO_WATCHED.name, true)
                val intent = Intent(this, IntroActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        tutorialButton.setOnClickListener {
            val intent = Intent(this, TutorialActivity::class.java)
            startActivity(intent)
        }

        tutorialButton.setOnLongClickListener {
            resetButton.isEnabled = true
            resetButton.visibility = View.VISIBLE
            true
        }

        resetButton.setOnClickListener {
            val gameDataManager = GameDataManager(this)
            gameDataManager.resetGameState()
        }
    }
}