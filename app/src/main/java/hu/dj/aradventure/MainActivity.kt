package hu.dj.aradventure

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.ar.core.*
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.rendering.Renderable
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.gorisse.thomas.sceneform.scene.await
import hu.dj.aradventure.armodel.*
import hu.dj.aradventure.controller.ScriptController
import hu.dj.aradventure.controller.SoundController
import hu.dj.aradventure.controller.TimedActionController
import hu.dj.aradventure.controller.VibrationController

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var arFragment: ArFragment
    private lateinit var augmentedImageMap: MutableMap<AugmentedImage, AnchorNode>
    private var model: Renderable? = null
    private var modelView: ViewRenderable? = null
    private val arSceneView get() = arFragment.arSceneView
    private val scene get() = arSceneView.scene

    private var textToSpeech: TextToSpeech? = null

    private var lastTouchX = 0f
    private var lastTouchY = 0f

    private var player = Player()
    private var gameState = GameState()
    private var medievalKnight = MedievalKnight
    private var unicorn = Unicorn
    var hellMinion = HellMinion
    var goldFish = GoldFish
    var chickenSandwich = ChickenSandwich

    var arModels = mutableListOf(medievalKnight, hellMinion, unicorn, chickenSandwich, goldFish)

    var timedActionController = TimedActionController()
    lateinit var soundController: SoundController
    lateinit var scriptController: ScriptController
    lateinit var vibrationController: VibrationController

    @SuppressLint("MissingInflatedId", "ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        soundController = SoundController(assets)

        scriptController = ScriptController(
            findViewById(R.id.mob_text),
            findViewById(R.id.player_text_A),
            findViewById(R.id.player_text_B),
            soundController
        )
        scriptController.init()

        vibrationController = VibrationController(this)

        gameState.isFightOngoing.observe(this) {
            var fightIndicatorView = findViewById<ImageView>(R.id.fight_indicator)
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

        textToSpeech = TextToSpeech(this, this)

        augmentedImageMap = mutableMapOf()

        /*        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar).apply {
                    title = ""
                })

                supportFragmentManager.commit {
                    add(R.id.containerFragment, MainFragment::class.java, Bundle())
                }*/

        //engine = Engine.create()


        //if (checkIsSupportedDeviceOrFinish()) {
        arFragment = supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment
        /*        arFragment = (childFragmentManager.findFragmentById(R.id.arFragment) as ArFragment).apply {
                    setOnSessionConfigurationListener { session, config ->
                        // Modify the AR session configuration here
                    }
                    setOnViewCreatedListener { arSceneView ->
                        arSceneView.setFrameRateFactor(SceneView.FrameRate.FULL)
                    }
                    setOnTapArPlaneListener(::onTapPlane)
                }*/
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

            val config = arFragment.arSceneView.session?.config
            config?.augmentedImageDatabase = augmentedImageDatabase
            arFragment.arSceneView.session?.configure(config)
        }

/*        arFragment.arSceneView.scene.setOnTouchListener { hitTestResult, motionEvent ->
            // Kattintás pozíciójának meghatározása
            val x = motionEvent.x
            val y = motionEvent.y

            // Találati pont meghatározása az AR térben
            val hitTestResults = arSceneView.arFrame?.hitTest(x, y)

            // Ha találtunk egy pontot
            if (hitTestResults != null && hitTestResults.isNotEmpty()) {
                val hitPose = hitTestResults[0].hitPose

                // Létrehozunk egy ViewRenderable-t a damage_number_layout alapján
                val renderable = ViewRenderable.builder()
                    .setView(this@MainActivity, R.layout.damage_number_layout)
                    .build()
                    .thenAccept { renderable ->
                        // Elhelyezzük a ViewRenderable-t a találati pozícióban
                        val node = Node()
                        node.renderable = renderable
                        node.worldPosition.x = hitPose.tx()
                        node.worldRotation.y = hitPose.ty()
                        node.worldPosition.z = hitPose.tz()

                        // Hozzáadjuk a node-ot az ARScene-hez
                        arSceneView.scene.addChild(node)
                    }
                    .exceptionally { throwable ->
                        throw RuntimeException("Error creating renderable", throwable)
                    }
            }

            true
        }*/

/*        arSceneView.setOnTouchListener { view, motionEvent ->
            lastTouchX = motionEvent.x
            lastTouchY = motionEvent.y
            false
        }*/

/*        //TODO it does not work
        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
            val trackable = hitResult.trackable
            if (trackable is AugmentedImage) {
                // Helyezze el a "damage number" szöveget az AR modell felett
                val damageNumberLayout = LayoutInflater.from(this).inflate(R.layout.damage_number_layout, arFragment.view as ViewGroup, false)
                val textView = damageNumberLayout.findViewById<TextView>(R.id.damageNumber)

                // Pozicionálja a szöveget az AR modell felett
                val anchor = hitResult.createAnchor()
                val anchorNode = AnchorNode(anchor)
                anchorNode.addChild(Node().apply {
                    parent = anchorNode
                    localPosition = Vector3(0f, 0.1f, 0f)  // Állítsa be a szöveg pozícióját az AR modell felett
//                    renderable = ViewRenderable.builder()
//                        .setView(this@MainActivity, damageNumberLayout)
//                        .build()
                    val viewRenderableFuture = ViewRenderable.builder()
                        .setView(this@MainActivity, damageNumberLayout)
                        .build()
                    viewRenderableFuture.thenAccept { renderable ->
                        anchorNode.addChild(Node().apply {
                            setParent(anchorNode)
                            localPosition = Vector3(0f, 0.1f, 0f)  // Állítsa be a szöveg pozícióját az AR modell felett
                            this.renderable = renderable
                        })
                    }
                })

                arFragment.arSceneView.scene.addChild(anchorNode)
            }
        }*/

        arFragment.arSceneView.scene.addOnUpdateListener { frameTime: FrameTime ->
            //updateModelOrientation()
            //checkOnTouch()
            val updatedAugmentedImages =
                arFragment.arSceneView.arFrame?.getUpdatedTrackables(AugmentedImage::class.java)
            updatedAugmentedImages?.forEach { augmentedImage ->
                when (augmentedImage.trackingState) {
                    TrackingState.TRACKING -> {
                        // Ha az augmented image követés alatt áll
                        if (!augmentedImageMap.containsKey(augmentedImage)) {
                            // Ha még nincs hozzárendelve AnchorNode, készíts egyet
                            val anchorNode = AnchorNode(augmentedImage.createAnchor(augmentedImage.centerPose))
                            augmentedImageMap[augmentedImage] = anchorNode

                            // A 3D modell betöltése és hozzáadása az AnchorNode-hoz
                            /*ModelRenderable.builder()
                                .setSource(this, Uri.parse("file:///android_asset/${augmentedImage.name}")) // Cseréld le a saját modell forrásodra
                                .build()
                                .thenAccept { modelRenderable ->
                                    val node = TransformableNode(arFragment.transformationSystem)
                                    node.setParent(anchorNode)
                                    node.renderable = modelRenderable
                                    arFragment.arSceneView.scene.addChild(anchorNode)
                                }*/
                            val currentModel = getModelByGtlfPath(augmentedImage.name)

                            displayModel(augmentedImage.name, currentModel, anchorNode)

                            //fight
                            /*if(currentModel is Enemy) {
                                anchorNode.setOnTapListener { hitTestResult, motionEvent ->
                                    currentModel.damage(player.damagePoint)
                                    if (currentModel.health >= 0) {
                                        anchorNode.renderableInstance.animate(currentModel.animations["dead"]).start()
                                    }
                                }
                            }*/
                        }
                    }

                    TrackingState.STOPPED -> {
                        // Ha az augmented image nem látható
                        augmentedImageMap.remove(augmentedImage)
                    }

                    TrackingState.PAUSED -> {}
                }
            }
        }

        /*        arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
                    onTapPlane(hitResult, plane, motionEvent)
                    // Create the Anchor
                    arFragment.arSceneView.scene.addChild(AnchorNode(hitResult.createAnchor()).apply {
                        // Create the transformable model and add it to the anchor
                        addChild(TransformableNode(arFragment.transformationSystem).apply {
                            this.scaleController.minScale = 0.1f
                            this.translationController.isEnabled = false
                            this.localScale = Vector3(0.4f, 0.4f, 0.4f)
                            renderable = model
                            renderableInstance.animate(true).start()
                        })
                    })
                }*/

        //TODO do I need to preload them?
        lifecycleScope.launchWhenCreated {
            loadModels()
        }

        //val sceneViewerIntent = Intent(Intent.ACTION_VIEW)
        //sceneViewerIntent.setData(Uri.parse("https://arvr.google.com/scene-viewer/1.0?file=https://raw.githubusercontent.com/KhronosGroup/glTF-Sample-Models/master/2.0/Avocado/glTF/Avocado.gltf"))
        //sceneViewerIntent.setData(Uri.parse("file:///android_asset/medieval_knight/scene.gltf"))
        //sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox")
        //startActivity(sceneViewerIntent)
        //}
    }

