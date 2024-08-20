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

    override var health: Int = 50

    override var maxHealth: Int = 50

    override var damagePoint: Int = 2

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