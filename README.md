ArAdventure

Added https://github.com/SceneView/sceneform-android internal to app/libs.
Had to change the source code, for glb models the memory was not freed up.
Added new function destroyGltfAsset to remove gltf models properly, following this aritcle: https://github.com/SceneView/sceneform-android/discussions/216?fbclid=IwZXh0bgNhZW0CMTAAAR1Crbmy-zgngNjCs7VzqFdLqjg-jtOwe9rGmvfDgaz14e5ZjQTLUG8FqIQ_aem_AapyeHxG5zi8nKIAt7DAyhu-LgXOzLgvd7zGzYUUF1qqnqCBbySsRw3eoOFZPoSVo-3DnPAvCEnt4R8BKzXrpoC2
In RenderableInstance.java.

add this way to free memory.

/**
* free gltf memo
*/
public void destroyGltfAsset(){
//desc- added by Ikkyu
if (loader == null)return;
if (filamentAsset != null){
loader.destroyAsset(filamentAsset);
filamentAsset = null;
}
}
Modify this method.

void createFilamentAssetModelInstance() {
if (renderable.getRenderableData() instanceof RenderableInternalFilamentAssetData) {
RenderableInternalFilamentAssetData renderableData =
(RenderableInternalFilamentAssetData) renderable.getRenderableData();
Engine engine = EngineInstance.getEngine().getFilamentEngine();
//updated by ikkyu
loader =
new AssetLoader(
engine,
RenderableInternalFilamentAssetData.getMaterialProvider(),
EntityManager.get());
//...
}