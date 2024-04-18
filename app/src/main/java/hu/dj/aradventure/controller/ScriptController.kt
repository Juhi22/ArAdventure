package hu.dj.aradventure.controller

import android.view.View
import android.widget.TextView
import hu.dj.aradventure.GameState
import hu.dj.aradventure.armodel.ArModel
import hu.dj.aradventure.item.Quest

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
    private var newQuest: Quest? = null

    private var onCompletionListener: (() -> Unit)? = null

    fun setOnCompletionListener(onCompletionListener: () -> Unit) {
        this.onCompletionListener = onCompletionListener
    }

    fun init() {
        mobText.visibility = View.INVISIBLE
        playerTextA.visibility = View.INVISIBLE
        playerTextB.visibility = View.INVISIBLE

        playerTextA.setOnClickListener {
            handleAnswer(answerA)
        }

        playerTextB.setOnClickListener {
            handleAnswer(answerB)
        }
    }

    private fun handleAnswer(answers: List<String>) {
        if (answers.isNotEmpty()) {
            npcSentenceKey = answers[1]
            displayDialogsAndPlaySound()
        } else {
            finishScript()
        }
    }

    private fun finishScript() {
        removeDialogs()
        if (newQuest != null) {
            QuestController.startQuest(newQuest!!)
            newQuest = null
        }
        onCompletionListener?.invoke()
    }

    fun play(gameState: GameState?, givenArModel: ArModel, specialScriptKey: String = "default") {
        npcSentenceKey = "1"
        arModel = givenArModel
        val script: Map<String, Any> = arModel.script
        if (gameState != null) {
            chapter = gameState.chapter.toString()
        } else {
            chapter = specialScriptKey
        }
        dialogs = if (script.isEmpty()) {
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
            finishScript()
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
        if (section[1] is Quest) {
            newQuest = section[1] as Quest
            answerA = emptyList()
            answerB = emptyList()
        } else {
            if (section[1] != null) {
                val answers: List<String> = section[1] as List<String>
                answerA = dialogs[answers[0]] as List<String>
                answerB = dialogs[answers[1]] as List<String>
            } else {
                answerA = emptyList()
                answerB = emptyList()
            }
        }
    }

    private fun removeDialogs() {
        mobText.visibility = View.INVISIBLE
        playerTextA.visibility = View.INVISIBLE
        playerTextB.visibility = View.INVISIBLE
    }
}