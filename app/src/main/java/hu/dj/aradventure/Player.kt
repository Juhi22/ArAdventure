package hu.dj.aradventure

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.dj.aradventure.item.HealthAmulet
import hu.dj.aradventure.item.Item

class Player : ViewModel() {
    var health = MutableLiveData(3)
    var maxHealth: Int = 3
    val minimumHealth: Int = 0
    var damagePoint: Int = 1
    var inventory: MutableList<Item> = mutableListOf()

    fun damage(damagePoint: Int) {
        val futureHealth = this.health.value?.minus(damagePoint)
        if (futureHealth != null) {
            if (futureHealth < minimumHealth) {
                this.health.value = minimumHealth
            } else {
                this.health.value = futureHealth
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
        if (!inventory.contains(item)) {
            inventory.add(item)
            if (item is HealthAmulet) {
                maxHealth += 5
                health.value = maxHealth
            }
        }
    }
}