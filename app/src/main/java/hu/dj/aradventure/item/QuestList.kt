package hu.dj.aradventure.item

import hu.dj.aradventure.armodel.*

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
            HealthAmulet,
            null
        ),
        3 to Quest(
            "Utánpótlás hiányában",
            "Az erdő szellemei testet öltöttek és akadályozzák az utánpótlás ellátást. Győzz le belőlük 3-at",
            QuestType.KILLING, Ent, 3, null, 1.0
        ),
        4 to Quest(
            "Sárkányok ereje",
            "Gyűjtsd össze a sárkány urak erő gömbjeit",
            QuestType.COLLECTING, DragonPowerOrb, 3, null, 1.1
        ),
        5 to Quest(
            "Tiszta vizet a pohárba",
            "Beszélj Viharszárnnyal, kérdezz rá az Unikornistól hallottakra",
            QuestType.SPEAKING,
            null,
            0,
            null,
            2.0
        ),
        6 to Quest(
            "Rontáslevétel",
            "Szabadítsd fel a mágia alól Viharszárny fogjait",
            QuestType.KILLING,
            DragonSlave,
            5,
            null,
            2.1
        ),
        7 to Quest(
            "Szuper csillag sárkány lovag",
            "Küzdj meg Viharszárny seregével és győzd le a sárkány erő gömbök által felerősített lovagjait",
            QuestType.KILLING,
            DragonKnight,
            5,
            null,
            2.2
        ),
        8 to Quest(
            "A vihar vége?",
            "Eljött az idő, megnyílt az út Viharszárnyhoz. Győzd le és ments meg a völgyet",
            QuestType.KILLING,
            StormWingBoss,
            1,
            null,
            2.3
        ),
    )

}