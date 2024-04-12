package hu.dj.aradventure

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Player : ViewModel() {
    //var health: Int = 3;
    var health = MutableLiveData(3);
    var maxHealth: Int = 3;
    val minimumHealth: Int = 0;
    var damagePoint: Int = 1;

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
}