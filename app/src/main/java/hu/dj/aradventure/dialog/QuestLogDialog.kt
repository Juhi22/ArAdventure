package hu.dj.aradventure.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.NonNull
import hu.dj.aradventure.R
import hu.dj.aradventure.item.Quest

class QuestLogDialog(private var context: Context) {

    private lateinit var alertDialog: AlertDialog

    fun show(quests: List<Quest>) {
        if (quests.isEmpty()) {
            buildEmptyQuestLogDialog()
        } else {
            buildDialog(QuestLogDialogAdapter(context, quests))
        }
        alertDialog.show()
    }

    @SuppressLint("SetTextI18n")
    private fun buildEmptyQuestLogDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.empty_stuff, null)
        val textView = dialogView.findViewById<TextView>(R.id.text)
        textView.text = "Jelenleg nincs küldetésed, kalandozz tovább!"
        alertDialog = AlertDialog.Builder(context).setView(dialogView).create()
    }

    private fun buildDialog(adapter: ListAdapter) {
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.quest_log, null)

        val listView = dialogView.findViewById<ListView>(R.id.listView)

        listView.adapter = adapter

        alertDialog = AlertDialog.Builder(context).setView(dialogView).create()
    }

    private class QuestLogDialogAdapter(
        context: Context,
        private val data: List<Quest>
    ) : ArrayAdapter<Quest>(context, R.layout.quest_list_item, data) {

        @NonNull
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.quest_list_item, parent, false)
            val nameView = view.findViewById<TextView>(R.id.questName)
            val descriptionView = view.findViewById<TextView>(R.id.questDescription)
            val progressView = view.findViewById<TextView>(R.id.questProgress)
            val stateView = view.findViewById<ImageView>(R.id.questState)

            val quest = data[position]
            nameView.text = quest.name
            descriptionView.text = quest.description
            progressView.text = quest.progress.toString() + "/" + quest.goal
            if (quest.isFinished) {
                stateView.setImageResource(R.drawable.good_mark)
            } else {
                stateView.setImageResource(R.drawable.cross_mark)
            }

            return view
        }
    }
}