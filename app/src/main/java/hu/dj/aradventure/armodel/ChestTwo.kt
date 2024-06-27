package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Vector3
import hu.dj.aradventure.item.Item
import hu.dj.aradventure.item.PowerAmulet

object ChestTwo : Collectable() {
    override val gltfPath: String
        get() = "chest_2/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "chest_2/fiducial_marker.png"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "Armature|Opening"
            )
        }

    override var item: Item = PowerAmulet

    override var name = "Kincsesláda"

    override var scale = Vector3(0.001F, 0.001F, 0.001F)

    override var loopIdleAnimation = false

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "common_sounds/chest_opening.mp3"
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
            "default" to hashMapOf(
                "1" to listOf(
                    "*vajon mit rejt?",
                    null
                ),
            ),
        )
}