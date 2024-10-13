package hu.dj.aradventure

import GameDataManager
import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.google.ar.core.AugmentedImage
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.collision.Box
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.RenderableInstance
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import hu.dj.aradventure.armodel.*
import hu.dj.aradventure.armodel.Bread
import hu.dj.aradventure.armodel.DragonBaby
import hu.dj.aradventure.controller.*
import hu.dj.aradventure.dialog.InventoryDialog
import hu.dj.aradventure.dialog.QuestLogDialog
import hu.dj.aradventure.item.*


class MainActivity : AppCompatActivity() {

    private lateinit var arFragment: ArFragment
    private lateinit var augmentedImageMap: MutableMap<AugmentedImage, AnchorNode>

    private lateinit var gameState: GameState
    private lateinit var gameDataManager: GameDataManager
    private lateinit var player: Player

    private var medievalKnight = MedievalKnight
    private var dragonKnight = DragonKnight
    private var unicorn = Unicorn
    private var hellMinion = HellMinion
    private var goldFish = GoldFish
    private var chickenSandwich = ChickenSandwich
    private var stormWing = StormWing
    private var stormWingBoss = StormWingBoss
    private var ent = Ent
    private var dragonSlave = DragonSlave
    private var dragonLordBlack = DragonLordBlack
    private var dragonLordSnowPrince = DragonLordSnowPrince
    private var dragonLordHorn = DragonLordHorn
    private var dragonBaby = DragonBaby
    private var dragonMom = DragonMom
    private var chestOne = ChestOne
    private var chestTwo = ChestTwo
    private var ogre = Ogre
    private var bread = Bread
    private var swampGuard = SwampGuard
    private var snake = Snake

    private var arModels = mutableListOf(
        medievalKnight,
        hellMinion,
        unicorn,
        chickenSandwich,
        goldFish,
        stormWing,
        ent,
        dragonSlave,
        dragonLordBlack,
        dragonLordSnowPrince,
        dragonLordHorn,
        dragonBaby,
        dragonMom,
        chestOne,
        chestTwo,
        ogre,
        bread,
        swampGuard,
        snake,
    )

    private var timedActionController = TimedActionController()
    private lateinit var soundController: SoundController
    private lateinit var scriptController: ScriptController
    private lateinit var vibrationController: VibrationController

    private var isItemBeingShown = false

