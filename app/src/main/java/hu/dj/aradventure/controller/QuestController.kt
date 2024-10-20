package hu.dj.aradventure.controller

import hu.dj.aradventure.item.Item
import hu.dj.aradventure.item.Quest
import hu.dj.aradventure.item.QuestType

object QuestController {

    interface QuestListener {
        fun onQuestStarts(quest: Quest)
        fun onQuestEnds(quest: Quest)
        fun onQuestsUpdated(quests: List<Quest>)
    }

    private var listener: QuestListener? = null

    fun onEventListener(listener: QuestListener) {
        this.listener = listener
    }

    fun startQuest(quest: Quest) {
        listener?.onQuestStarts(quest)
    }

    fun finishQuest(quest: Quest) {
        listener?.onQuestEnds(quest)
    }

    fun update(quests: List<Quest>, type: QuestType, questItem: Any?, playerInventory: List<Item> = emptyList()) {
        var item = questItem
        if (questItem is Quest) {
            item = questItem.questItem
        }
        quests.forEach {
            if (!it.isFinished && it.questType == type) {
                if (type == QuestType.COLLECTING && playerInventory.isNotEmpty() && it.questItem is Item) {
                    val numberOfQuestItems = playerInventory.count { inventoryItem -> inventoryItem.name == it.questItem.name }
                    it.progress = numberOfQuestItems
                } else if (questItem !is Quest && it.questItem == item && it.progress < it.goal) {
                    it.progress++
                }
                if (it.progress >= it.goal) {
                    it.isFinished = true
                }
            }
        }
        listener?.onQuestsUpdated(quests)
    }

}