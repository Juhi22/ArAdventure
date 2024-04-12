package hu.dj.aradventure.armodel

open class Enemy: ArModel() {

    open var damagePoint: Int = 0;

    fun damage(damagePoint: Int) {
        this.health -= damagePoint
    }
}
