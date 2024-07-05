package hu.dj.aradventure.armodel

import com.google.ar.sceneform.math.Vector3
import hu.dj.aradventure.item.Quest
import hu.dj.aradventure.item.QuestList

object Unicorn : ArModel() {
    override val gltfPath: String
        get() = "unicorn/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "unicorn/fiducial_marker.jpg"

    override var scale = Vector3(0.05F, 0.05F, 0.05F)

    override var name = "Unikornis"

    override val quests: List<Quest> = listOf(
        QuestList.list[5]!!,
    )

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "CINEMA_4D_Main"
            )
        }

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "unicorn/sounds/1.mp3",
                "0.0/1" to "unicorn/sounds/1.mp3",
                "0.0/2" to "unicorn/sounds/1.mp3",
                "0.0/3" to "unicorn/sounds/1.mp3",
                "0.0/4" to "unicorn/sounds/1.mp3",
                "0.0/5" to "unicorn/sounds/1.mp3",
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
            "1.1" to hashMapOf(
                "1" to listOf(
                    "Szia! Te is egy Viharszány lovag vagy?",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "Én csak egy gyönyörű mágikus unikornis vagyok, nem láttál még unikornist?",
                    listOf("player3", "player4")
                ),
                "3" to listOf(
                    "Miféle maskara? Nincs semmi maskara! Nem láttál még unikornist?",
                    listOf("player3", "player4")
                ),
                "4" to listOf(
                    "Ja, ja. Majd mindjárt. Te is csak egy újabb agymosott lovag vagy. Mivel etettek téged? Kapsz egy nyalókát?",
                    listOf("player5", "player6")
                ),
                "5" to listOf(
                    "Gonoszt akarsz kiűzni te bolond? Nem értem miért engem tartanak őrültnek. A rossz oldalon állsz barátom.",
                    listOf("player7", "player8")
                ),
                "6" to listOf(
                    "Mutasd a pengéd és rád tüsszentek egy kis tüzet vagy inkább végre kinyitod a szemedet és használod az üres fejedet.",
                    listOf("player9", "player10")
                ),
                "7" to listOf(
                    "Te agytröszt, Viharszárny testesíti meg a gonoszt. Egyeduralomra akar törni minden áron. A háborút ő robbantotta ki, hogy megszerezze a trónt.",
                    listOf("player11", "player12")
                ),
                "8" to listOf(
                    "Anyukád nem tanította meg, hogy nem szólunk közbe? Tehát, hogy megértsd rövidre fogom: trónt akar, ellenségek a sárkány urak, megkaparintja az összes sárkánygömböt eléri a célját.",
                    listOf("player13", "player14")
                ),
                "9" to listOf(
                    "Téged sem mondanálak sárkánynak mégis itt vagy. Az erdő szellemeit és a sárkány urakat legyőzték, ők voltak az utolsó akadályok Viharszárny előtt.",
                    listOf("player15", "player16")
                ),
                "10" to listOf(
                    "Erre nincs idő, amíg itt trécselünk, egyre közelebb ér, hogy felhasználja a sárkánygömbök erejét. Járj utána magad! Kérdezd meg tőle!",
                    quests[0]
                ),
                "player1" to listOf(
                    "Igen, az ő szolgálatában vagyok. Visszaállítjuk a békét a völgyben! Te ki vagy?",
                    "2"
                ),
                "player2" to listOf(
                    "Igen, mi ez a maskara rajtad?",
                    "3"
                ),
                "player3" to listOf(
                    "Ilyen csúnya lovat ritkán látni. Te vagy az unikornis akit Viharszárny keres. Elé viszlek, bármi is a bűnöd, meg kell érte bűnhődnöd!",
                    "4"
                ),
                "player4" to listOf(
                    "Te vagy az unikornis! Kezeket fel, joga van hallgatni, ügyvéd nincs!",
                    "4"
                ),
                "player5" to listOf(
                    "A békéért harcolok, ki űzzük a gonoszt a völgyből. Ha te a rossz oldalon állsz akkor ellenem vagy!",
                    "5"
                ),
                "player6" to listOf(
                    "Szeretem a nyalókát, neked van? Csokit is elfogadok, de először a gonoszt űzzük ki a völgyből!",
                    "5"
                ),
                "player7" to listOf(
                    "Hagyd abba a sértegetést és gyere Viharszárny elé, máskülönben megkóstolod a pengém!",
                    "6"
                ),
                "player8" to listOf(
                    "Rossz oldalon? Miről beszélsz?",
                    "7"
                ),
                "player9" to listOf(
                    "Rendben, miről van szó?",
                    "7"
                ),
                "player10" to listOf(
                    "Na mondjad amit akarsz. Legyél meggyőző, mert ha nem...",
                    "7"
                ),
                "player11" to listOf(
                    "Ez most komoly?",
                    "8"
                ),
                "player12" to listOf(
                    "Kamu!",
                    "8"
                ),
                "player13" to listOf(
                    "Mi közöd van neked ehhez az egésznek? Még csak nem is vagy sárkány.",
                    "9"
                ),
                "player14" to listOf(
                    "Ezek erős vádak, mivel támasztod ezt alá?",
                    "10"
                ),
                "player15" to listOf(
                    "Na jó, ez egy nagy mese.",
                    "10"
                ),
                "player16" to listOf(
                    "Utolsó akadályok voltak?",
                    "10"
                ),
            )
        )
}
