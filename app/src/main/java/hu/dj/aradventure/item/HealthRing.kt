package hu.dj.aradventure.item

import hu.dj.aradventure.R

object HealthRing: Item() {
    override var name = "Életerő gyűrű"
    override var description = "Megnöveli a maximális életerődet"
    override var imageId = R.drawable.health_ring
    override var type = ItemType.MAX_HEALTH
    override var value = 5
}