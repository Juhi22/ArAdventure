package hu.dj.aradventure.armodel

import hu.dj.aradventure.item.Item

open class Collectable: ArModel() {

    open var item: Item = Item()
    open var loopIdleAnimation: Boolean = true

}
