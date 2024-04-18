package hu.dj.aradventure.armodel

import hu.dj.aradventure.item.QuestList

object MedievalKnight : ArModel() {
    override val gltfPath: String
        get() = "medieval_knight/scene.gltf"

    override val fiducialMarkerPath: String
        get() = "medieval_knight/fiducial_marker.jpg"

    override val animations: MutableMap<String, String>
        get() {
            return mutableMapOf(
                "idle" to "mixamo.com"
            )
        }

    override val sounds: Map<String, String>
        get() {
            return mutableMapOf(
                "default" to "medieval_knight/sounds/1.mp3",
                "0.0/1" to "medieval_knight/sounds/1.mp3",
                "0.0/2" to "medieval_knight/sounds/1.mp3",
                "0.0/3" to "medieval_knight/sounds/1.mp3",
                "0.0/4" to "medieval_knight/sounds/1.mp3",
                "0.0/5" to "medieval_knight/sounds/1.mp3",
            )
        }

    override val script: Map<String, Any>
        get() {
            return hashMapOf(
                "0.0" to hashMapOf(
                    "1" to listOf(
                        "Üdvözöllek Sárkányvölgyben! Köszönjük, hogy ilyen gyorsan jöttél és segítesz nekünk a láda kinyitásában és a gonosz legyőzésében!",
                        listOf("player1", "player2")
                    ),
                    "2" to listOf(
                        "Mi, hősök, a jó és igaz sárkányt, Viharszárnyat támogatjuk. Mielőtt találkozhatnál vele, át kell esned egy próbán.",
                        listOf("player3", "player4")
                    ),
                    "3" to listOf(
                        "Elég flúgos figura vagy... Mielőtt találkozhatnál az általunk támogatott Viharszárnnyal, át kell esned egy próbán!",
                        listOf("player3", "player4")
                    ),
                    "4" to listOf(
                        "Le kell győznöd öt pokolfajzatot. Ha ez sikerül, méltó vagy Viharszárny seregébe lépni!",
                        QuestList.list[1]
                    ),
                    "5" to listOf(
                        "Önbizalmad az van, aztán nehogy véletlenül odavessz! Győzz le öt pokolfajzatot, meglátjuk Viharszárny méltó katonája lehetsz e.",
                        QuestList.list[1]
                    ),
                    "player1" to listOf(
                        "Jöttem, ahogy csak tudtam, elvégre segítenünk kell egymást!",
                        "2"
                    ),
                    "player2" to listOf(
                        "Nem tudom miről beszélsz, én csak túrázgatok.",
                        "3"
                    ),
                    "player3" to listOf(
                        "Miféle próbán?",
                        "4"
                    ),
                    "player4" to listOf(
                        "Kemény vagyok mint Tarzan sarka, jöhet bármi.",
                        "5"
                    ),
                )
            )
        }
}
