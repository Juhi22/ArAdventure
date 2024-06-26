package hu.dj.aradventure

import hu.dj.aradventure.item.Item

object PlayerUtil {

    fun isItemInInventory(inventory: List<Item>, item: Item): Boolean {
        val foundItem = inventory.firstOrNull { itemInInventory -> itemInInventory.name == item.name }
        return foundItem != null
    }
}