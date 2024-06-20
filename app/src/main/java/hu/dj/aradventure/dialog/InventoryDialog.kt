package hu.dj.aradventure.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.NonNull
import hu.dj.aradventure.R
import hu.dj.aradventure.item.Item
import hu.dj.aradventure.item.Quest
import java.util.stream.Collectors

class InventoryDialog(private var context: Context) {

    private lateinit var alertDialog: AlertDialog

    private var tooltipPopup: PopupWindow? = null

    fun show(inventory: List<Item>) {
        buildDialog(inventory)
        alertDialog.show()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun buildDialog(inventory: List<Item>) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.inventory, null)
        val gridLayout = dialogView.findViewById<GridLayout>(R.id.gridLayout)

        for (item in inventory) {
            val imageView = ImageView(context).apply {
                setImageResource(item.imageId)
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = GridLayout.LayoutParams.WRAP_CONTENT
                    columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                }
                scaleType = ImageView.ScaleType.CENTER_CROP
                adjustViewBounds = true

                setOnClickListener {
                    showTooltip(it as ImageView, item)
                }
            }
            gridLayout.addView(imageView)
        }

        val dialogBuilder = AlertDialog.Builder(context)
            .setView(dialogView)
            .setCancelable(true)

        alertDialog = dialogBuilder.create()
    }

    private fun showTooltip(imageView: ImageView, item: Item) {
        dismissTooltip()

        val tooltipView = LayoutInflater.from(context).inflate(R.layout.tooltip, null)

        val tooltipTextView = tooltipView.findViewById<TextView>(R.id.tooltipTextView)
        tooltipTextView.text = item.name + "\n" + item.description

        tooltipPopup = PopupWindow(tooltipView, GridLayout.LayoutParams.WRAP_CONTENT, GridLayout.LayoutParams.WRAP_CONTENT, true)
        tooltipPopup?.apply {
            isOutsideTouchable = true
            setBackgroundDrawable(BitmapDrawable())
            showAsDropDown(imageView, 0, -imageView.height)
        }
    }

    private fun dismissTooltip() {
        tooltipPopup?.dismiss()
        tooltipPopup = null
    }
}