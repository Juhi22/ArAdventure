package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Vector3

object DragonSlave: Enemy() {
    override val gltfPath: String
        get() = "dragon_slave/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "dragon_slave/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "Idle.001",
                "attack" to "Attack",
                "dead" to "Idle.001"
            )
        }

    override var health: Int = 5

    override var maxHealth: Int = 5

    override var damagePoint: Int = 1

    override var scale = Vector3(10F, 10F, 10F)

    override var useCustomCollisionShape: Boolean = true

    override var name = "Sárkány szolga"

    override var invincibleUntilChapter = 2.0F

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "dragon_slave/sounds/dragon_growl.mp3",
                "attack/1" to "dragon_slave/sounds/dragon_growl.mp3",
                "dead/1" to "dragon_slave/sounds/1.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
                "attack" to hashMapOf(
                    "1" to listOf(
                        "GGGGRRRRRRRRRRRRR!!!",
                        null
                    ),
                ),
                "dead" to hashMapOf(
                    "1" to listOf(
                        "Sza-szabad vagyok! Kiszabadítottál! Köszönöm!",
                        null
                    ),
                )
            )
}