package hu.dj.aradventure.item

import hu.dj.aradventure.R

object Sword: Item() {
    override var name = "Egyszerű kard"
    override var description = "Megnöveli a sebzésedet"
    override var imageId = R.drawable.sword
    override var type = ItemType.ATTACK_POWER
    override var value = 1
}