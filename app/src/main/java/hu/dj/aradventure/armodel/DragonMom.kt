package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Vector3
import hu.dj.aradventure.item.Quest
import hu.dj.aradventure.item.QuestList

object DragonMom : ArModel() {
    override val gltfPath: String
        get() = "dragon_mom/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "dragon_mom/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "Flying"
            )
        }

    override var name = "Sárkány anya"

    override val quests: List<Quest> = listOf(
        QuestList.list[2]!!,
        QuestList.list[9]!!,
    )

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "dragon_mom/sounds/default.mp3",
                "quest2/1" to "dragon_mom/sounds/quest2_1.mp3",
                "quest2/2" to "dragon_mom/sounds/quest2_2.mp3",
                "quest2/3" to "dragon_mom/sounds/quest2_3.mp3",
                "quest2/4" to "dragon_mom/sounds/quest2_4.mp3",
                "quest9/1" to "dragon_mom/sounds/quest9_1.mp3",
                "quest9/2" to "dragon_mom/sounds/quest9_2.mp3",
                "quest9/3" to "dragon_mom/sounds/quest2_2.mp3",
                quests[0].name + "/1" to "dragon_mom/sounds/quest_0_1.mp3",
                quests[0].name + "/2" to "dragon_mom/sounds/quest_0_2.mp3",
                quests[0].name + "/3" to "dragon_mom/sounds/quest9_2.mp3",
                quests[0].name + "/4" to "dragon_mom/sounds/quest2_2.mp3",
                quests[1].name + "/1" to "dragon_mom/sounds/quest_1_1.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
            "default" to hashMapOf(
                "1" to listOf(
                    "Ön a mi hősünk!",
                    null
                ),
            ),
            quests[0].name to hashMapOf(
                "1" to listOf(
                    "Nagyon szépen köszönöm, hogy hazahoztad!",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "Lenne még egy apróság, ma még nem reggeliztünk. Tudnál hozni nekünk a boltból valamilyen pékárut?",
                    listOf("player3", "player4")
                ),
                "3" to listOf(
                    "Nagyon hálás vagyok, egy kenyér nagyon jó lenne.",
                    quests[1]
                ),
                "4" to listOf(
                    "Bocsánat, nem akartam rabolni az idejét.",
                    null
                ),
                "player1" to listOf(
                    "Legközelebb ne engedd a gyereket repkedni, ilyen felelőtlenséget...",
                    "2"
                ),
                "player2" to listOf(
                    "Ez csak természetes, van bármi amiben tudok még segíteni?",
                    "2"
                ),
                "player3" to listOf(
                    "Természetesen! Egy hős nem hagyhatja, hogy éhezzetek. Milyen termék legyen?",
                    "3"
                ),
                "player4" to listOf(
                    "Felejtsd el, így is sok időt töltöttem gyerek felvigyázással",
                    "4"
                )
            ),
            quests[1].name to hashMapOf(
                "1" to listOf(
                    "Nagyon szépen köszönöm, végre egy nagyon finom reggelit fogok tudni adni a gyermekemnek!",
                    null
                )
            ),
            "quest2" to hashMapOf(
                "1" to listOf(
                    "Hála istennek, Ön egy igazi hős? Kérem, tudna nekem segíteni?",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "Bocsánat, nem akartam rabolni az idejét.",
                    null
                ),
                "3" to listOf(
                    "Igen, nagy a baj! A gyermekem abban a korban van amikor az első szárnycsapásokkal próbálkoznak. Csak egy pillanatra nem figyeltem és elrepülhetett. Kérem segítsen megtalálni.",
                    listOf("player3", "player4")
                ),
                "4" to listOf(
                    "Köszönöm, nagyon hálás vagyok a segítségéért!",
                    quests[0]
                ),
                "player1" to listOf(
                    "Igen egy igazi hős vagyok, van sokkal fontosabb dolgom is!",
                    "2"
                ),
                "player2" to listOf(
                    "Természetesen segítek. Mi történt? Van valami baj?",
                    "3"
                ),
                "player3" to listOf(
                    "Ez egy vicc? Erre nincs időm, van fontosabb dolog is ennél.",
                    "2"
                ),
                "player4" to listOf(
                    "Nyugodjon meg, biztos itt van valahol a közelben. Ígérem, hogy hazahozom.",
                    "4"
                ),
            ),
            "quest9" to hashMapOf(
                "1" to listOf(
                    "A mi hősünk újra itt. Mi szél hozta Önt erre?",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "Nagyon hálás vagyok, egy kenyér nagyon jó lenne.",
                    quests[1]
                ),
                "3" to listOf(
                    "Bocsánat, nem akartam rabolni az idejét.",
                    null
                ),
                "player1" to listOf(
                    "Jöttem beszerezni a pékárut, mire van szükség?",
                    "2"
                ),
                "player2" to listOf(
                    "Csak nézelődök, egy hős mindig elfoglalt és sosem ér rá.",
                    "3"
                ),
            ),
        )
}
