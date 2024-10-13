package hu.dj.aradventure.item

import hu.dj.aradventure.R

object Poison: Item() {
    override var name = "Méreg"
    override var description = "Remek alapanyag a gonosz tervek megvalósításához."
    override var imageId = R.drawable.poison
    override var type = ItemType.COLLECTABLE
    override var stackable = true
}