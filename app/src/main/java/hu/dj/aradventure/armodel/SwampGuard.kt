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
        QuestList.list[11]!!,
        QuestList.list[12]!!,
    )

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "swamp_guard/sounds/default.mp3",
                "quest10" to "swamp_guard/sounds/quest10_1.mp3",
                "quest10" to "swamp_guard/sounds/quest10_2.mp3",
                "quest10" to "swamp_guard/sounds/quest10_3.mp3",
                "quest10" to "swamp_guard/sounds/quest10_4.mp3",
                "quest10" to "swamp_guard/sounds/quest10_5.mp3",
                "quest11" to "swamp_guard/sounds/quest11_1.mp3",
                "quest11" to "swamp_guard/sounds/quest10_3.mp3",
                "quest11" to "swamp_guard/sounds/quest11_3.mp3",
                quests[0].name + "/1" to "swamp_guard/sounds/quest_0_1.mp3",
                quests[0].name + "/2" to "swamp_guard/sounds/quest10_3.mp3",
                quests[0].name + "/3" to "swamp_guard/sounds/quest_0_3.mp3",
                quests[0].name + "/4" to "swamp_guard/sounds/quest11_3.mp3",
                quests[1].name + "/1" to "swamp_guard/sounds/quest_1_1.mp3",
                quests[1].name + "/2" to "swamp_guard/sounds/quest_1_2.mp3",
                quests[2].name + "/1" to "swamp_guard/sounds/quest_2_1.mp3",
                quests[2].name + "/2" to "swamp_guard/sounds/quest_2_2.mp3",
                quests[2].name + "/3" to "swamp_guard/sounds/quest_2_3.mp3",
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
            quests[2].name to hashMapOf(
                "1" to listOf(
                    "Itt is van a kulcs! Remek! Nagyon jó szolgálatot tettél, mostmár ki tudom nyitni a zsilipet és végre tudom hajtani a tervem.",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "Legjobbakat, sok szerencsét neked is. Kelleni fog!",
                    null
                ),
                "3" to listOf(
                    "Nincs több ogre az utamban, nálam a zsilip kulcsa és a méregből kikevert vegyülettel elpusztíthatom... ööö akarom mondani megtisztíthatom a völgyet a gonosztól!",
                    listOf("player1", "player3")
                ),
                "player1" to listOf(
                    "Örülök, hogy segíthettem, viszlát!",
                    "2"
                ),
                "player2" to listOf(
                    "Miféle terv?",
                    "3"
                ),
                "player3" to listOf(
                    "Remélem nem sántikálsz semmi rosszban, mert akkor még találkozunk! Addig is viszlát!",
                    "2"
                ),
            ),
            quests[1].name to hashMapOf(
                "1" to listOf(
                    "Meg is hoztad a mérget, remek. Még egy dolog lenne.",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "Egy sárkány pusztítja a mocsarat. Senkit nem enged be. Győzd le és hozd el nekem a nála lévő vörös kulcsot!",
                    quests[2]
                ),
                "player1" to listOf(
                    "Teljesítem bármi legyen is az!",
                    "2"
                ),
                "player2" to listOf(
                    "De ez az utolsó!",
                    "2"
                ),
            ),
            "quest11" to hashMapOf(
                "1" to listOf(
                    "Lenne még feladatom a számodra. Gyűjts nekem mérget, egy adalékot keverek amivel meg tudom tisztítani a mocsár vizét.",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "Hát jó, ha meggondolnád magad itt leszek.",
                    null
                ),
                "3" to listOf(
                    "Remek, alig várom.",
                    quests[1]
                ),
                "player1" to listOf(
                    "Mindjárt hozom.",
                    "3"
                ),
                "player2" to listOf(
                    "Köszönöm, de nem érdekel.",
                    "2"
                ),
            ),
            quests[0].name to hashMapOf(
                "1" to listOf(
                    "Nagyon jó, nagyon jó... Lenne még feladatom a számodra.",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "Hát jó, ha meggondolnád magad itt leszek.",
                    null
                ),
                "3" to listOf(
                    "Gyűjts nekem mérget, egy adalékot keverek amivel meg tudom tisztítani a mocsár vizét.",
                    listOf("player2", "player3")
                ),
                "4" to listOf(
                    "Remek, alig várom.",
                    quests[1]
                ),
                "player1" to listOf(
                    "Mi lenne az?",
                    "3"
                ),
                "player2" to listOf(
                    "Köszönöm, de nem érdekel.",
                    "2"
                ),
                "player3" to listOf(
                    "Mindjárt hozom.",
                    "4"
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
