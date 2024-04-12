package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Vector3

open class ArModel() {
    open val gltfPath: String = "";
    open val fiducialMarkerPath: String = "";
    open val animations = mutableMapOf<String, String>()
    open var health: Int = 1
    open val script = emptyMap<String, Any>()
    open val sounds = emptyMap<String, String>()
    open var scale = Vector3(0.4F, 0.4F, 0.4F)
}
