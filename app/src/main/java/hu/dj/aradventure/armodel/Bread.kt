package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Vector3
import hu.dj.aradventure.item.Bread
import hu.dj.aradventure.item.Item

object Bread : Collectable() {
    override val gltfPath: String
        get() = "bread/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "bread/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "3dsMax publisher"
            )
        }

    override var item: Item = Bread

    override var name = "Kenyér"

    override var scale = Vector3(0.01F, 0.01F, 0.01F)
}