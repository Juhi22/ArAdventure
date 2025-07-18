package hu.dj.aradventure;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel

class GameState: ViewModel() {
    var isFightOngoing = MutableLiveData(false)
    var chapter = 0.0
    var completedQuestIndexes = mutableSetOf<Int>()
    var isGoldFishDefeated = false
    var isDragonLordBlackIsDefeated = false
    var isDragonLordHornIsDefeated = false
    var isDragonLordSnowPrinceIsDefeated = false
    var isStormWingBossDefeated = false
    var isLightDragonDefeated = false
}
