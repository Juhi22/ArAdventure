package hu.dj.aradventure.item

import hu.dj.aradventure.R

object RedKey: Item() {
    override var name = "Vörös kulcs"
    override var description = "Ez egy kulcs. Biztosan nyit valamit."
    override var imageId = R.drawable.red_key
    override var type = ItemType.COLLECTABLE
}