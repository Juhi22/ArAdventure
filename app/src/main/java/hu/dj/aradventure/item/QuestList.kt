package hu.dj.aradventure.item

import hu.dj.aradventure.armodel.Ent
import hu.dj.aradventure.armodel.HellMinion

object QuestList {

    val list = hashMapOf(
        1 to Quest(
            "Méltó vagy e?",
            "Győzz le 5 pokolfajzatot, hogy bebizonyítsd méltó vagy Viharszárny szolgálatára",
            QuestType.KILLING, HellMinion, 5, Sword, 0.1
        ),
        2 to Quest(
            "Hol a baba? Itt a baba!",
            "Találd meg és vidd vissza az elkószált sárkány bébit",
            QuestType.COLLECTING,
            DragonBaby,
            1,
            null,
            null
        ),
        3 to Quest(
            "Utánpótlás hiányában",
            "Az erdő szellemei testet öltöttek és akadályozzák az utánpótlás ellátást. Győzz le belőlük 3-at",
            QuestType.KILLING, Ent, 3, null, 0.2
        ),
    )

}