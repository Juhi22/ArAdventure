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

    //override var scale = Vector3(0.001F, 0.001F, 0.001F)

    override val quests: List<Quest> = listOf(
        QuestList.list[2]!!,
    )

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "dragon_mom/sounds/1.mp3",
                "default/1" to "dragon_mom/sounds/1.mp3",
                "default/2" to "dragon_mom/sounds/1.mp3",
                "default/3" to "dragon_mom/sounds/1.mp3",
                "default/4" to "dragon_mom/sounds/1.mp3",
                "0.0/1" to "dragon_mom/sounds/1.mp3",
                "0.0/2" to "dragon_mom/sounds/1.mp3",
                "0.0/3" to "dragon_mom/sounds/1.mp3",
                "0.0/4" to "dragon_mom/sounds/1.mp3",
                "0.0/5" to "dragon_mom/sounds/1.mp3",
                quests[0].name + "/1" to "dragon_mom/sounds/1.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
            quests[0].name to hashMapOf(
                "1" to listOf(
                    "Nagyon szépen köszönöm, hogy hazahoztad!",
                    null
                )
            ),
            "default" to hashMapOf(
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
            )
        )
}
