package hu.dj.aradventure.armodel

import hu.dj.aradventure.item.Item
import hu.dj.aradventure.item.RedKey

object LightDragon: Enemy() {
    override val gltfPath: String
        get() = "light_dragon/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "light_dragon/fiducial_marker.jpeg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "stand",
                "attack" to "atk01",
                "dead" to "deaddown"
            )
        }

    override var health: Int = 70

    override var maxHealth: Int = 70

    override var damagePoint: Int = 2

    override var loopDeathAnimation: Boolean = false

    override var name = "Fénysárkány"

    override var reward: Item? = RedKey

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "light_dragon/sounds/1.mp3",
                "attack/1" to "light_dragon/sounds/1.mp3",
                "dead/1" to "light_dragon/sounds/1.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
                "attack" to hashMapOf(
                    "1" to listOf(
                        "A mocsarat meg kell tisztítani a gonosztól. Te nem a fényt szolgálod! Készülj!",
                        null
                    ),
                ),
                "dead" to hashMapOf(
                    "1" to listOf(
                        "A fény nem aludhat ki!",
                        null
                    ),
                )
            )
}