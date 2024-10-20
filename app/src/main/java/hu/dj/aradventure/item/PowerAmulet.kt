package hu.dj.aradventure.item

import hu.dj.aradventure.R

object PowerAmulet: Item() {
    override var name = "Erő amulett"
    override var description = "Megnöveli a sebzésedet"
    override var imageId = R.drawable.power_amulet
    override var type = ItemType.ATTACK_POWER
    override var value = 2
    override var stackable = true
}