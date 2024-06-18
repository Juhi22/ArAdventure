package hu.dj.aradventure.armodel

import hu.dj.aradventure.item.Quest
import hu.dj.aradventure.item.QuestList

object StormWing : ArModel() {
    override val gltfPath: String
        get() = "storm_wing/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "storm_wing/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "Idle"
            )
        }

    override val quests: List<Quest> = listOf(
        QuestList.list[3]!!,
    )

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "storm_wing/sounds/1.mp3",
                "default/1" to "storm_wing/sounds/1.mp3",
                "0.1/1" to "storm_wing/sounds/1.mp3",
                "0.1/2" to "storm_wing/sounds/1.mp3",
                "0.1/3" to "storm_wing/sounds/1.mp3",
                "0.1/4" to "storm_wing/sounds/1.mp3",
                "0.1/5" to "storm_wing/sounds/1.mp3",
                quests[0].name + "/1" to "storm_wing/sounds/1.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
            "default" to hashMapOf(
                "1" to listOf(
                    "Hosszú az út, de végül békét teremtünk a völgyben!",
                    null
                ),
            ),
            quests[0].name to hashMapOf(
                "1" to listOf(
                    "Jó szolgálatot tettél, mostmár a szövetségeseink újra ételhez juthatnak.",
                    null
                )
            ),
            "0.1" to hashMapOf(
                "1" to listOf(
                    "Üdvözöllek, Viharszárny vagyok. Megüzenték, hogy sikeresen helyt álltál.",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "Erős és nagyképű, csak egy újabb átlagos hős lennél? A napokban probléma adódott amit haladéktalanul meg kell oldani.",
                    listOf("player3", "player4")
                ),
                "3" to listOf(
                    "Erős és vakmerő, több ilyen hős kellene a seregembe. A napokban probléma adódott amit haladéktalanul meg kell oldani.",
                    listOf("player3", "player4")
                ),
                "4" to listOf(
                    "Az erdő szellemei testet öltöttek és a gonosz oldalára álltak, nem engedik át az ételt szállító utánpótlást, ezért a nép éhezik. Eltökélt vagy ez tetszik.",
                    quests[0]
                ),
                "5" to listOf(
                    "Semmittevés? Az erdő szellemei testet öltöttek és a gonosz oldalára álltak, nem engedik át az ételt szállító utánpótlást, ezért a nép éhezik. Ha olyan erősnek érzed magad, mutasd is meg.",
                    quests[0]
                ),
                "player1" to listOf(
                    "Szevasz, persze! Nagyon egyszerű volt, csak pokolfajzatok voltak...",
                    "2"
                ),
                "player2" to listOf(
                    "Minden Tiszteletem! Próbálom legjobb tudásom szerint szolgálni a völgyet.",
                    "3"
                ),
                "player3" to listOf(
                    "Mi a feladat? Ha rám gondolt, azonnal vállalom.",
                    "4"
                ),
                "player4" to listOf(
                    "Mi lenne az? Egy újabb semmittevés?",
                    "5"
                ),
            )
        )
}
