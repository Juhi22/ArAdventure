package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Vector3

object Unicorn : ArModel() {
    override val gltfPath: String
        get() = "unicorn/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "unicorn/fiducial_marker.jpg"

    override var scale = Vector3(0.05F, 0.05F, 0.05F)

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "CINEMA_4D_Main"
            )
        }

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "unicorn/sounds/1.mp3",
                "0.0/1" to "unicorn/sounds/1.mp3",
                "0.0/2" to "unicorn/sounds/1.mp3",
                "0.0/3" to "unicorn/sounds/1.mp3",
                "0.0/4" to "unicorn/sounds/1.mp3",
                "0.0/5" to "unicorn/sounds/1.mp3",
            )
        }

    override val script: Map<String, Any>
        get() {
            return hashMapOf(
                "0.0" to hashMapOf(
                    "1" to listOf(
                        "Üdvözöllek Sárkányvölgyben! Köszönjük, hogy ilyen gyorsan jöttél és segítesz nekünk a láda kinyitásában és a gonosz legyőzésében!",
                        listOf("player1", "player2")
                    ),
                    "2" to listOf(
                        "Igazi bajtárs vagy! Menj a trónterembe és találkozz Viharszárnnyal, Ő vezet minket.",
                        listOf("player3", "player4")
                    ),
                    "3" to listOf(
                        "Elég flúgos figura vagy... Menj a trónterembe és beszélj Viharszárnnyal, Ő vezet minket. Előtte ne beszélj össze-vissza!",
                        listOf("player3", "player4")
                    ),
                    "4" to listOf(
                        "Ő az utolsó sárkány úr aki a békét és jólétet próbálja visszaállítani, támogatnunk kell ebben!",
                        null
                    ),
                    "5" to listOf(
                        "Na ne szórakozz! Haladj!",
                        null
                    ),
                    "player1" to listOf(
                        "Jöttem, ahogy csak tudtam, elvégre segítenünk kell egymást!",
                        "2"
                    ),
                    "player2" to listOf(
                        "Nem tudom miről beszélsz, én csak túrázgatok.",
                        "3"
                    ),
                    "player3" to listOf(
                        "Ki az a Viharszárny?",
                        "4"
                    ),
                    "player4" to listOf(
                        "Félek a sárkányoktól, ez problémát jelent?",
                        "5"
                    ),
                )
            )
        }
}
