package hu.dj.aradventure.armodel

object Ent: Enemy() {
    override val gltfPath: String
        get() = "ent/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "ent/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "idle",
                "attack" to "attack",
                "dead" to "death"
            )
        }

    override var health: Int = 50

    override var maxHealth: Int = 50

    override var damagePoint: Int = 1

    override var loopDeathAnimation: Boolean = false

    override var name = "Erdő szelleme"

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "ent/sounds/ent_attack.mp3",
                "attack/1" to "ent/sounds/ent_attack.mp3",
                "dead/1" to "ent/sounds/ent_dead.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
                "attack" to hashMapOf(
                    "1" to listOf(
                        "Aki pusztítja a természetet az ellenünk van, távozz!!!",
                        null
                    ),
                ),
                "dead" to hashMapOf(
                    "1" to listOf(
                        "Földanya, én megpróbáltam...",
                        null
                    ),
                )
            )
}