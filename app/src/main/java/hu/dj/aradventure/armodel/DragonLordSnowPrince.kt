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

    override var health: Int = 70

    override var maxHealth: Int = 70

    override var damagePoint: Int = 2

    override var loopDeathAnimation: Boolean = false

    override var name = "Sárkányúr: Hó herceg"

    override var reward: Item? = DragonPowerOrb

    override var scale = Vector3(0.1F, 0.1F, 0.1F)

    override var invincibleUntilChapter = 1.0

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "dragon_lord_snow_prince/sounds/dragon_lord_snow_prince_attack.mp3",
                "attack/1" to "dragon_lord_snow_prince/sounds/dragon_lord_snow_prince_attack.mp3",
                "dead/1" to "dragon_lord_snow_prince/sounds/dragon_lord_snow_prince_dead.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
            "attack" to hashMapOf(
                "1" to listOf(
                    "Mi van veled hóember? Mától a neved Olaf és te vagy az új játékszerem! Most azonnal játszani fogunk!",
                    null
                ),
            ),
            "dead" to hashMapOf(
                "1" to listOf(
                    "Áú! Csúnya vagy! Veled nem jó játszani és még a sárkány erő gömbömet is elveszed! Anyukám azt mondta a trón az enyém lesz.",
                    null
                ),
            )
        )
}