    @SuppressLint("MissingInflatedId", "ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBackDropContent(View.INVISIBLE, Item())

        val modelInfoView: LinearLayout = findViewById(R.id.modelInfo)
        val modelNameView: TextView = findViewById(R.id.modelName)
        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        val healthPointsView: TextView = findViewById(R.id.healthPoints)
        val heartIconView: ImageView = findViewById(R.id.heartIcon)
        val damagePointsView: TextView = findViewById(R.id.damagePoints)
        val damageIconView: ImageView = findViewById(R.id.damageIcon)
        val questLogImage: ImageView = findViewById(R.id.questLog)
        val inventoryImage: ImageView = findViewById(R.id.inventory)
        modelInfoView.visibility = View.GONE
        healthPointsView.visibility = View.GONE
        heartIconView.visibility = View.GONE
        damageIconView.visibility = View.GONE
        damagePointsView.visibility = View.GONE
        questLogImage.visibility = View.GONE
        inventoryImage.visibility = View.GONE

        soundController = SoundController(assets)

        scriptController = ScriptController(
            findViewById(R.id.mob_text),
            findViewById(R.id.player_text_A),
            findViewById(R.id.player_text_B),
            soundController
        )
        scriptController.init()

        vibrationController = VibrationController(this)

        gameDataManager = GameDataManager(this)
        gameState = gameDataManager.loadGameState()
        player = gameDataManager.loadPlayer()

        gameState.isFightOngoing.observe(this) {
            val fightIndicatorView = findViewById<ImageView>(R.id.fight_indicator)
            if (it) {
                val pulseAnimation = AnimationUtils.loadAnimation(this, R.anim.pulse_animation)
                fightIndicatorView.visibility = View.VISIBLE
                fightIndicatorView.startAnimation(pulseAnimation)
            } else {
                fightIndicatorView.visibility = View.GONE
                fightIndicatorView.clearAnimation()
            }
        }

        healthPointsView.text = player.health.value.toString()
        player.health.observe(this) {
            healthPointsView.text = it.toString()
        }
        damagePointsView.text = player.damagePoint.value.toString()
        player.damagePoint.observe(this) {
            damagePointsView.text = it.toString()
        }
        player.isDead.observe(this) {
            if (it) {
                gameState.isFightOngoing.value = false
                showItem(Death)
                clearAllNodes()
            }
        }

        inventoryImage.setOnClickListener {
            val inventoryDialog = InventoryDialog(this, resources)
            inventoryDialog.show(player.inventory)
        }

        questLogImage.setOnClickListener {
            val questLogDialog = QuestLogDialog(this)
            questLogDialog.show(player.quests)
        }

        QuestController.onEventListener(object : QuestController.QuestListener {
            override fun onQuestStarts(quest: Quest) {
                showItem(quest)
                QuestController.update(player.quests, quest.questType, quest.questItem)
            }

            override fun onQuestEnds(quest: Quest) {
                player.finishQuest(quest)
                if (quest.reward != null) {
                    showItem(quest.reward)
                }
                if (quest.nextChapter != null) {
                    gameState.chapter = quest.nextChapter
                    if (gameState.chapter == 2.0) {
                        transformFriendsToEnemies()
                    }
                    if (gameState.chapter == 2.3) {
                        showItem(StoryFinishTrophy)
                    }
                    gameDataManager.saveGameState(gameState)
                }
                val entry = QuestList.list.entries.first { entry -> entry.value.name == quest.name }
                gameState.completedQuestIndexes.add(entry.key)
                gameDataManager.saveGameState(gameState)
                gameDataManager.savePlayer(player)
            }

            override fun onQuestsUpdated(quests: List<Quest>) {
                gameDataManager.savePlayer(player)
                player.updateQuests(quests)
            }
        })

        augmentedImageMap = mutableMapOf()

        arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment

        arFragment.arSceneView.post {
            if (gameState.chapter >= 2.0) {
                transformFriendsToEnemies()
            } else {
                addArModelsToAgumentedImageDb()
            }

            progressBar.visibility = View.GONE
            healthPointsView.visibility = View.VISIBLE
            heartIconView.visibility = View.VISIBLE
            damagePointsView.visibility = View.VISIBLE
            damageIconView.visibility = View.VISIBLE
            questLogImage.visibility = View.VISIBLE
            inventoryImage.visibility = View.VISIBLE
        }

        arFragment.arSceneView.scene.addOnUpdateListener { frameTime: FrameTime ->
            if (!player.isDead.value!! && !gameState.isFightOngoing.value!! && !scriptController.isScriptOngoing && !isItemBeingShown) {
                val updatedAugmentedImages =
                    arFragment.arSceneView.arFrame?.getUpdatedTrackables(AugmentedImage::class.java)
                updatedAugmentedImages?.forEach { augmentedImage ->
                    when (augmentedImage.trackingState) {
                        TrackingState.TRACKING -> {
                            if (!augmentedImageMap.containsKey(augmentedImage) &&
                                augmentedImage.trackingMethod == AugmentedImage.TrackingMethod.FULL_TRACKING
                            ) {
                                val currentModel = getModelByGtlfPath(augmentedImage.name)
                                if (canDisplayModel(currentModel)) {
                                    clearAllNodes()
                                    val anchorNode = AnchorNode(augmentedImage.createAnchor(augmentedImage.centerPose))
                                    augmentedImageMap[augmentedImage] = anchorNode
                                    displayModel(augmentedImage.name, currentModel, anchorNode)

                                    modelNameView.text = currentModel.name
                                    modelInfoView.visibility = View.VISIBLE
                                    modelNameView.post {
                                        setModelHealthBar(100, modelNameView)
                                    }
                                }
                            }
                        }

                        TrackingState.STOPPED -> {
                            augmentedImageMap.remove(augmentedImage)
                        }

                        TrackingState.PAUSED -> {}
                    }
                }
            }
        }
    }

    private fun addArModelsToAgumentedImageDb() {
        val augmentedImageDatabase = AugmentedImageDatabase(arFragment.arSceneView.session)
        arModels.forEach {
            addAugmentedImageToDB(it, augmentedImageDatabase)
        }
        val config = arFragment.arSceneView.session?.config
        config?.augmentedImageDatabase = augmentedImageDatabase
        arFragment.arSceneView.session?.configure(config)
    }

