package hu.dj.aradventure.armodel

import hu.dj.aradventure.item.DragonPowerOrb
import hu.dj.aradventure.item.Item

object DragonLordHorn: Enemy() {
    override val gltfPath: String
        get() = "dragon_lord_horn/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "dragon_lord_horn/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "Idle 2",
                "attack" to "Tail Whip",
                "dead" to "Furious/Die"
            )
        }

    override var health: Int = 70

    override var maxHealth: Int = 70

    override var damagePoint: Int = 2

    override var loopDeathAnimation: Boolean = false

    override var name = "Sárkányúr: Agyar"

    override var reward: Item? = DragonPowerOrb

    override var invincibleUntilChapter = 1.0

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "dragon_lord_horn/sounds/dragon_lord_horn_attack.mp3",
                "attack/1" to "dragon_lord_horn/sounds/dragon_lord_horn_attack.mp3",
                "dead/1" to "dragon_lord_horn/sounds/dragon_lord_horn_dead.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
                "attack" to hashMapOf(
                    "1" to listOf(
                        "Mit akarsz olajbogyó? Szétkaplak mint Foxi a lábtörlőt!",
                        null
                    ),
                ),
                "dead" to hashMapOf(
                    "1" to listOf(
                        "Az erkölcsi siker akkor is az enyém!",
                        null
                    ),
                )
            )
}