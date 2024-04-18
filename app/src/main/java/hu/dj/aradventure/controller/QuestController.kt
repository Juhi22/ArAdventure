package hu.dj.aradventure.controller

import hu.dj.aradventure.armodel.Enemy
import hu.dj.aradventure.item.Quest
import hu.dj.aradventure.item.QuestType

object QuestController {

    interface QuestListener {
        fun onQuestStarts(quest: Quest)
        fun onQuestEnds(quest: Quest)
        fun onQuestsUpdated(quests: List<Quest>)
    }

    private var listener: QuestListener? = null

    fun onQuestStartListener(listener: QuestListener) {
        this.listener = listener
    }

    fun startQuest(quest: Quest) {
        listener?.onQuestStarts(quest)
    }

    fun finishQuest(quest: Quest) {
        listener?.onQuestEnds(quest)
    }

    fun update(quests: List<Quest>, type: QuestType, questItem: Any) {
        quests.forEach{
            if (it.questType == type) {
                if (type == QuestType.KILLING && it.questItem == questItem && it.progress < it.goal) {
                    it.progress++
                    if (it.progress >= it.goal) {
                        it.isFinished = true
                    }
                } else if (type == QuestType.COLLECTING) {

                }
            }
        }
        listener?.onQuestsUpdated(quests)
    }

}