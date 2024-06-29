package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Vector3
import hu.dj.aradventure.item.Quest
import hu.dj.aradventure.item.QuestList

object StormWing : ArModel() {
    override val gltfPath: String
        get() = "storm_wing/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "storm_wing/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "Armature_Stand Ready_full"
            )
        }

    override var name = "Viharszárny"

    override val quests: List<Quest> = listOf(
        QuestList.list[3]!!,
        QuestList.list[4]!!,
    )

    override var scale = Vector3(0.1F, 0.1F, 0.1F)

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "storm_wing/sounds/1.mp3",
                "default/1" to "storm_wing/sounds/1.mp3",
                "0.1/1" to "storm_wing/sounds/1.mp3",
                "0.1/2" to "storm_wing/sounds/1.mp3",
                "0.1/3" to "storm_wing/sounds/1.mp3",
                "0.1/4" to "storm_wing/sounds/1.mp3",
                "0.1/5" to "storm_wing/sounds/1.mp3",
                quests[0].name + "/1" to "storm_wing/sounds/1.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
            "default" to hashMapOf(
                "1" to listOf(
                    "Hosszú az út, de végül békét teremtünk a völgyben!",
                    null
                ),
            ),
            quests[0].name to hashMapOf(
                "1" to listOf(
                    "Jó szolgálatot tettél, mostmár a szövetségeseink újra ételhez juthatnak.",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "Fő lovagom nem hiába ajánlott téged, aki képes legyőzni az erdő szellemeit, sokkal többre hivatott.",
                    listOf("player3", "player4")
                ),
                "3" to listOf(
                    "Van egy nagyon fontos feladat, amit sikeresen végre tudnál hajtani. Ez nem egyszerű, még senkinek sem sikerült.",
                    listOf("player5", "player6")
                ),
                "4" to listOf(
                    "Ha kipihented magad, gyere vissza lenne egy feladat a számodra.",
                    listOf("player7", "player8")
                ),
                "5" to listOf(
                    "Becsokiztál? Azt hittem keményebb fából faragtak. Biztosan hallottál már a sárkány urakról. Én is egy vagyok közülük.",
                    listOf("player9", "player10")
                ),
                "6" to listOf(
                    "Biztosan hallottál már a sárkány urakról. Én is egy vagyok közülük.",
                    listOf("player9", "player10")
                ),
                "7" to listOf(
                    "Akkor biztos azt is tudod, hogy az egyik közülük, Hó herceg, többet akart ennél. Nem engedhetjük, hogy egyeduralomra törjön és káoszt teremtsen.",
                    listOf("player11", "player12")
                ),
                "8" to listOf(
                    "Azokról a sárkányokról beszélsz akik együtt uralták a völgyet, békében. Az egyik közülük, Hó herceg, többet akart ennél. Nem engedhetjük, hogy egyeduralomra törjön és káoszt teremtsen.",
                    listOf("player11", "player12")
                ),
                "9" to listOf(
                    "Ahhoz, hogy a béke visszaálljon, meg kell szereznünk a sárkány erőgömböket tőlük, hogy egyesítve őket békét teremtsünk.",
                    listOf("player13", "player14")
                ),
                "10" to listOf(
                    "Sok szerencsét kívánok, járj sikerrel!",
                    quests[1]
                ),
                "player1" to listOf(
                    "Megtettem amit meg kellett, én is éhes voltam.",
                    "2"
                ),
                "player2" to listOf(
                    "Kérésed számomra parancs, a jó cél érdekében bármit.",
                    "2"
                ),
                "player3" to listOf(
                    "Köszönöm, tudok még valamiben segíteni?",
                    "3"
                ),
                "player4" to listOf(
                    "Köszönöm",
                    "4"
                ),
                "player5" to listOf(
                    "Senkinek?! Becsokiztam!",
                    "5"
                ),
                "player6" to listOf(
                    "Mi lenne az? Meg teszek minden tőlem telhetőt!",
                    "6"
                ),
                "player7" to listOf(
                    "A béke nevében érkeztem a völgybe, nincs idő pihenni.",
                    "3"
                ),
                "player8" to listOf(
                    "Pihenni... nem kell nekem lustaság. Bármit bármikor.",
                    "3"
                ),
                "player9" to listOf(
                    "Igen, ismerem a történetet.",
                    "7"
                ),
                "player10" to listOf(
                    "Sárkányok és még urak is?",
                    "8"
                ),
                "player11" to listOf(
                    "Hó herceg?! A sárkányok tényleg nem értenek a nevekhez. Meg kellene látogatnom és új nevet adni neki?",
                    "9"
                ),
                "player12" to listOf(
                    "Nem is fogom engedni, hogy káoszt teremtsen!",
                    "9"
                ),
                "player13" to listOf(
                    "Felkészülök a feladatra, napnyugta előtt útra kelek.",
                    "10"
                ),
                "player14" to listOf(
                    "Nem hangzik nehéznek, 10 perc és itt vagyok.",
                    "10"
                ),
            ),
            "0.1" to hashMapOf(
                "1" to listOf(
                    "Üdvözöllek, Viharszárny vagyok. Megüzenték, hogy sikeresen helyt álltál.",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "Erős és nagyképű, csak egy újabb átlagos hős lennél? A napokban probléma adódott amit haladéktalanul meg kell oldani.",
                    listOf("player3", "player4")
                ),
                "3" to listOf(
                    "Erős és vakmerő, több ilyen hős kellene a seregembe. A napokban probléma adódott amit haladéktalanul meg kell oldani.",
                    listOf("player3", "player4")
                ),
                "4" to listOf(
                    "Az erdő szellemei testet öltöttek és a gonosz oldalára álltak, nem engedik át az ételt szállító utánpótlást, ezért a nép éhezik. Eltökélt vagy ez tetszik.",
                    quests[0]
                ),
                "5" to listOf(
                    "Semmittevés? Az erdő szellemei testet öltöttek és a gonosz oldalára álltak, nem engedik át az ételt szállító utánpótlást, ezért a nép éhezik. Ha olyan erősnek érzed magad, mutasd is meg.",
                    quests[0]
                ),
                "player1" to listOf(
                    "Szevasz, persze! Nagyon egyszerű volt, csak pokolfajzatok voltak...",
                    "2"
                ),
                "player2" to listOf(
                    "Minden Tiszteletem! Próbálom legjobb tudásom szerint szolgálni a völgyet.",
                    "3"
                ),
                "player3" to listOf(
                    "Mi a feladat? Ha rám gondolt, azonnal vállalom.",
                    "4"
                ),
                "player4" to listOf(
                    "Mi lenne az? Egy újabb semmittevés?",
                    "5"
                ),
            )
        )
}
