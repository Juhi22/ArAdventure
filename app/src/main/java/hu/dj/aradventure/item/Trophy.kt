package hu.dj.aradventure.item

import hu.dj.aradventure.R

open class Trophy: Item() {
    override var imageId = R.drawable.trophy
    override var type = ItemType.COLLECTABLE
}