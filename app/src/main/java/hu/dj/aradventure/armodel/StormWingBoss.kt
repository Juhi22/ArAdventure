package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Vector3
import hu.dj.aradventure.item.Quest
import hu.dj.aradventure.item.QuestList

object StormWingBoss : Enemy() {
    override val gltfPath: String
        get() = "storm_wing/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "storm_wing/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "Armature_Stand Ready_full",
                "attack" to "Armature_Spell J_full",
                "dead" to "Armature_Channel Hearth_full"
            )
        }

    override var health: Int = 5

    override var maxHealth: Int = 5

    override var damagePoint: Int = 1

    override var name = "Viharszárny"

    override var scale = Vector3(0.1F, 0.1F, 0.1F)

    override var invincibleUntilChapter = 2.2

    override var loopDeathAnimation: Boolean = false

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "storm_wing_boss/sounds/1.mp3",
                "attack/1" to "storm_wing_boss/sounds/1.mp3",
                "dead/1" to "storm_wing_boss/sounds/1.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
            "attack" to hashMapOf(
                "1" to listOf(
                    "Hát visszajöttél! Rossz döntés volt, a sárkány erőgömbök erejét felhasználtam, én vagyok a leghatalmasabb sárkány! Aki ellen mer nekem szegülni azt elpusztítom!",
                    null
                ),
            ),
            "dead" to hashMapOf(
                "1" to listOf(
                    "Ez nem lehet, ho-hogyan? Túl nagy erőre tettél szert! Ezt nem fogom annyiban hagyni! Legyőztek, de visszatérek még és bosszút állok!",
                    null
                ),
            )
        )
}
