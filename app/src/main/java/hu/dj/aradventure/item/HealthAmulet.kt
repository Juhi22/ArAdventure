package hu.dj.aradventure.item

import hu.dj.aradventure.R

object HealthAmulet: Item() {
    override var name = "Életerő amulett"
    override var description = "Megnöveli a maximális életerődet"
    override var imageId = R.drawable.health_amulet
    override var type = ItemType.MAX_HEALTH
    override var value = 5
    override var stackable = true
}