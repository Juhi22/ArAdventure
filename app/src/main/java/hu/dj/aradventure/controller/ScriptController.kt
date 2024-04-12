package hu.dj.aradventure.controller

import android.content.res.AssetManager
import android.view.View
import android.widget.TextView
import hu.dj.aradventure.GameState
import hu.dj.aradventure.armodel.ArModel

class ScriptController(
    private val mobText: TextView,
    private val playerTextA: TextView,
    private val playerTextB: TextView,
    private val soundController: SoundController
) {

    private lateinit var dialogs: HashMap<String, List<Any>>
    private lateinit var arModel: ArModel
    private lateinit var chapter: String
    private lateinit var answerA: List<String>
    private lateinit var answerB: List<String>
    private var npcSentenceKey: String = "1"

    fun init() {
        mobText.visibility = View.INVISIBLE
        playerTextA.visibility = View.INVISIBLE
        playerTextB.visibility = View.INVISIBLE

        playerTextA.setOnClickListener {
            if (answerA.isNotEmpty()) {
                npcSentenceKey = answerA[1]
                displayDialogsAndPlaySound()
            } else {
                removeDialogs()
            }
        }

        playerTextB.setOnClickListener {
            if (answerB.isNotEmpty()) {
                npcSentenceKey = answerB[1]
                displayDialogsAndPlaySound()
            } else {
                removeDialogs()
            }
        }
    }

    fun play(gameState: GameState, givenArModel: ArModel) {
        arModel = givenArModel
        chapter = gameState.chapter.toString()
        val script: Map<String, Any> = arModel.script
        dialogs = if(script.isEmpty()) {
            HashMap()
        } else {
            script[chapter] as HashMap<String, List<Any>>
        }
        displayDialogsAndPlaySound()

    }

    private fun displayDialogsAndPlaySound() {
        if (dialogs.isNotEmpty()) {
            val section = dialogs[npcSentenceKey]
            if (section != null) {
                setAnswers(section)
                setTexts(section)

                val soundKey = "$chapter/$npcSentenceKey"
                var soundPath: String? = arModel.sounds[soundKey]
                if (soundPath == null) {
                    soundPath = arModel.sounds["default"]
                }
                if (soundPath != null) {
                    soundController.start(soundPath)
                }
            }
        } else {
            arModel.sounds["default"]?.let { soundController.start(it) }
        }
    }

    private fun setTexts(section: List<Any>) {
        mobText.text = section[0].toString()
        mobText.visibility = View.VISIBLE
        playerTextA.text = if (answerA.isNotEmpty()) answerA[0] else "-"
        playerTextA.visibility = View.VISIBLE
        playerTextB.text = if (answerB.isNotEmpty()) answerB[0] else "-"
        playerTextB.visibility = View.VISIBLE
    }

    private fun setAnswers(section: List<Any>) {
        if (section[1] != null) {
            val answers: List<String> = section[1] as List<String>
            answerA = dialogs[answers[0]] as List<String>
            answerB = dialogs[answers[1]] as List<String>
        } else {
            answerA = emptyList()
            answerB = emptyList()
        }
    }

    private fun removeDialogs() {
        mobText.visibility = View.INVISIBLE
        playerTextA.visibility = View.INVISIBLE
        playerTextB.visibility = View.INVISIBLE
    }
}