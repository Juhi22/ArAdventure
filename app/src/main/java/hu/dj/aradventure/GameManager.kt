import android.content.Context
import android.content.SharedPreferences
import hu.dj.aradventure.GameState

class GameDataManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("game_data", Context.MODE_PRIVATE)

    val chapterKey = "chapter"
    val goldFishDefeatKey = "goldFishDefeated"

    fun saveGameState(chapter: Double) {
        val editor = prefs.edit()
        editor.putFloat(chapterKey, chapter.toFloat())
        editor.apply()
    }

    fun loadGameState(): GameState {
        val gameState = GameState()
        gameState.chapter = prefs.getFloat(chapterKey, 0.0.toFloat()).toDouble()
        gameState.isGoldFishDefeated = prefs.getBoolean(goldFishDefeatKey, false)
        return gameState
    }

    fun resetGameState() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }

    fun saveGoldFishDefeat() {
        val editor = prefs.edit()
        editor.putBoolean(goldFishDefeatKey, true)
        editor.apply()
    }
}