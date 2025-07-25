package hu.dj.aradventure.armodel

import hu.dj.aradventure.item.DragonPowerOrb
import hu.dj.aradventure.item.Item

object DragonLordBlack: Enemy() {
    override val gltfPath: String
        get() = "dragon_lord_black/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "dragon_lord_black/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "Idle",
                "attack" to "Roar",
                "dead" to "Die"
            )
        }

    override var health: Int = 70

    override var maxHealth: Int = 70

    override var damagePoint: Int = 2

    override var loopDeathAnimation: Boolean = false

    override var name = "Sárkányúr: Fekete"

    override var reward: Item? = DragonPowerOrb

    override var invincibleUntilChapter = 1.0

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "dragon_lord_black/sounds/black_attack.mp3",
                "attack/1" to "dragon_lord_black/sounds/black_attack.mp3",
                "dead/1" to "dragon_lord_black/sounds/black_dead.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
                "attack" to hashMapOf(
                    "1" to listOf(
                        "Viharszárny egyik csatlósa személyesen, éhes lettem!",
                        null
                    ),
                ),
                "dead" to hashMapOf(
                    "1" to listOf(
                        "Neee, nem bukhatunk el!",
                        null
                    ),
                )
            )
}