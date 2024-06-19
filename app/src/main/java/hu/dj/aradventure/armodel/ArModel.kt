package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Vector3
import hu.dj.aradventure.item.Quest

open class ArModel() {
    open val gltfPath: String = "";
    open val fiducialMarkerPath: String = "";
    open val animations = mutableMapOf<String, String>()
    open var health: Int = 1
    open var maxHealth: Int = 1
    open var script = mutableMapOf<String, Any>()
    open val sounds = emptyMap<String, String>()
    open val quests = emptyList<Quest>()
    open var scale = Vector3(0.4F, 0.4F, 0.4F)
    open var useCustomCollisionShape = false
}
