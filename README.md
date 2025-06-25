# ArAdventure
Be the hero who saves the valley. Complete quests, collect better equipments, make friends and fight.
This app can be a great game for youngsters, who are getting familiar with video games.

## How to play
Build an apk (`gradle build`) and install it to your device.

There are images images for the different objects/characters in the folders in /app/src/main/assets. They are the `fiducial_marker.jpg` files. Print them and lay them separately. When the game starts move the camera on them to make live.

There is a tutorial in the menu. Check it if you have some question about the game.

## Game has auto save
Your progress is saved automatically. If you hold the tutorial button a reset button appears, that can be used to undo your saves.

## Used external project
I could not reach the author of that project so I have added https://github.com/SceneView/sceneform-android internal to app/libs.
Had to change the source code, for glb models the memory was not freed up.
Added new function destroyGltfAsset to remove gltf models properly, following this aritcle: https://github.com/SceneView/sceneform-android/discussions/216?fbclid=IwZXh0bgNhZW0CMTAAAR1Crbmy-zgngNjCs7VzqFdLqjg-jtOwe9rGmvfDgaz14e5ZjQTLUG8FqIQ_aem_AapyeHxG5zi8nKIAt7DAyhu-LgXOzLgvd7zGzYUUF1qqnqCBbySsRw3eoOFZPoSVo-3DnPAvCEnt4R8BKzXrpoC2
In RenderableInstance.java.

Added this way to free memory:
```
public void destroyGltfAsset(){
  if (loader == null)return;
  if (filamentAsset != null){
    loader.destroyAsset(filamentAsset);
    filamentAsset = null;
  }
}

void createFilamentAssetModelInstance() {
if (renderable.getRenderableData() instanceof RenderableInternalFilamentAssetData) {
  RenderableInternalFilamentAssetData renderableData =
  (RenderableInternalFilamentAssetData) renderable.getRenderableData();
  Engine engine = EngineInstance.getEngine().getFilamentEngine();
  loader =
  new AssetLoader(
  engine,
  RenderableInternalFilamentAssetData.getMaterialProvider(),
  EntityManager.get());
  //...
}
```

## Spoiler!!!
This version is for Christmas, after the main quest the hero is sent to the tree. It can be used to make the player go to the tree and get the present.
