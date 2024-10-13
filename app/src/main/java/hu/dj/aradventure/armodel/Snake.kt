package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Quaternion
import hu.dj.aradventure.item.Item
import hu.dj.aradventure.item.Poison

object Snake: Enemy() {
    override val gltfPath: String
        get() = "snake/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "snake/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "RIGI|Walk_Cycle",
                "attack" to "RIGI|Close_Attack",
                "dead" to "RIGI|Snake_Death :("
            )
        }

    override var health: Int = 20

    override var maxHealth: Int = 20

    override var damagePoint: Int = 3

    override var reward: Item? = Poison

    override var name = "Kígyó"

    override var rotationDegree = 270f

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "snake/sounds/snake_attack.mp3",
                "attack/1" to "snake/sounds/snake_attack.mp3",
                "dead/1" to "snake/sounds/snake_attack.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
                "attack" to hashMapOf(
                    "1" to listOf(
                        "SSSSzzzzzzzz",
                        null
                    ),
                ),
                "dead" to hashMapOf(
                    "1" to listOf(
                        "SSSSzzzzzzzz",
                        null
                    ),
                )
            )
}