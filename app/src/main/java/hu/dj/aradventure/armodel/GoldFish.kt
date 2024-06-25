package hu.dj.aradventure.armodel

import hu.dj.aradventure.item.HealthAmulet
import hu.dj.aradventure.item.Item

object GoldFish : Enemy() {
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

    override var maxHealth: Int = 5

    override var damagePoint: Int = 1

    override var reward: Item? = HealthAmulet

    override var name = "Aranyhal"

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "gold_fish/sounds/1.mp3",
                "attack/1" to "gold_fish/sounds/1.mp3",
                "attack/2" to "gold_fish/sounds/1.mp3",
                "attack/3" to "gold_fish/sounds/1.mp3",
                "attack/4" to "gold_fish/sounds/1.mp3",
                "attack/5" to "gold_fish/sounds/1.mp3",
                "dead/1" to "gold_fish/sounds/1.mp3",
                "dead/2" to "gold_fish/sounds/1.mp3"
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
            "attack" to hashMapOf(
                "1" to listOf(
                    "Hello idegen! Mi szél hozott a tavamhoz?",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "El kell, hogy szomorítsalak, ez nem így működik. Viszont tesztelni kellene az új ruhámat, most varrtam.",
                    listOf("player3", "player4")
                ),
                "3" to listOf(
                    "Látom a nyelvtannal vannak problémák. Nem tudom, honnan vetted, hogy kívánságokat teljesítek. Pont jó alany lennél a ruhám az új ruhám tesztelésére.",
                    listOf("player3", "player4")
                ),
                "4" to listOf(
                    "Kösd fel a gatyád!",
                    null
                ),
                "5" to listOf(
                    "Teszteljük le, hogy le tudsz e győzni, ha ezt a rucit hordom. Semmi kívánság, de ha legyőzöl adok valami értékeset.",
                    listOf("player3", "player3")
                ),
                "player1" to listOf(
                    "Szia, téged kereslek hátha tudsz segíteni egy kívánság megvalósításában.",
                    "2"
                ),
                "player2" to listOf(
                    "Te lenni aranyhal, kívánságot valósítani meg nekem!!!",
                    "3"
                ),
                "player3" to listOf(
                    "Vágjunk bele!",
                    "4"
                ),
                "player4" to listOf(
                    "Tesztelni? Ez mit jelent? Cserébe teljesíted a kivánságomat?",
                    "5"
                ),
            ),
            "dead" to hashMapOf(
                "1" to listOf(
                    "Ez igen! Erre nem számítottam, legyőztél. Új ruha után kell néznem...",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "Nem felejtettem el, ami jár az jár.",
                    null
                ),
                "player1" to listOf(
                    "Nem volt nehéz, semmi esélyed sem volt.",
                    "2"
                ),
                "player2" to listOf(
                    "Méltó ellenfél voltál.",
                    "2"
                ),
            )
        )
}