    private fun transformFriendsToEnemies() {
        arModels.remove(stormWing)
        arModels.add(stormWingBoss)
        arModels.remove(medievalKnight)
        arModels.add(dragonKnight)
        addArModelsToAgumentedImageDb()
    }

    private fun setModelHealthBar(healthPercent: Int, modelNameView: TextView = findViewById(R.id.modelName)) {
        val modelHealthView: ProgressBar = findViewById(R.id.modelHealth)
        modelHealthView.progress = healthPercent
        val layoutParams = modelHealthView.layoutParams
        layoutParams.width = modelNameView.width
        modelHealthView.layoutParams = layoutParams
    }

    private fun canDisplayModel(model: ArModel): Boolean {
        if (model is GoldFish) {
            return !gameState.isGoldFishDefeated
        } else if (model is DragonLordBlack) {
            return !gameState.isDragonLordBlackIsDefeated
        } else if (model is DragonLordSnowPrince) {
            return !gameState.isDragonLordSnowPrinceIsDefeated
        } else if (model is DragonLordHorn) {
            return !gameState.isDragonLordHornIsDefeated
        } else if (model is StormWingBoss) {
            return !gameState.isStormWingBossDefeated
        } else if (model is Collectable) {
            if (model is DragonBaby) {
                return !gameState.completedQuestIndexes.contains(2) && !PlayerUtil.isItemInInventory(
                    player.inventory,
                    model.item
                )
            } else if (model is Bread) {
                return !gameState.completedQuestIndexes.contains(9) && !PlayerUtil.isItemInInventory(
                    player.inventory,
                    model.item
                )
            }
            return !PlayerUtil.isItemInInventory(player.inventory, model.item)
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        soundController.shutdown()
    }

    private fun setBackDropContent(visibility: Int, item: Item) {
        val backdrop = findViewById<View>(R.id.backdrop)
        val healthPoints: TextView = findViewById(R.id.healthPoints)
        val heartIcon: ImageView = findViewById(R.id.heartIcon)
        val damagePoints: TextView = findViewById(R.id.damagePoints)
        val damageIcon: ImageView = findViewById(R.id.damageIcon)
        val questLogIcon: ImageView = findViewById(R.id.questLog)
        val inventoryIcon: ImageView = findViewById(R.id.inventory)
        val centerImage: ImageView = findViewById(R.id.centerImage)
        val centerTitle: TextView = findViewById(R.id.centerTitle)
        val centerDescription: TextView = findViewById(R.id.centerDescription)

        backdrop.visibility = visibility
        centerImage.visibility = visibility
        centerImage.setImageResource(item.imageId)
        centerTitle.visibility = visibility
        centerTitle.text = item.name
        centerDescription.visibility = visibility
        centerDescription.text = item.description

        if (visibility == View.VISIBLE) {
            healthPoints.visibility = View.INVISIBLE
            heartIcon.visibility = View.INVISIBLE
            damagePoints.visibility = View.INVISIBLE
            damageIcon.visibility = View.INVISIBLE
            questLogIcon.visibility = View.INVISIBLE
            inventoryIcon.visibility = View.INVISIBLE

            centerImage.setOnClickListener {
                setBackDropContent(View.INVISIBLE, Item())
                timedActionController.stopRunning()
                player.pickUpItem(item)
                isItemBeingShown = false
                gameDataManager.savePlayer(player)
                QuestController.update(player.quests, QuestType.COLLECTING, item, player.inventory)
                QuestController.update(player.quests, QuestType.SPEAKING, item)
            }
        } else {
            healthPoints.visibility = View.VISIBLE
            heartIcon.visibility = View.VISIBLE
            damagePoints.visibility = View.VISIBLE
            damageIcon.visibility = View.VISIBLE
            questLogIcon.visibility = View.VISIBLE
            inventoryIcon.visibility = View.VISIBLE
        }
    }

    private fun addAugmentedImageToDB(arModel: ArModel, augmentedImageDatabase: AugmentedImageDatabase) {
        augmentedImageDatabase.addImage(
            arModel.gltfPath,
            BitmapFactory.decodeStream(assets.open(arModel.fiducialMarkerPath)),
            0.12F
        )
    }

    private fun updateModelOrientation(transformableNode: TransformableNode, degree: Float) {
        val cameraPosition = arFragment.arSceneView.scene.camera.worldPosition
        val transformableNodePosition = transformableNode.worldPosition
        if (cameraPosition != null && transformableNodePosition != null) {
            val relativePosition = Vector3.subtract(cameraPosition, transformableNodePosition)
            val direction = Vector3(relativePosition.x, 0.0f, relativePosition.z).normalized()
            val rotation = Quaternion.lookRotation(direction, Vector3.up())
            transformableNode.worldRotation = Quaternion.multiply(rotation, Quaternion.axisAngle(Vector3.up(), degree))
        }
    }

    private fun getModelByGtlfPath(gltfPath: String): ArModel {
        val result = arModels.find { it.gltfPath == gltfPath }
        if (result == null) {
            return ArModel()
        }
        return result
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayModel(imageName: String, arModel: ArModel, anchorNode: Node) {
        ModelRenderable.builder()
            .setSource(this, Uri.parse("file:///android_asset/$imageName"))
            .setIsFilamentGltf(true)
            .build()
            .thenAccept { modelRenderable ->
                // use runOnUiThread to make sure all views appear properly (does this really work???)
                runOnUiThread {
                    arFragment.arSceneView.scene.addChild(anchorNode.apply {
                        addChildNode(this, modelRenderable, arModel, "idle")

                        if (arModel is Enemy) {
                            var isInitialAttack = true
                            scriptController.setOnCompletionListener {
                                if (isInitialAttack) {
                                    changeNodeAnimation(this, modelRenderable, arModel, "attack")
                                    isInitialAttack = false
                                }
                            }
                        }
                    })
                }
            }
    }

    private fun changeNodeAnimation(
        anchorNode: Node,
        modelRenderable: ModelRenderable,
        arModel: ArModel,
        animationName: String
    ) {
        clearChildNodes(anchorNode)
        addChildNode(anchorNode, modelRenderable, arModel, animationName)
    }

    private fun addChildNode(
        anchorNode: Node,
        modelRenderable: ModelRenderable,
        arModel: ArModel,
        animationName: String
    ) {
        anchorNode.addChild(createChildNode(anchorNode, modelRenderable, arModel, animationName))
    }

    private fun createChildNode(
        anchorNode: Node,
        modelRenderable: ModelRenderable,
        arModel: ArModel,
        animationName: String
    ): TransformableNode {
        return TransformableNode(arFragment.transformationSystem).apply {
            this.scaleController.maxScale = 100F
            this.scaleController.minScale = 0.001f
            this.translationController.isEnabled = false
            this.localScale = arModel.scale
            renderable = modelRenderable

            if (animationName == "attack") {
                gameState.isFightOngoing.value = true
            }

            if (renderableInstance.animationCount > 0) {

                if (arModel.useCustomCollisionShape) {
                    collisionShape = Box(
                        Vector3(localScale.x / 2, localScale.y / 2, localScale.z / 2)
                    )
                }

                if (animationName == "dead" && arModel is Enemy && !arModel.loopDeathAnimation) {
                    playAnimationOnce(renderableInstance, arModel, animationName, true)
                } else if (animationName == "idle" && arModel is Collectable && !arModel.loopIdleAnimation) {
                    playAnimationOnce(renderableInstance, arModel, animationName, false)
                } else {
                    renderableInstance.animate(arModel.animations[animationName]).start()
                }

                if (animationName == "idle") {
                    if (arModel is Enemy) {
                        scriptController.play(null, arModel, player.quests, "attack")
                    } else {
                        scriptController.play(gameState, arModel, player.quests)
                    }
                }

                if (arModel is Enemy && animationName == "attack") {

                    arModel.health = arModel.maxHealth

                    val attackLength = renderableInstance.getAnimation(arModel.animations[animationName]).durationMillis
                    timedActionController.runRepeatedly(attackLength) {
                        player.damage(arModel)
                        soundController.start("common_sounds/get_hit.mp3")
                        vibrationController.vibrate()
                    }

                    setOnTapListener { hitTestResult, motionEvent ->
                        //hit enemy
                        if (arModel.health > 0) {
                            if (isInvincible(arModel)) {
                                arModel.damage(0)
                            } else {
                                arModel.damage(player.damagePoint.value!!)
                                setModelHealthBar((arModel.health.toFloat() / arModel.maxHealth.toFloat() * 100).toInt())
                            }
                            soundController.start("common_sounds/punch.mp3")
                            if (arModel.health <= 0) {
                                timedActionController.stopRunning()
                                changeNodeAnimation(anchorNode, modelRenderable, arModel, "dead")
                                gameState.isFightOngoing.value = false
                                QuestController.update(player.quests, QuestType.KILLING, arModel)
                                handleSpecialCharacterDefeat(arModel)
                            }
                        }
                    }
                }

                if (arModel !is Collectable) {
                    if (animationName != "dead") {
                        arFragment.arSceneView.scene.addOnUpdateListener {
                            updateModelOrientation(this, arModel.rotationDegree)
                        }
                    } else {
                        gameState.isFightOngoing.value = false
                        timedActionController.runAfterDelay(100) {
                            updateModelOrientation(this, arModel.rotationDegree)
                        }
                        scriptController.play(null, arModel, player.quests, "dead")
                        if (arModel is Enemy) {
                            scriptController.setOnCompletionListener {
                                if (arModel.reward != null) {
                                    showItem(arModel.reward!!)
                                }
                                if (arModel.loopDeathAnimation) {
                                    timedActionController.runAfterDelay(3000) {
                                        clearAllNodes()
                                    }
                                }
                            }
                        }
                    }
                } else {
                    setOnTapListener { hitTestResult, motionEvent ->
                        showItem(arModel.item)
                        QuestController.update(player.quests, QuestType.COLLECTING, arModel.item)
                        clearAllNodes()
                    }
                }
            }

            if (arModel is Food) {
                setOnTapListener { hitTestResult, motionEvent ->
                    player.heal(arModel.healPoint)
                    arModel.sounds["default"]?.let { soundController.start(it) }
                }
            }
        }
    }

    private fun isInvincible(arModel: ArModel): Boolean {
        return arModel is Enemy && arModel.invincibleUntilChapter > gameState.chapter
    }

    private fun playAnimationOnce(
        renderableInstance: RenderableInstance,
        arModel: ArModel,
        animationName: String,
        clearAfterAnimation: Boolean
    ) {
        val animatableModel = renderableInstance.animate(arModel.animations[animationName])
        animatableModel.repeatCount = 0
        animatableModel.start()
        if (clearAfterAnimation) {
            animatableModel.doOnEnd {
                clearAllNodes()
            }
        }
    }

    private fun handleSpecialCharacterDefeat(arModel: ArModel) {
        if (arModel is GoldFish) {
            gameState.isGoldFishDefeated = true
            gameDataManager.saveBooleanValue(GameDataManager.Key.GOLD_FISH_DEFEATED.name, true)
        } else if (arModel is DragonLordBlack) {
            gameState.isDragonLordBlackIsDefeated = true
            gameDataManager.saveBooleanValue(GameDataManager.Key.DRAGON_LORD_BLACK_DEFEATED.name, true)
        } else if (arModel is DragonLordSnowPrince) {
            gameState.isDragonLordSnowPrinceIsDefeated = true
            gameDataManager.saveBooleanValue(GameDataManager.Key.DRAGON_LORD_SNOW_PRINCE_DEFEATED.name, true)
        } else if (arModel is DragonLordHorn) {
            gameState.isDragonLordHornIsDefeated = true
            gameDataManager.saveBooleanValue(GameDataManager.Key.DRAGON_LORD_HORN_DEFEATED.name, true)
        } else if (arModel is StormWingBoss) {
            gameState.isStormWingBossDefeated = true
            gameDataManager.saveBooleanValue(GameDataManager.Key.STORM_WING_BOSS_DEFEATED.name, true)
        }
    }

    private fun showItem(reward: Item) {
        isItemBeingShown = true
        setBackDropContent(View.VISIBLE, reward)
    }

    private fun clearChildNodes(anchorNode: Node) {
        anchorNode.children.forEach {
            anchorNode.removeChild(it)
            it.renderableInstance.destroyGltfAsset()
            it.parent = null
            it.renderable = null
        }
    }

    private fun clearAllNodes() {
        val modelInfoView: LinearLayout = findViewById(R.id.modelInfo)
        modelInfoView.visibility = View.GONE
        with(arFragment.arSceneView.scene.children.iterator()) {
            forEach {
                if (it is AnchorNode) {
                    clearChildNodes(it)
                    arFragment.arSceneView.scene.removeChild(it)
                    it.anchor?.detach()
                    it.parent = null
                    it.renderable = null
                    augmentedImageMap.clear()
                }
            }
        }
    }
}