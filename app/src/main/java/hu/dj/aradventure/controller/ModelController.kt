package hu.dj.aradventure.controller

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.TransformableNode
import hu.dj.aradventure.armodel.ArModel
import hu.dj.aradventure.armodel.Enemy

class ModelController(context: Context) {

/*    @RequiresApi(Build.VERSION_CODES.O)
    private fun displayModel(imageName: String, arModel: ArModel, anchorNode: Node, context: Context) {
        ModelRenderable.builder()
            .setSource(context, Uri.parse("file:///android_asset/$imageName"))
            .setIsFilamentGltf(true)
            .build()
            .thenAccept { modelRenderable ->
                arFragment.arSceneView.scene.addChild(anchorNode.apply {
                    addChildNode(this, modelRenderable, arModel, "idle")

                    mediaPlayer?.setOnCompletionListener {
                        changeNodeAnimation(this, modelRenderable, arModel, "attack")
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
            this.scaleController.minScale = 0.1f
            this.translationController.isEnabled = false
            this.localScale = Vector3(0.4f, 0.4f, 0.4f)
            renderable = modelRenderable

            if (animationName == "attack") {
                gameState.isFightOngoing.value = true
            }

            renderableInstance.animate(arModel.animations[animationName]).start()

            if (animationName == "idle") {
                val assetFilePath = "hell_minion/sounds/1.mp3"
                val assetFileDescriptor = assets.openFd(assetFilePath)
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(
                        assetFileDescriptor.fileDescriptor,
                        assetFileDescriptor.startOffset,
                        assetFileDescriptor.length
                    )
                    prepare()
                    start()
                }
            }

            if (arModel is Enemy && animationName == "attack") {
                val attackLength = renderableInstance.getAnimation(arModel.animations[animationName]).durationMillis
                timedActionController.runRepeatedly(attackLength) {
                    player.damage(arModel.damagePoint)
                }

                setOnTapListener { hitTestResult, motionEvent ->
                    //hit enemy
                    if (arModel.health > 0) {
                        arModel.damage(player.damagePoint)
                        if (arModel.health <= 0) {
                            timedActionController.stopRunning()
                            changeNodeAnimation(anchorNode, modelRenderable, arModel, "dead")
                        }
                    }
                }
            }

            if (animationName != "dead") {
                arFragment.arSceneView.scene.addOnUpdateListener {
                    updateModelOrientation(this)
                }
            } else {
                timedActionController.runAfterDelay(100) {
                    gameState.isFightOngoing.value = false
                    updateModelOrientation(this)
                }
            }
        }
    }

    private fun clearChildNodes(anchorNode: Node) {
        anchorNode.children.forEach {
            anchorNode.removeChild(it)
        }
    }*/
}