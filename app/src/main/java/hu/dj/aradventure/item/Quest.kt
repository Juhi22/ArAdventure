package hu.dj.aradventure.item

import hu.dj.aradventure.R

class Quest(override var name: String, override var description: String,
            val questType: QuestType, val questItem: Any, val goal: Int) : Item() {
    override var imageId = R.drawable.quest_log
    override var type = ItemType.QUEST
    var isFinished = false
    var progress = 0
}