package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Vector3

object Ogre: Enemy() {
    override val gltfPath: String
        get() = "ogre/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "ogre/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "idle",
                "attack" to "punch",
                "dead" to "death"
            )
        }

    override var health: Int = 5

    override var maxHealth: Int = 5

    override var damagePoint: Int = 1

    override var loopDeathAnimation: Boolean = false

    override var scale = Vector3(0.2F, 0.2F, 0.2F)

    override var name = "Ogre"

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "ogre/sounds/ogre_attack.mp3",
                "attack/1" to "ogre/sounds/ogre_attack.mp3",
                "dead/1" to "ogre/sounds/ogre_dead.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
                "attack" to hashMapOf(
                    "1" to listOf(
                        "Hé, szép páncél, most adod ide nekem!",
                        null
                    ),
                ),
                "dead" to hashMapOf(
                    "1" to listOf(
                        "Aaarrrgh!",
                        null
                    ),
                )
            )
}