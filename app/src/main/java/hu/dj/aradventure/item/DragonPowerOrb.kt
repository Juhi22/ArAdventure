package hu.dj.aradventure.item

import hu.dj.aradventure.R

object DragonPowerOrb: Item() {
    override var name = "Sárkány erőgömb"
    override var description = "A gömb egy sárkány erejét hordozza magában"
    override var imageId = R.drawable.dragon_power_orb
    override var type = ItemType.COLLECTABLE
    override var stackable = true
}