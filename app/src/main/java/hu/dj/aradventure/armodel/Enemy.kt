package hu.dj.aradventure.armodel

import hu.dj.aradventure.item.Item

open class Enemy: ArModel() {

    open var damagePoint: Int = 0
    open var reward: Item? = null
    open var loopDeathAnimation: Boolean = true
    open var invincibleUntilChapter: Float = 0.0F

    fun damage(damagePoint: Int) {
        this.health -= damagePoint
    }
}
