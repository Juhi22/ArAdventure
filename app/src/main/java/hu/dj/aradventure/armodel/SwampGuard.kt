package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Vector3
import hu.dj.aradventure.item.Quest
import hu.dj.aradventure.item.QuestList

object SwampGuard : ArModel() {
    override val gltfPath: String
        get() = "swamp_guard/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "swamp_guard/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "Animation"
            )
        }

    override var name = "Mocsár őrző"

    override val quests: List<Quest> = listOf(
        QuestList.list[10]!!,
    )

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "swamp_guard/sounds/1.mp3",
                quests[0].name + "/1" to "swamp_guard/sounds/1.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
            "default" to hashMapOf(
                "1" to listOf(
                    "Mi van? Kell valami? Most nem érek rá.",
                    null
                ),
            ),
            quests[0].name to hashMapOf(
                "1" to listOf(
                    "Nagyon jó, nagyon jó...",
                    null
                ),
            ),
            "quest10" to hashMapOf(
                "1" to listOf(
                    "Psszt... Igen... Te ott...",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "Olyan hős félének tűnsz, vállalsz feladatokat? Lenne számodra néhány.",
                    listOf("player3", "player4")
                ),
                "3" to listOf(
                    "Hát jó, ha meggondolnád magad itt leszek.",
                    null
                ),
                "4" to listOf(
                    "A mocsarat felügyelem és egy csöppet sok lett itt az ogre. Nem lehet velük bírni, csak a baj van velük.",
                    listOf("player5", "player6")
                ),
                "5" to listOf(
                    "Remek. Egy erő amulettel tudok fizetni, amint végeztél a feladatoddal.",
                    quests[0]
                ),
                "player1" to listOf(
                    "Igen?",
                    "2"
                ),
                "player2" to listOf(
                    "Esetleg köszönni?",
                    "2"
                ),
                "player3" to listOf(
                    "Nem vállalok semmit mindenféle idegennek.",
                    "3"
                ),
                "player4" to listOf(
                    "Vállalok, mi lenne az.",
                    "4"
                ),
                "player5" to listOf(
                    "Segítek, mi a fizettség?",
                    "5"
                ),
                "player6" to listOf(
                    "Ogrebarát vagyok, kösz nem élek a lehetőséggel.",
                    "3"
                ),
            ),
        )

    override var scale = Vector3(0.05F, 0.05F, 0.05F)
}