/*    private fun checkOnTouch() {
        // Találati eredmények
        val hitTestResults = arSceneView.arFrame?.hitTest(lastTouchX, lastTouchY)

        // Ha van találat
        if (hitTestResults != null && hitTestResults.isNotEmpty()) {
            // Az első találati pont
            val hitResult = hitTestResults[0]

            // Itt eldöntheted, hogy a kattintás a modellre történt-e vagy sem
            val trackable = hitResult.trackable
            if (trackable is AugmentedImage && trackable.trackingState == TrackingState.TRACKING) {
                // Ha a kattintás a modellre történt, jelenítsd meg a damage indicatort
                createDamageNumber(hitResult.hitPose)
            }
        }
    }*/

//     private fun createDamageNumber(hitPose: Pose) {
   /* private fun createDamageNumber(x: Float, y: Float, z: Float) {
        val damageNumberView = findViewById<TextView>(R.id.damageNumber)

    val damageRenderable = ViewRenderable.builder()
        .setView(this, damageNumberView)
        .build()

    val damageNode = Node().apply {
        setParent(arSceneView.scene)
        localPosition = Vector3(x, y, z)
    }

    damageRenderable.thenAccept { renderable ->
        damageNode.renderable = renderable
    }
    }*/

    override fun onInit(status: Int) {
        // Ellenőrizzük, hogy a TextToSpeech inicializálás sikeres volt-e
        if (status == TextToSpeech.SUCCESS) {
            // A TextToSpeech sikeresen inicializálva lett, itt további beállításokat végezhetünk
            /*val locale = Locale.getDefault()
            val result = textToSpeech!!.setLanguage(locale)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // A kívánt nyelv nem támogatott vagy hiányzik az adatok
                // Itt kezelheted a hiányzó nyelvet vagy adatokat
            }*/
        } else {
            // A TextToSpeech inicializálása sikertelen
            // Itt kezelheted a sikertelenséget
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        textToSpeech?.stop()
        textToSpeech?.shutdown()

        soundController.shutdown()
    }

    /*    private fun updateModelOrientation() {
            // Megkeressük az AnchorNode-ot a jelenlegi scene-ben
            val anchorNode = arFragment.arSceneView.scene.children
                .firstOrNull { it is AnchorNode } as? AnchorNode

            // Ha van AnchorNode, akkor megkeressük a gyermekét (TransformableNode)
            anchorNode?.children?.firstOrNull { it is TransformableNode }?.let { transformableNode ->
                // A kamera pozícióját és a TransformableNode pozícióját lekérjük
                val cameraPosition = arFragment.arSceneView.scene.camera.worldPosition
                val transformableNodePosition = transformableNode.worldPosition

                // Ha mindkét pozíció elérhető, akkor számoljuk az irányvektort
                if (cameraPosition != null && transformableNodePosition != null) {
                    // Kiszámoljuk a modell és a kamera közötti relativ pozíciót
                    val relativePosition = Vector3.subtract(cameraPosition, transformableNodePosition)

                    // Kiszámoljuk az irányvektort csak a modell tengelye körül
                    val direction = Vector3(relativePosition.x, 0.0f, relativePosition.z).normalized()

                    // Kiszámoljuk az orientációt a modell helyzetének megváltoztatása nélkül
                    val rotation = Quaternion.lookRotation(direction, Vector3.up())

                    // Beállítjuk a TransformableNode orientációját és elforgatjuk az eleje felé
                    //transformableNode.localRotation = Quaternion.multiply(rotation, Quaternion.axisAngle(Vector3.up(), -90f))
                    transformableNode.localRotation = Quaternion.multiply(rotation, Quaternion.axisAngle(Vector3.up(), 180f))
                    //transformableNode.worldRotation = Quaternion.multiply(rotation, Quaternion.axisAngle(Vector3(0f, 0f, 1f),180f))
                }
            }
        }*/

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
                arFragment.arSceneView.scene.addChild(anchorNode.apply {
                    addChildNode(this, modelRenderable, arModel, "idle")

                    soundController.mediaPlayer?.setOnCompletionListener {
                        if (arModel is Enemy) {
                            changeNodeAnimation(this, modelRenderable, arModel, "attack")
                        }
                    }
                })
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
                    scriptController.play(gameState, arModel)
                }

                if (arModel is Enemy && animationName == "attack") {
                    val attackLength = renderableInstance.getAnimation(arModel.animations[animationName]).durationMillis
                    timedActionController.runRepeatedly(attackLength) {
                        player.damage(arModel.damagePoint)
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

    private fun clearChildNodes(anchorNode: Node) {
        anchorNode.children.forEach {
            anchorNode.removeChild(it)
        }
    }

    private fun adjustNodeFacingCamera(transformableNode: TransformableNode) {
        // Get the camera's forward vector.
        val cameraForward =
            Vector3.subtract(arFragment.arSceneView.scene.camera.worldPosition, transformableNode.worldPosition)
        cameraForward.y = 0.0f // Ignore the vertical component.

        // Calculate the rotation quaternion to make the node face the camera.
        val rotation = Quaternion.lookRotation(cameraForward.normalized(), Vector3.up())
        transformableNode.worldRotation = Quaternion.multiply(rotation, Quaternion.axisAngle(Vector3.up(), -180f))
    }

    private suspend fun loadModels() {
        model = ModelRenderable.builder()
            .setSource(this, Uri.parse("file:///android_asset/medieval_knight/scene.gltf"))
            .setIsFilamentGltf(true)
            .await()
        /*       modelView = ViewRenderable.builder()
                    .setView(this, R.layout.activity_main)
                    .await()*/
        /*        ModelRenderable.builder()
                    .setSource(this, RenderableSource.builder().setSource(
                        this,
                        Uri.parse("file:///android_asset/aaa.glb"),
                        RenderableSource.SourceType.GLB)
                        .setScale(0.5f)  // Scale the original model to 50%.
                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                        .build())
                    .setRegistryId(GLTF_ASSET)
                    .build()
                    .thenAccept { renderable ->
                        addNodeToScene(renderable)
                        //playAnimation(renderable)
                    }
                    .exceptionally { throwable ->
                        return@exceptionally null
                    }*/
        /*        ModelRenderable.builder()
                    .setSource(this, RenderableSource.builder().setSource(
                        this,
                        Uri.parse("file:///android_asset/medieval_knight/scene.gltf"),
                        RenderableSource.SourceType.GLTF2)
                        .setScale(0.5f)  // Scale the original model to 50%.
                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                        .build())
                    .setRegistryId(GLTF_ASSET)
                    .build()
                    .thenAccept { renderable ->
                        addNodeToScene(renderable)
                        playAnimation(renderable)
                    }
                    .exceptionally { throwable ->
                        return@exceptionally null
                    }
                ModelRenderable.builder()
                    .setSource(this, RenderableSource.builder().setSource(
                        this,
                        Uri.parse(GLTF_ASSET),
                        RenderableSource.SourceType.GLTF2)
                        .setScale(0.5f)  // Scale the original model to 50%.
                        .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                        .build())
                    .setRegistryId(GLTF_ASSET)
                    .build()
                    .thenAccept { renderable -> addNodeToScene(renderable) }
                .exceptionally(
                    {throwable -> {
                    Toast toast =
                    Toast.makeText(this, "Unable to load renderable " +
                            GLTF_ASSET, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    }
                });
                ModelRenderable.builder()
                    //.setSource(this, R.raw.aaa) // R.raw.your_model helyett add meg a GLB fájl forrását
                    .setSource(this, Uri.parse("file:///android_asset/medieval_knight/scene.gltf"))
                    .build()
                    .thenAccept { renderable -> addNodeToScene(renderable) }
                    .exceptionally { throwable ->
                        // Hiba esetén kezelés
                        return@exceptionally null
                    }*/
    }

    private fun onTapPlane(hitResult: HitResult, plane: com.google.ar.core.Plane, motionEvent: MotionEvent) {
        if (model == null || modelView == null) {
            Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
            return
        }

        // Create the Anchor.
        scene.addChild(AnchorNode(hitResult.createAnchor()).apply {
            // Create the transformable model and add it to the anchor.
            addChild(TransformableNode(arFragment.transformationSystem).apply {
                renderable = model
                renderableInstance.setCulling(false)
                renderableInstance.animate(true).start()
                // Add the View
                addChild(Node().apply {
                    // Define the relative position
                    //localPosition = Vector3(0.0f, 1f, 0.0f)
                    //localScale = Vector3(0.7f, 0.7f, 0.7f)
                    renderable = modelView
                })
            })
        })
    }

    private fun addNodeToScene(renderable: Renderable) {
        val node: TransformableNode = TransformableNode(arFragment.transformationSystem)
        node.renderable = renderable
        node.setParent(arFragment.arSceneView.scene)
        node.select()
    }

    /*    private fun playAnimation(renderable: Renderable) {
            // Ellenőrizze, hogy a modell tartalmaz-e animációt
            if (renderable is ModelRenderable && renderable.animationDataCount > 0) {
                val animationData = renderable.getAnimationData(0)
                val animator = ModelAnimator(animationData, renderable)

                // Animáció lejátszása
                animator.start()
            }
        }*/

    /*    private fun playAnimation(renderable: Renderable) {
            // Ellenőrizze, hogy a modell tartalmaz-e animációt
            if (renderable is ModelRenderable && renderable.animationDataCount > 0) {
                val animationData = renderable.getAnimationData(0)
                val animator = ModelAnimator(animationData, renderable)

                // Animáció lejátszása
                animator.start()
            }
        }*/

    private fun checkIsSupportedDeviceOrFinish(): Boolean {
        val availability = ArCoreApk.getInstance().checkAvailability(this)
        if (availability.isSupported) {
            return true
        } else {
            // Eszköz nem támogatott, kezeljük a helyzetet
            // Például: Toast.makeText(this, "Az eszköz nem támogatja az AR-t.", Toast.LENGTH_LONG).show()
            finish()
            return false
        }
    }

    //---------------------------------------------------------------------

    /*    private lateinit var appBarConfiguration: AppBarConfiguration
        private lateinit var binding: ActivityMainBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            setSupportActionBar(binding.toolbar)

            val navController = findNavController(R.id.nav_host_fragment_content_main)
            appBarConfiguration = AppBarConfiguration(navController.graph)
            setupActionBarWithNavController(navController, appBarConfiguration)

            binding.fab.setOnClickListener { view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.menu_main, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            return when (item.itemId) {
                R.id.action_settings -> true
                else -> super.onOptionsItemSelected(item)
            }
        }

        override fun onSupportNavigateUp(): Boolean {
            val navController = findNavController(R.id.nav_host_fragment_content_main)
            return navController.navigateUp(appBarConfiguration)
                    || super.onSupportNavigateUp()
        }*/
}