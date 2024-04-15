package hu.dj.aradventure.item

open class Item {
    open var name: String = ""
    open var description: String = ""
    open var imageId: Int = 0
    open var type: ItemType = ItemType.MAX_HEALTH
    open var value: Int = 0
}