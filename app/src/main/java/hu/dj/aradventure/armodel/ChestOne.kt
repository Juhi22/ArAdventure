package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Vector3
import hu.dj.aradventure.item.HealthRing
import hu.dj.aradventure.item.Item

object ChestOne : Collectable() {
    override val gltfPath: String
        get() = "chest_1/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "chest_1/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "Chest_open"
            )
        }

    override var item: Item = HealthRing

    override var name = "Kincsesláda"

    override var scale = Vector3(0.001F, 0.001F, 0.001F)

    override var loopIdleAnimation = false

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "common_sounds/chest_opening.mp3"
            )
        }
}