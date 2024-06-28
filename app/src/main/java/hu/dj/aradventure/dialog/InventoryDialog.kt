package hu.dj.aradventure.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.res.Resources
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

class InventoryDialog(private var context: Context, private var resources: Resources) {

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

        val countedItems = mutableMapOf<String, Int>()
        inventory.stream().forEach {
            val item = countedItems[it.name]
            if (item == null) {
                countedItems[it.name] = 1
            } else {
                val count = countedItems[it.name]!!
                countedItems[it.name] = count + 1
            }
        }

        for (itemNameWithCount in countedItems) {
            val item = inventory.first { it.name == itemNameWithCount.key }

            val frameLayout = FrameLayout(context).apply {
                layoutParams = ViewGroup.LayoutParams(270, 270)
            }

            val imageView = ImageView(context).apply {
                setImageResource(item.imageId)
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                ).apply {
                    setMargins(4, 4, 4, 4) // margin a cellák között
                }
                scaleType = ImageView.ScaleType.CENTER_CROP
                adjustViewBounds = true

                setOnClickListener {
                    showTooltip(it as ImageView, item)
                }
            }

            val textView = TextView(context).apply {
                text = itemNameWithCount.value.toString()
                setTextColor(Color.WHITE)
                setBackgroundColor(Color.BLACK)
                textSize = 20f
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = android.view.Gravity.BOTTOM or android.view.Gravity.END
                    setMargins(0, 0, 8, 8)
                }
            }

            frameLayout.addView(imageView)
            frameLayout.addView(textView)
            gridLayout.addView(frameLayout)
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