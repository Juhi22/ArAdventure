package hu.dj.aradventure.armodel

object GoldFish: Enemy() {
    override val gltfPath: String
        get() = "gold_fish/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "gold_fish/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "Idle_g",
                "attack" to "Standing_Shoot_Super_g",
                "dead" to "Idle_g"
            )
        }

    override var health: Int = 5

    override var damagePoint: Int = 1

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "gold_fish/sounds/1.mp3"
            )
        }
}