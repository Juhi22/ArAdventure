package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Vector3
import hu.dj.aradventure.item.DragonPowerOrb
import hu.dj.aradventure.item.Item

object DragonLordSnowPrince: Enemy() {
    override val gltfPath: String
        get() = "dragon_lord_snow_prince/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "dragon_lord_snow_prince/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "ANI_YongYong_Idle_01",
                "attack" to "ANI_YongYong_Attack_Light_01",
                "dead" to "ANI_YongYong_Stun"
            )
        }

    override var health: Int = 5

    override var maxHealth: Int = 5

    override var damagePoint: Int = 1

    override var loopDeathAnimation: Boolean = false

    override var name = "Sárkányúr: Hó herceg"

    override var reward: Item? = DragonPowerOrb

    override var scale = Vector3(0.1F, 0.1F, 0.1F)

    override var invincibleUntilChapter = 1.0

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "dragon_lord_snow_prince/sounds/1.mp3",
                "attack/1" to "dragon_lord_snow_prince/sounds/1.mp3",
                "dead/1" to "dragon_lord_snow_prince/sounds/1.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
            "attack" to hashMapOf(
                "1" to listOf(
                    "Viharszárny egyik csatlósa személyesen, éhes lettem!",
                    null
                ),
            ),
            "dead" to hashMapOf(
                "1" to listOf(
                    "Neee, nem bukhatunk el!",
                    null
                ),
            )
        )
}