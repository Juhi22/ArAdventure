package hu.dj.aradventure.controller

import android.view.View
import android.widget.TextView
import hu.dj.aradventure.GameState
import hu.dj.aradventure.armodel.ArModel
import hu.dj.aradventure.item.Quest
import hu.dj.aradventure.item.QuestList
import java.util.stream.Collectors

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
    private var finishedQuest: Quest? = null
    var isScriptOngoing = false
    private val constantDialogs = listOf("default", "attack", "dead")

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
        removeDoneDialog()
        isScriptOngoing = false
        if (newQuest != null) {
            QuestController.startQuest(newQuest!!)
            newQuest = null
        }
        if (finishedQuest != null) {
            QuestController.finishQuest(finishedQuest!!)
            finishedQuest = null
        }
        onCompletionListener?.invoke()
    }

    private fun removeDoneDialog() {
        if (!constantDialogs.contains(chapter) && arModel.script.keys.contains(chapter)) {
            arModel.script.remove(chapter)
        }
    }

    fun play(gameState: GameState?, givenArModel: ArModel, quests: List<Quest>, specialScriptKey: String = "default") {
        npcSentenceKey = "1"
        arModel = givenArModel
        val script: Map<String, Any> = arModel.script
        // clear listener
        onCompletionListener = null

        val compeletedQuests = collectCompletedQuestsOfArModel(quests)
        if (compeletedQuests.isNotEmpty()) {
            chapter = compeletedQuests[0].name
            finishedQuest = compeletedQuests[0]
        } else {
            if (gameState != null) {
                chapter = gameState.chapter.toString()
            } else {
                chapter = specialScriptKey
            }
        }
        dialogs = if (script.isEmpty()) {
            HashMap()
        } else {
            if (script[chapter] != null) {
                script[chapter] as HashMap<String, List<Any>>
            } else {
                var foundQuestScript: HashMap<String, List<Any>>? = null
                for (i in 1..50) {
                    val questScript = script["quest$i"]
                    if (questScript != null && gameState != null && !gameState.completedQuestIndexes.contains(i)
                    ) {
                        if (checkIfQuestIsInProgressByIndex(i, quests)) {
                            break
                        }
                        foundQuestScript = questScript as HashMap<String, List<Any>>
                        break
                    }
                }
                foundQuestScript ?: script["default"] as HashMap<String, List<Any>>
            }
        }
        displayDialogsAndPlaySound()
    }

    private fun checkIfQuestIsInProgressByIndex(index: Int, quests: List<Quest>): Boolean {
        val questFromTheList = QuestList.list[index]
        if (questFromTheList != null) {
            return quests
                .stream()
                .filter { it.name == questFromTheList.name }
                .collect(Collectors.toList()).isNotEmpty()
        }
        return false
    }

    private fun collectCompletedQuestsOfArModel(quests: List<Quest>): List<Quest> {
        return quests
            .stream()
            .filter { it.isFinished && arModel.quests.contains(it) }
            .collect(Collectors.toList())
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
            isScriptOngoing = true
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