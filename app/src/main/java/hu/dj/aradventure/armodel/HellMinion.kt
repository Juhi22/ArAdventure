package hu.dj.aradventure.armodel

object HellMinion: Enemy() {
    override val gltfPath: String
        get() = "hell_minion/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "hell_minion/fiducial_marker.jpeg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "Armature.001_Stand_full",
                "attack" to "Armature.001_Attack 01_full",
                "dead" to "Armature.001_Stun_full"
            )
        }

    override var health: Int = 5

    override var maxHealth: Int = 5

    override var damagePoint: Int = 1

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "hell_minion/sounds/1.mp3",
                "attack/1" to "hell_minion/sounds/1.mp3",
                "dead/1" to "hell_minion/sounds/1.mp3",
            )
        }

    override val script: MutableMap<String, Any>
        get() {
            return hashMapOf(
                "attack" to hashMapOf(
                    "1" to listOf(
                        "Hogy merészelsz megzavarni? Adok egy kóstolót az öklömből!",
                        null
                    ),
                ),
                "dead" to hashMapOf(
                    "1" to listOf(
                        "AJDOAIJ(%+U+)(QFQAKJLKA!",
                        null
                    ),
                )
            )
        }
}