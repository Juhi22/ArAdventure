package hu.dj.aradventure

import GameDataManager
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.AugmentedImage
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import hu.dj.aradventure.armodel.*
import hu.dj.aradventure.controller.*
import hu.dj.aradventure.dialog.QuestLogDialog
import hu.dj.aradventure.item.Death
import hu.dj.aradventure.item.Item
import hu.dj.aradventure.item.Quest
import hu.dj.aradventure.item.QuestType

class MainActivity : AppCompatActivity() {

    private lateinit var arFragment: ArFragment
    private lateinit var augmentedImageMap: MutableMap<AugmentedImage, AnchorNode>

    private lateinit var gameState: GameState
    private lateinit var gameDataManager: GameDataManager

    private var player = Player()
    private var medievalKnight = MedievalKnight
    private var unicorn = Unicorn
    private var hellMinion = HellMinion
    private var goldFish = GoldFish
    private var chickenSandwich = ChickenSandwich
    private var stormWing = StormWing

    private var arModels = mutableListOf(medievalKnight, hellMinion, unicorn, chickenSandwich, goldFish, stormWing)

    private var timedActionController = TimedActionController()
    private lateinit var soundController: SoundController
    private lateinit var scriptController: ScriptController
    private lateinit var vibrationController: VibrationController

