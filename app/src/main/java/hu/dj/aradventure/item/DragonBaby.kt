package hu.dj.aradventure.item

import hu.dj.aradventure.R

object DragonBaby: Item() {
    override var name = "Sárkány baba"
    override var description = "Kicsi sárkány, remélhetőleg még nem okád tüzet"
    override var imageId = R.drawable.dragon_baby
    override var type = ItemType.COLLECTABLE
}