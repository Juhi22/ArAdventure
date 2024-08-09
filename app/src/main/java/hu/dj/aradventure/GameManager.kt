import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import hu.dj.aradventure.GameState
import hu.dj.aradventure.Player
import hu.dj.aradventure.item.Item
import hu.dj.aradventure.item.ItemType
import hu.dj.aradventure.item.QuestList
import java.math.RoundingMode

class GameDataManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("game_data", Context.MODE_PRIVATE)

    fun savePlayer(player: Player) {
        val editor = prefs.edit()
        editor.putInt(Key.PLAYER_HEALTH.name, player.health.value!!)
        editor.putInt(Key.PLAYER_MAX_HEALTH.name, player.maxHealth)
        editor.putInt(Key.PLAYER_DAMAGE_POINT.name, player.damagePoint.value!!)
        editor.putBoolean(Key.PLAYER_IS_DEAD.name, player.isDead.value!!)

        // remove inventory and quest item, not to have stucked in items
        // it can happen that there are no more quests for the player
        // then the already saved one won't be removed/overwritten
        for (i in 0..100) {
            editor.remove(Key.PLAYER_ITEM_NAME.name + i)
            editor.remove(Key.PLAYER_ITEM_DESCRIPTION.name + i)
            editor.remove(Key.PLAYER_ITEM_TYPE.name + i)
            editor.remove(Key.PLAYER_ITEM_VALUE.name + i)
            editor.remove(Key.PLAYER_ITEM_IMAGE_ID.name + i)
        }
        for (i in 0..100) {
            editor.remove(Key.QUEST_INDEX.name + i)
            editor.remove(Key.QUEST_PROGRESS.name + i)
            editor.remove(Key.QUEST_IS_FINISHED.name + i)
        }

        player.inventory.forEachIndexed { index, item ->
            editor.putString(Key.PLAYER_ITEM_NAME.name + index, item.name)
            editor.putString(Key.PLAYER_ITEM_DESCRIPTION.name + index, item.description)
            editor.putString(Key.PLAYER_ITEM_TYPE.name + index, item.type.name)
            editor.putInt(Key.PLAYER_ITEM_VALUE.name + index, item.value)
            editor.putInt(Key.PLAYER_ITEM_IMAGE_ID.name + index, item.imageId)
        }
        player.quests.forEachIndexed { index, quest ->
            val entry = QuestList.list.entries.first { entry -> entry.value.name == quest.name }
            editor.putInt(Key.QUEST_INDEX.name + index, entry.key)
            editor.putInt(Key.QUEST_PROGRESS.name + index, quest.progress)
            editor.putBoolean(Key.QUEST_IS_FINISHED.name + index, quest.isFinished)
        }
        editor.apply()
    }

    fun saveGameState(gameState: GameState) {
        val editor = prefs.edit()
        editor.putFloat(Key.CHAPTER.name, gameState.chapter.toFloat())

        gameState.completedQuestIndexes.forEachIndexed { index, questIndex ->
            editor.putInt(Key.COMPLETED_QUEST_INDEX.name + index, questIndex)
        }

        editor.apply()
    }

    fun loadPlayer(): Player {
        val player = Player()
        player.health = MutableLiveData(prefs.getInt(Key.PLAYER_HEALTH.name, 3))
        player.maxHealth = prefs.getInt(Key.PLAYER_MAX_HEALTH.name, 3)
        player.damagePoint = MutableLiveData(prefs.getInt(Key.PLAYER_DAMAGE_POINT.name, 1))
        player.isDead = MutableLiveData(prefs.getBoolean(Key.PLAYER_IS_DEAD.name, false))
        for (i in 0..100) {
            val item = Item()
            item.name = prefs.getString(Key.PLAYER_ITEM_NAME.name + i, "")!!
            if (item.name == "") {
                break
            }
            item.description = prefs.getString(Key.PLAYER_ITEM_DESCRIPTION.name + i, "")!!
            item.type = ItemType.valueOf(prefs.getString(Key.PLAYER_ITEM_TYPE.name + i, null)!!)
            item.value = prefs.getInt(Key.PLAYER_ITEM_VALUE.name + i, 0)
            item.imageId = prefs.getInt(Key.PLAYER_ITEM_IMAGE_ID.name + i, 0)
            player.inventory.add(item)
        }
        for (i in 0..100) {
            val questIndex = prefs.getInt(Key.QUEST_INDEX.name + i, 0)
            if (questIndex == 0) {
                break
            }
            val quest = QuestList.list[questIndex]!!
            quest.progress = prefs.getInt(Key.QUEST_PROGRESS.name + i, 0)
            quest.isFinished = prefs.getBoolean(Key.QUEST_IS_FINISHED.name + i, false)
            player.quests.add(quest)
        }
        return player
    }

    fun loadGameState(): GameState {
        val gameState = GameState()
        gameState.chapter =
            prefs.getFloat(Key.CHAPTER.name, 0.0.toFloat()).toBigDecimal().setScale(1, RoundingMode.UP).toDouble()

        for (i in 0..100) {
            val questIndex = prefs.getInt(Key.COMPLETED_QUEST_INDEX.name + i, 0)
            if (questIndex == 0) {
                break
            }
            gameState.completedQuestIndexes.add(questIndex)
        }

        gameState.isGoldFishDefeated = prefs.getBoolean(Key.GOLD_FISH_DEFEATED.name, false)
        gameState.isDragonLordBlackIsDefeated = prefs.getBoolean(Key.DRAGON_LORD_BLACK_DEFEATED.name, false)
        gameState.isDragonLordSnowPrinceIsDefeated = prefs.getBoolean(Key.DRAGON_LORD_SNOW_PRINCE_DEFEATED.name, false)
        gameState.isDragonLordHornIsDefeated = prefs.getBoolean(Key.DRAGON_LORD_HORN_DEFEATED.name, false)
        gameState.isStormWingBossDefeated = prefs.getBoolean(Key.STORM_WING_BOSS_DEFEATED.name, false)
        return gameState
    }

    fun resetGameState() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }

    fun saveBooleanValue(key: String, value: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBooleanValue(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }

    enum class Key {
        INTRO_WATCHED,
        CHAPTER,
        PLAYER_HEALTH,
        PLAYER_MAX_HEALTH,
        PLAYER_DAMAGE_POINT,
        PLAYER_IS_DEAD,
        PLAYER_ITEM_NAME,
        PLAYER_ITEM_DESCRIPTION,
        PLAYER_ITEM_TYPE,
        PLAYER_ITEM_VALUE,
        PLAYER_ITEM_IMAGE_ID,
        QUEST_INDEX,
        QUEST_PROGRESS,
        QUEST_IS_FINISHED,
        COMPLETED_QUEST_INDEX,
        GOLD_FISH_DEFEATED,
        DRAGON_LORD_BLACK_DEFEATED,
        DRAGON_LORD_SNOW_PRINCE_DEFEATED,
        DRAGON_LORD_HORN_DEFEATED,
        STORM_WING_BOSS_DEFEATED,
    }
}