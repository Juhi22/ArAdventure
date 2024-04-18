package hu.dj.aradventure.item

import hu.dj.aradventure.armodel.HellMinion

object QuestList {

    val list = hashMapOf(
        1 to Quest("Méltó vagy e?",
            "Győzz le 5 pokolfajzatot, hogy bebizonyítsd méltó vagy Viharszárny szolgálatára",
            QuestType.KILLING, HellMinion, 5),
        2 to Quest("Gyűjtögetés", "Gyűjts össze...", QuestType.COLLECTING, "", 0)
    )

}