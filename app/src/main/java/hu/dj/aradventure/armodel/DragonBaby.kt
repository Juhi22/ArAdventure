package hu.dj.aradventure.armodel

import hu.dj.aradventure.item.DragonBaby
import hu.dj.aradventure.item.Item

object DragonBaby : Collectable() {
    override val gltfPath: String
        get() = "dragon_baby/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "dragon_baby/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "Take 001"
            )
        }

    override var item: Item = DragonBaby

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "dragon_baby/sounds/baby_giggles.mp3"
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
            "default" to hashMapOf(
                "1" to listOf(
                    "*gügyögés",
                    null
                ),
            ),
        )
}