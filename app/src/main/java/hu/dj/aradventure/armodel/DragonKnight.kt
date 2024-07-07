package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Vector3

object DragonKnight: Enemy() {
    override val gltfPath: String
        get() = "dragon_knight/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "medieval_knight/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "Armature_Taunt_full",
                "attack" to "Armature_Attack_full",
                "dead" to "Armature_Stand_full"
            )
        }

    override var health: Int = 5

    override var maxHealth: Int = 5

    override var damagePoint: Int = 1

    override var name = "Sárkánylovag"

    override var invincibleUntilChapter = 2.1

    override var scale = Vector3(0.1F, 0.1F, 0.1F)

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "dragon_knight/sounds/1.mp3",
                "attack/1" to "dragon_knight/sounds/1.mp3",
                "dead/1" to "dragon_knight/sounds/1.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
                "attack" to hashMapOf(
                    "1" to listOf(
                        "Viharszárny ellen fordultál! Áruló!",
                        null
                    ),
                ),
                "dead" to hashMapOf(
                    "1" to listOf(
                        "Viharszárnynak kell megszereznie a trónt, ő a kiválasztott.",
                        null
                    ),
                )
            )
}