package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Vector3

object ChickenSandwich: Food() {
    override val gltfPath: String
        get() = "chicken_sandwich/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "chicken_sandwich/fiducial_marker.jpg"

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "chicken_sandwich/sounds/eating-sound-effect.mp3",
            )
        }

    override var healPoint: Int = 1

    override var scale = Vector3(0.05F, 0.05F, 0.05F)

    override var name = "Szendvics"
}