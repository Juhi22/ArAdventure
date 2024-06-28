package hu.dj.aradventure

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.dj.aradventure.armodel.Enemy
import hu.dj.aradventure.item.*

class Player : ViewModel() {
    var health = MutableLiveData(3)
    var maxHealth: Int = 3
    val minimumHealth: Int = 0
    var damagePoint: Int = 1
    var inventory: MutableList<Item> = mutableListOf()
    var isDead = MutableLiveData(false)
    var quests: MutableList<Quest> = mutableListOf()

    fun damage(enemy: Enemy) {
        val futureHealth = this.health.value?.minus(enemy.damagePoint)
        if (futureHealth != null) {
            if (futureHealth < minimumHealth) {
                this.health.value = minimumHealth
            } else {
                this.health.value = futureHealth
            }
            if(this.health.value!! <= 0) {
                enemy.health = enemy.maxHealth
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
        } else if (item is Quest && !quests.contains(item)) {
            quests.add(item)
        } else if (!PlayerUtil.isItemInInventory(inventory, item) || item.stackable) {
            inventory.add(item)
            if (item.type == ItemType.MAX_HEALTH) {
                maxHealth += item.value
                health.value = maxHealth
            } else if (item.type == ItemType.ATTACK_POWER) {
                damagePoint += item.value
            }
        }
    }

    fun finishQuest(quest: Quest) {
        quests.remove(quest)
    }

    fun updateQuests(quests: List<Quest>) {
        this.quests = quests.toMutableList()
    }
}