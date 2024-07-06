package hu.dj.aradventure.armodel

import hu.dj.aradventure.item.Item

open class Enemy: ArModel() {

    open var damagePoint: Int = 0
    open var reward: Item? = null
    open var loopDeathAnimation: Boolean = true
    open var invincibleUntilChapter: Double = 0.0

    fun damage(damagePoint: Int) {
        this.health -= damagePoint
    }
}
