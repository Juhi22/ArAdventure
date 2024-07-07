package hu.dj.aradventure.item

import hu.dj.aradventure.R

object StoryFinishTrophy: Item() {
    override var name = "Trófea: Fő történet befejezve"
    override var description = "Gratulálunk! Jutalmad a fő küldetésszál befejezéséért. Látogass el a Sárkányfog erdőben lévő karácsonyfához a Jézuska ajándékáért"
    override var imageId = R.drawable.trophy
    override var type = ItemType.COLLECTABLE
}