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
        QuestList.list[6]!!,
        QuestList.list[7]!!,
        QuestList.list[8]!!,
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
                "default" to "unicorn/sounds/unicorn_default.mp3",
                "1.1/1" to "unicorn/sounds/unicorn_1_1_1.mp3",
                "1.1/2" to "unicorn/sounds/unicorn_1_1_2.mp3",
                "1.1/3" to "unicorn/sounds/unicorn_1_1_3.mp3",
                "1.1/4" to "unicorn/sounds/unicorn_1_1_4.mp3",
                "1.1/5" to "unicorn/sounds/unicorn_1_1_5.mp3",
                "1.1/6" to "unicorn/sounds/unicorn_1_1_6.mp3",
                "1.1/7" to "unicorn/sounds/unicorn_1_1_7.mp3",
                "1.1/8" to "unicorn/sounds/unicorn_1_1_8.mp3",
                "1.1/9" to "unicorn/sounds/unicorn_1_1_9.mp3",
                "1.1/10" to "unicorn/sounds/unicorn_1_1_10.mp3",
                "2.0/1" to "unicorn/sounds/unicorn_2_0_1.mp3",
                "2.0/2" to "unicorn/sounds/unicorn_2_0_2.mp3",
                "2.0/3" to "unicorn/sounds/unicorn_2_0_3.mp3",
                "2.0/4" to "unicorn/sounds/unicorn_2_0_4.mp3",
                "2.0/5" to "unicorn/sounds/unicorn_2_0_5.mp3",
                "2.0/6" to "unicorn/sounds/unicorn_2_0_6.mp3",
                "2.0/7" to "unicorn/sounds/unicorn_2_0_7.mp3",
                "2.0/8" to "unicorn/sounds/unicorn_2_0_8.mp3",
                quests[1].name + "/1" to "unicorn/sounds/unicorn_quest_1_1.mp3",
                quests[2].name + "/1" to "unicorn/sounds/unicorn_quest_2_1.mp3",
                quests[2].name + "/2" to "unicorn/sounds/unicorn_quest_2_2.mp3",
                quests[3].name + "/1" to "unicorn/sounds/unicorn_quest_3_1.mp3",
                quests[3].name + "/2" to "unicorn/sounds/unicorn_quest_3_2.mp3",
                quests[3].name + "/3" to "unicorn/sounds/unicorn_quest_3_3.mp3",
                quests[3].name + "/4" to "unicorn/sounds/unicorn_quest_3_4.mp3",
                quests[3].name + "/5" to "unicorn/sounds/unicorn_quest_3_5.mp3",
                quests[3].name + "/6" to "unicorn/sounds/unicorn_quest_3_6.mp3",
                quests[3].name + "/7" to "unicorn/sounds/unicorn_quest_3_7.mp3",
                quests[3].name + "/8" to "unicorn/sounds/unicorn_quest_3_8.mp3",
                quests[3].name + "/9" to "unicorn/sounds/unicorn_quest_3_9.mp3",
            )
        }

    override var script: MutableMap<String, Any> =
        hashMapOf(
            "default" to hashMapOf(
                "1" to listOf(
                    "Egy gyönyörű unikornis vagyok, szivárvány!",
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
            ),
            "2.0" to hashMapOf(
                "1" to listOf(
                    "Na mi van tökfilkó? Azért jöttél, hogy Viharszárny elé vigyél vagy mert kirúgott a főnök?",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "Persze, hogy nem önmaga. A gömbök ereje hatalmat ad, de közben meg is rontja az elmét. Segíts nekem legyőzni őt és az átalakult lovagjait.",
                    listOf("player3", "player4")
                ),
                "3" to listOf(
                    "Hát ez szomorú, lehet új karrier után kell nézned? Vagy csatlakozhatsz is hozzám. Amúgy csak nézd meg a lovagtársaidat, ha nekem nem hiszel, már nem ugyan azok mint voltak.",
                    listOf("player3", "player4")
                ),
                "4" to listOf(
                    "A terv, hogy megszerezzük a trónt és békét hozunk végre. Kell egy erős sereg ami fel tudja venni a harcot Viharszárnyéval. Jó pár lovagot már sikerült magam mellé állítani.",
                    listOf("player7", "player8")
                ),
                "5" to listOf(
                    "Egykor én is sárkányúr voltam, én vezettem őket. Bizalmat fektettem Viharszárnyba, a sárkány erő gömbömet is oda adtam neki és ez lett a vége. A gömböktől egy kicsit begolyóztam, nem tagadom, ezért száműztek. De most, hogy nagy a baj felkérném egy táncra.",
                    listOf("player5", "player6")
                ),
                "6" to listOf(
                    "Trollok portyáztak akkoriban és az tűnt a legjobb megoldásnak, ha ilyen módon a segítségét kérem. Utána jött a száműzetés, ő meg háborút robbantott ki.",
                    listOf("player9", "player10")
                ),
                "7" to listOf(
                    "Részletezem, csak 3 lépés. 1. Foglyokat tart fogva mágiával, mind erős sárkányok, fel kell őket szabadítanunk és erősíteni fognak minket. 2. Meg kell ütköznünk a lovagjaival, rést kell ütnünk a védelmen. 3. A célpont: Viharszárny.",
                    listOf("player11", "player12")
                ),
                "8" to listOf(
                    "Nincs idő szundikálni, már így is maximumon van az ereje a gömbök miatt. Menjünk és szabadítsuk fel a fogolytábort!",
                    quests[1]
                ),
                "player1" to listOf(
                    "Viharszárny, mintha nem lett volna önmaga. Lehet elviszlek neki és akkor jobb belátásra tér.",
                    "2"
                ),
                "player2" to listOf(
                    "Kirúgott. Még mindig nem tudom, kinek higyjek.",
                    "3"
                ),
                "player3" to listOf(
                    "Mi lett a lovagokkal? Honnan tudsz ennyi mindent?",
                    "5"
                ),
                "player4" to listOf(
                    "Hiszek neked, bár nem tudom miért, hogyan győzzük le őt?",
                    "4"
                ),
                "player5" to listOf(
                    "Segítek neked ha elmondod, miért adtad oda neki a gömböt. Mi lenne a terv?",
                    "6"
                ),
                "player6" to listOf(
                    "Csapjuk szét a brigádját mielőtt rosszabbra fordulna a helyzet. Mi a terv?",
                    "4"
                ),
                "player7" to listOf(
                    "Ez még elég gyengének hangzik.",
                    "7"
                ),
                "player8" to listOf(
                    "Ennyi lenne a nagy terv? Részletesebben?",
                    "7"
                ),
                "player9" to listOf(
                    "Értem, elhiszem. Mi a terv?",
                    "4"
                ),
                "player10" to listOf(
                    "Még mindig bizalmatlan vagyok. Nem tudok mást tenni, segítek. Mi a terv?",
                    "4"
                ),
                "player11" to listOf(
                    "Mikor kezdjük?",
                    "8"
                ),
                "player12" to listOf(
                    "Beverem a szunyát előtte.",
                    "8"
                ),
            ),
            quests[1].name to hashMapOf(
                "1" to listOf(
                    "Első lépés pipa. A kémek jelentése szerint, mostmár bizonyára elég erősek vagyunk, hogy nyíltan megütközzünk az ellenséggel. Irány lovagokat simogatni!",
                    quests[2]
                ),
            ),
            quests[2].name to hashMapOf(
                "1" to listOf(
                    "Második lépés pipa. Szívósabbak voltak mint reméltem, de győztünk. Egész jó csapatot alkotunk Tökfilkó",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "Bla bla bla... Utolsó lépés jön, főellenség a láthatáron! Vigyázz, kész, rajt!",
                    quests[3]
                ),
                "player1" to listOf(
                    "Nem vagyok Tökfilkó! Ne nevezz így, mert különben hazamegyek!",
                    "2"
                ),
                "player2" to listOf(
                    "Gyengék voltak, én vagyok itt a völgyben a muszkli manus.",
                    "2"
                ),
            ),
            quests[3].name to hashMapOf(
                "1" to listOf(
                    "Sikerült! A háborúnak, a csatáknak vége. A völgy újra szabad! Tökfilkó, köszönöm a segítségedet minden sárkány nevében. Újra tudunk mindent kezdeni.",
                    listOf("player1", "player2")
                ),
                "2" to listOf(
                    "Mit akarsz ezzel mondani?",
                    listOf("player3", "player4")
                ),
                "3" to listOf(
                    "Micsoda? Elgurult a gyógyszered? Szerinted ezt hagyni fogják az itt élők, hogy egy újabb zsarnok lépjen a trónra?",
                    listOf("player5", "player6")
                ),
                "4" to listOf(
                    "Állok szolgálatára Tökfi.. Akarom mondani Uram.",
                    listOf("player7", "player8")
                ),
                "5" to listOf(
                    "Van egy jó hírem Uram, a napokban volt szerencsém beszélni vele. Egy ajándékot elrejtett a Sárkányfog erdőben található karácsonyfa alá.",
                    null
                ),
                "6" to listOf(
                    "Igen, a feladatra alaklmas sárkányokat keresek és úrnak nevezem ki őket. A hatalmat megosztjuk és a völgy újra virágozni fog.",
                    listOf("player9", "player10")
                ),
                "7" to listOf(
                    "Megteszek minden tőlem telhetőt, köszönünk neked mindent Tökfilkó!",
                    listOf("player11", "player12")
                ),
                "8" to listOf(
                    "Örök hálánk neked. Lenne még egy apróság. Mielőtt mennél látogass el a Sárkányfog erdőbe." +
                            "A napokban beszéltem a Jézuskával és hagyott az ott található karácsonyfa alatt egy csomagot.",
                    null
                ),
                "9" to listOf(
                    "Örök hálánk neked. Meg leszünk, már bőven kivetted a részed. Inkább látogass el a Sárkányfog erdőbe. A napokban beszéltem a Jézuskával és hagyott az ott található karácsonyfa alatt egy csomagot.",
                    null
                ),
                "player1" to listOf(
                    "Újra bizony, viszont azt ne felejtsd el, hogy erősebb vagyok már minden sárkánynál.",
                    "2"
                ),
                "player2" to listOf(
                    "Újra bizony, új sárkányurak lesznek kinevezve és meg lesz osztva a hatalom?",
                    "6"
                ),
                "player3" to listOf(
                    "Csak annyit, hogy ki vagyok gyúrva és remélem kapok ingyen bérletet a konditerembe. Új sárkányurak lesznek kinevezve és meg lesz osztva a hatalom?",
                    "6"
                ),
                "player4" to listOf(
                    "Nincs több sárkányúr aki ellenállna nekem. Vasmarokkal megragadom a hatalmat és a völgyet mostantól én vezetem.",
                    "3"
                ),
                "player5" to listOf(
                    "Az ő szavuk az enyém ellen semmit nem ér, ha bárki ellenszegül akkor megbánja.",
                    "4"
                ),
                "player6" to listOf(
                    "Már eldöntöttem, te leszel a jobb kezem és követed a parancsaimat.",
                    "4"
                ),
                "player7" to listOf(
                    "Uralokodásom megerősítése érdekében vedd fel a kapcsolatot a Jézuskával és szerezd meg az ajándék hollétét.",
                    "5"
                ),
                "player8" to listOf(
                    "Fel kell keresnünk a karácsonyi ajándékomat, mielőtt más kaparintja meg.",
                    "5"
                ),
                "player9" to listOf(
                    "Remek terv, biztos vagyok benne, hogy a megfelelő sárkányokat fogod választani a feladatra.",
                    "7"
                ),
                "player10" to listOf(
                    "Én vagyok a legerősebb a völgyben. Ez a terv nem fog teljesülni.",
                    "2"
                ),
                "player11" to listOf(
                    "Szívesen, a feladatomat itt elvégeztem. Új kalandot keresek, még ma elhagyom a völgyet.",
                    "8"
                ),
                "player12" to listOf(
                    "Szívesen, ha további segítségre van szükség szóljatok és jövök.",
                    "9"
                ),
            ),
        )
}
