package hu.dj.aradventure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class TutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        val items = listOf(
            PageItem(R.drawable.valley_of_the_dragons_icon, "Valley of the Dragons: Kézikönyv"),
            PageItem(R.drawable.tutorial_basic_icons, "A képernyőn ezek az ikonok mindig veled lesznek az úton.\n" +
                    "- Szív: az életerődet jelöli\n" +
                    "- Kard: a sebzésedet jelöli\n" +
                    "- Pergament: a küldetéseidet tartalmazza, kattints rá és olvasd el\n" +
                    "- Táska: az összegyűjtött tárgyakat tartalmazza, kattints rá és nézz bele\n"),
            PageItem(R.drawable.tutorial_additional_icons, "Vannak más ikonok is amik csak bizonyos helyezetben jelennek meg a képernyő tetején.\n" +
                    "- Karakter adatok: a képernyő tetején, középen látható annak a neve és életereje (piros csík) akivel beszélgetsz vagy éppen harcolsz\n" +
                    "- Harc indikátor: a jobb felső sarokban pulzáló kardok jelölik, ha harcolsz valakivel"),
            PageItem(R.drawable.tutorial_item_pick_up, "Megjelenhetnek ikonok és szövegek az alábbi elrendezésben.\n" +
                    "Ezek lehetnek gyűjthető tárgyak vagy azt is mutathatja, hogy legyőztek, ahogy a képen a példa is ezt mutatja.\n" +
                    "Ebben az esetben kattints az ikonra, hogy begyűjts a tárgyat, tovább haladj."),
            PageItem(R.drawable.tutorial_real_life_photos, "Hol is vannak a völgy lényei? Hogyan jelennek meg?\n" +
                    "Mutass a kamerával a képekre és a képen lévő karakter egyből megelevenedik."),
            PageItem(R.drawable.tutorial_conversation, "Találkozhatsz barátságos és ellenséges lényekkel is.\n" +
                    "Beszélgess velük, a képernyő alján megjelenő buborékok segítségével:\n" +
                    "- a felső: amit a karakter mond neked\n" +
                    "- a középső és az alsó: amit te adhatsz válasznak (kattints rá, csak egyet választhatsz!)"),
            PageItem(R.drawable.tutorial_conversation_ending, "A párbeszéd során, abban az esetben ha mínusz jel van a választható válaszok között az azt jelenti, hogy nincs több mondandód és véget ér a beszélgetés.\n" +
                    "Erre is rá kell nyomni, ne felejtsd el!"),
            PageItem(R.drawable.tutorial_fight, "Ha harcra kerül a sor, támadj az ellenségre nyomva. Addig üsd amíg az életereje el nem fogy.\n" +
                    "Ha legyőzted várj egy kicsit amíg eltűnik, ha többen vannak, a kamerával újra a képre célozva megjelenik egy újabb karakter."),
            PageItem(R.drawable.tutorial_quest, "A küldetések teljesítéséhez hajtsd végre a leírt feladatot, majd jelezd annak aki megbízott téged, hogy sikerrel jártál.\n" +
                    "Számláló mutatja, hogy éppen mennyi van még hátra. Ha már leadható a küldetés a piros jel zöldre vált."),
        )

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val pageIndicator: TextView = findViewById(R.id.pageIndicator)

        val adapter = ViewPagerAdapter(items)
        viewPager.adapter = adapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                pageIndicator.text = "${position + 1}/${adapter.itemCount}"
            }
        })
    }

    data class PageItem(
        val imageResId: Int,
        val description: String
    )

    class ViewPagerAdapter(
        private val items: List<PageItem>
    ) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.imageView)
            val textView: TextView = itemView.findViewById(R.id.textView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.page_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.imageView.setImageResource(item.imageResId)
            holder.textView.text = item.description
        }

        override fun getItemCount(): Int = items.size
    }
}