package hu.dj.aradventure.item

import hu.dj.aradventure.R

object Death: Item() {
    override var name = "Legyőztek"
    override var description = "Elfogyott az életerőd. Egyél, hogy újratöltsd"
    override var imageId = R.drawable.skull_and_crossbones
    override var type = ItemType.DEATH
}