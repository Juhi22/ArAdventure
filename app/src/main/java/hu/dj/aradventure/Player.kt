package hu.dj.aradventure

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.dj.aradventure.item.HealthAmulet
import hu.dj.aradventure.item.Item
import hu.dj.aradventure.item.ItemType

class Player : ViewModel() {
    var health = MutableLiveData(3)
    var maxHealth: Int = 3
    val minimumHealth: Int = 0
    var damagePoint: Int = 1
    var inventory: MutableList<Item> = mutableListOf()
    var isDead = MutableLiveData(false)

    fun damage(damagePoint: Int) {
        val futureHealth = this.health.value?.minus(damagePoint)
        if (futureHealth != null) {
            if (futureHealth < minimumHealth) {
                this.health.value = minimumHealth
            } else {
                this.health.value = futureHealth
            }
            if(this.health.value!! <= 0) {
                isDead.value = true
            }
        }
    }

    fun heal(healPoint: Int) {
        val futureHealth = this.health.value?.plus(healPoint)
        if (futureHealth != null) {
            if (futureHealth <= maxHealth) {
                this.health.value = futureHealth
            }
        }
    }

    fun pickUpItem(item: Item) {
        if (item.type == ItemType.DEATH) {
            health.value = 1
            isDead.value = false
        } else if (!inventory.contains(item)) {
            inventory.add(item)
            if (item.type == ItemType.MAX_HEALTH) {
                maxHealth += item.value
                health.value = maxHealth
            } else if (item.type == ItemType.ATTACK_POWER) {
                damagePoint += item.value
            }
        }
    }
}