package hu.dj.aradventure.item

import hu.dj.aradventure.R

object Bread: Item() {
    override var name = "Kenyér"
    override var description = "Friss pékáru, még jó lehet később"
    override var imageId = R.drawable.bread
    override var type = ItemType.COLLECTABLE
}