    @SuppressLint("MissingInflatedId", "ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setBackDropContent(View.INVISIBLE, Item())

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

        val healthPointsView: TextView = findViewById(R.id.healthPoints)
        healthPointsView.text = player.health.value.toString()
        player.health.observe(this) {
            healthPointsView.text = it.toString()
        }
        player.isDead.observe(this) {
            if (it) {
                gameState.isFightOngoing.value = false
                showItem(Death)
                clearAllNodes()
            }
        }

        val questLogImage: ImageView = findViewById(R.id.questLog)
        questLogImage.setOnClickListener {
            val questLogDialog = QuestLogDialog(this)
            questLogDialog.show(player.quests)
        }

        QuestController.onEventListener(object : QuestController.QuestListener{
            override fun onQuestStarts(quest: Quest) {
                showItem(quest)
            }
            override fun onQuestEnds(quest: Quest) {
                player.finishQuest(quest)
                showItem(quest.reward)
                if (quest.nextChapter != null) {
                    gameState.chapter = quest.nextChapter
                    gameDataManager.saveGameState(quest.nextChapter)
                }
            }
            override fun onQuestsUpdated(quests: List<Quest>) {
                player.updateQuests(quests)
            }
        })

        augmentedImageMap = mutableMapOf()

        arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment

        arFragment.arSceneView.post {
            val augmentedImageDatabase = AugmentedImageDatabase(arFragment.arSceneView.session)
            //add medieval knight
            var bitmap: Bitmap = BitmapFactory.decodeStream(assets.open(medievalKnight.fiducialMarkerPath))
            augmentedImageDatabase.addImage(medievalKnight.gltfPath, bitmap, 0.12F)
            //add hell minion
            bitmap = BitmapFactory.decodeStream(assets.open(hellMinion.fiducialMarkerPath))
            augmentedImageDatabase.addImage(hellMinion.gltfPath, bitmap, 0.12F)
            //add chicken sandwich
            bitmap = BitmapFactory.decodeStream(assets.open(chickenSandwich.fiducialMarkerPath))
            augmentedImageDatabase.addImage(chickenSandwich.gltfPath, bitmap, 0.12F)
            //add unicorn
            bitmap = BitmapFactory.decodeStream(assets.open(unicorn.fiducialMarkerPath))
            augmentedImageDatabase.addImage(unicorn.gltfPath, bitmap, 0.12F)
            //add gold fish
            bitmap = BitmapFactory.decodeStream(assets.open(goldFish.fiducialMarkerPath))
            augmentedImageDatabase.addImage(goldFish.gltfPath, bitmap, 0.12F)
            //add storm wing
            bitmap = BitmapFactory.decodeStream(assets.open(stormWing.fiducialMarkerPath))
            augmentedImageDatabase.addImage(stormWing.gltfPath, bitmap, 0.12F)

            val config = arFragment.arSceneView.session?.config
            config?.augmentedImageDatabase = augmentedImageDatabase
            arFragment.arSceneView.session?.configure(config)
        }

        arFragment.arSceneView.scene.addOnUpdateListener { frameTime: FrameTime ->
            if (!player.isDead.value!! && !gameState.isFightOngoing.value!! && !scriptController.isScriptOngoing) {
                val updatedAugmentedImages =
                    arFragment.arSceneView.arFrame?.getUpdatedTrackables(AugmentedImage::class.java)
                updatedAugmentedImages?.forEach { augmentedImage ->
                    when (augmentedImage.trackingState) {
                        TrackingState.TRACKING -> {
                            if (!augmentedImageMap.containsKey(augmentedImage) &&
                                augmentedImage.trackingMethod == AugmentedImage.TrackingMethod.FULL_TRACKING
                            ) {
                                val currentModel = getModelByGtlfPath(augmentedImage.name)
                                if (!(currentModel is GoldFish && gameState.isGoldFishDefeated)) {
                                    clearAllNodes()
                                    val anchorNode = AnchorNode(augmentedImage.createAnchor(augmentedImage.centerPose))
                                    augmentedImageMap[augmentedImage] = anchorNode
                                    displayModel(augmentedImage.name, currentModel, anchorNode)
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

    override fun onDestroy() {
        super.onDestroy()
        soundController.shutdown()
    }

    private fun setBackDropContent(visibility: Int, item: Item) {
        val backdrop = findViewById<View>(R.id.backdrop)
        val healthPoints: TextView = findViewById(R.id.healthPoints)
        val heartIcon: ImageView = findViewById(R.id.heartIcon)
        val questLogIcon: ImageView = findViewById(R.id.questLog)
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
            questLogIcon.visibility = View.INVISIBLE

            centerImage.setOnClickListener {
                setBackDropContent(View.INVISIBLE, Item())
                timedActionController.stopRunning()
                player.pickUpItem(item)
            }
        } else {
            healthPoints.visibility = View.VISIBLE
            heartIcon.visibility = View.VISIBLE
            questLogIcon.visibility = View.VISIBLE
        }
    }

    private fun updateModelOrientation(transformableNode: TransformableNode) {
        val cameraPosition = arFragment.arSceneView.scene.camera.worldPosition
        val transformableNodePosition = transformableNode.worldPosition
        if (cameraPosition != null && transformableNodePosition != null) {
            val relativePosition = Vector3.subtract(cameraPosition, transformableNodePosition)
            val direction = Vector3(relativePosition.x, 0.0f, relativePosition.z).normalized()
            val rotation = Quaternion.lookRotation(direction, Vector3.up())
            transformableNode.worldRotation = Quaternion.multiply(rotation, Quaternion.axisAngle(Vector3.up(), 180f))
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
            this.scaleController.minScale = 0.01f
            this.translationController.isEnabled = false
            this.localScale = arModel.scale
            renderable = modelRenderable

            if (animationName == "attack") {
                gameState.isFightOngoing.value = true
            }

            if (renderableInstance.animationCount > 0) {

                renderableInstance.animate(arModel.animations[animationName]).start()

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
                            arModel.damage(player.damagePoint)
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

                if (animationName != "dead") {
                    arFragment.arSceneView.scene.addOnUpdateListener {
                        updateModelOrientation(this)
                    }
                } else {
                    gameState.isFightOngoing.value = false
                    timedActionController.runAfterDelay(100) {
                        updateModelOrientation(this)
                    }
                    scriptController.play(null, arModel, player.quests, "dead")
                    if (arModel is Enemy && arModel.reward != null) {
                        scriptController.setOnCompletionListener {
                            if (arModel.reward != null) {
                                showItem(arModel.reward!!)
                            }
                        }
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

    private fun handleSpecialCharacterDefeat(arModel: ArModel) {
        if (arModel is GoldFish) {
            gameState.isGoldFishDefeated = true
            gameDataManager.saveGoldFishDefeat()
        }
    }

    private fun showItem(reward: Item) {
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