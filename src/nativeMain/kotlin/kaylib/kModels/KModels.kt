package kaylib.kModels

import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kModels

//=======================================================//
// BASIC 3D GEOMETRIC DRAWING FUNCTIONS
//=======================================================//

/**
 * Draw a line in 3D world space
 */
inline fun drawLine3D(startPos: Vector3, endPos: Vector3, color: Color) {
    DrawLine3D(startPos.readValue(), endPos.readValue(), color.readValue())
}

/**
 * Draw a point in 3D space, actually a small line
 */
inline fun drawPoint3D(position: Vector3, color: Color) {
    DrawPoint3D(position.readValue(), color.readValue())
}

/**
 * Draw a circle in 3D world space
 */
inline fun drawCircle3D(center: Vector3, radius: Float, rotationAxis: Vector3, rotationAngle: Float, color: Color) {
    DrawCircle3D(center.readValue(), radius, rotationAxis.readValue(), rotationAngle, color.readValue())
}

/**
 * Draw a color-filled triangle (vertex in counter-clockwise order!)
 */
inline fun drawTriangle3D(v1: Vector3, v2: Vector3, v3: Vector3, color: Color) {
    DrawTriangle3D(v1.readValue(), v2.readValue(), v3.readValue(), color.readValue())
}

/**
 * Draw a triangle strip defined by points
 */
inline fun drawTriangleStrip3D(points: Vector3, pointCount: Int, color: Color) {
    DrawTriangleStrip3D(points.ptr, pointCount, color.readValue())
}

/**
 * Draw cube
 */
inline fun drawCube(position: Vector3, width: Float, height: Float, length: Float, color: Color) {
    DrawCube(position.readValue(), width, height, length, color.readValue())
}

/**
 * Draw cube (Vector version)
 */
inline fun drawCubeV(position: Vector3, size: Vector3, color: Color) {
    DrawCubeV(position.readValue(), size.readValue(), color.readValue())
}

/**
 * Draw cube wires
 */
inline fun drawCubeWires(position: Vector3, width: Float, height: Float, length: Float, color: Color) {
    DrawCubeWires(position.readValue(), width, height, length, color.readValue())
}

/**
 * Draw cube wires (Vector version)
 */
inline fun drawCubeWiresV(position: Vector3, size: Vector3, color: Color) {
    DrawCubeWiresV(position.readValue(), size.readValue(), color.readValue())
}

///**
// * Draw cube textured
// */
//inline fun drawCubeTexture(texture: Texture2D, position: Vector3, width: Float, height: Float, length: Float, color: Color) {
//    DrawCubeTexture(texture.readValue(), position.readValue(), width, height, length, color.readValue())
//}
//
///**
// * Draw cube with a region of a texture
// */
//inline fun drawCubeTextureRec(texture: Texture2D, source: Rectangle, position: Vector3, width: Float, height: Float, length: Float, color: Color) {
//    DrawCubeTextureRec(texture.readValue(), source.readValue(), position.readValue(), width, height, length, color.readValue())
//}

/**
 * Draw sphere
 */
inline fun drawSphere(centerPos: Vector3, radius: Float, color: Color) {
    DrawSphere(centerPos.readValue(), radius, color.readValue())
}

/**
 * Draw sphere with extended parameters
 */
inline fun drawSphereEx(centerPos: Vector3, radius: Float, rings: Int, slices: Int, color: Color) {
    DrawSphereEx(centerPos.readValue(), radius, rings, slices, color.readValue())
}

/**
 * Draw sphere wires
 */
inline fun drawSphereWires(centerPos: Vector3, radius: Float, rings: Int, slices: Int, color: Color) {
    DrawSphereWires(centerPos.readValue(), radius, rings, slices, color.readValue())
}

/**
 * Draw a cylinder/cone
 */
inline fun drawCylinder(position: Vector3, radiusTop: Float, radiusBottom: Float, height: Float, slices: Int, color: Color) {
    DrawCylinder(position.readValue(), radiusTop, radiusBottom, height, slices, color.readValue())
}

/**
 * Draw a cylinder with base at startPos and top at endPos
 */
inline fun drawCylinderEx(startPos: Vector3, endPos: Vector3, startRadius: Float, endRadius: Float, sides: Int, color: Color) {
    DrawCylinderEx(startPos.readValue(), endPos.readValue(), startRadius, endRadius, sides, color.readValue())
}

/**
 * Draw a cylinder/cone wires
 */
inline fun drawCylinderWires(position: Vector3, radiusTop: Float, radiusBottom: Float, height: Float, slices: Int, color: Color) {
    DrawCylinderWires(position.readValue(), radiusTop, radiusBottom, height, slices, color.readValue())
}

/**
 * Draw a cylinder wires with base at startPos and top at endPos
 */
inline fun drawCylinderWiresEx(startPos: Vector3, endPos: Vector3, startRadius: Float, endRadius: Float, sides: Int, color: Color) {
    DrawCylinderWiresEx(startPos.readValue(), endPos.readValue(), startRadius, endRadius, sides, color.readValue())
}

/**
 * Draw a plane XZ
 */
inline fun drawPlane(centerPos: Vector3, size: Vector2, color: Color) {
    DrawPlane(centerPos.readValue(), size.readValue(), color.readValue())
}

/**
 * Draw a ray line
 */
inline fun drawRay(ray: Ray, color: Color) {
    DrawRay(ray.readValue(), color.readValue())
}

/**
 * Draw a grid (centered at (0, 0, 0))
 */
inline fun drawGrid(slices: Int, spacing: Float) {
    DrawGrid(slices, spacing)
}

//=======================================================//
// 3D MODEL LOADING FUNCTIONS
//=======================================================//

/**
 * Load model from files (meshes and materials)
 */
inline fun loadModel(fileName: String) : Model {
    return LoadModel(fileName).getPointer(MemScope()).pointed
}

/**
 * Load model from generated mesh (default material)
 */
inline fun loadModelFromMesh(mesh: Mesh) : Model {
    return LoadModelFromMesh(mesh.readValue()).getPointer(MemScope()).pointed
}

/**
 * Unload model (including meshes) from memory (RAM and/or VRAM)
 */
inline fun unloadModel(model: Model) {
    UnloadModel(model.readValue())
}

/**
 * Unload model (but not meshes) from memory (RAM and/or VRAM)
 */
inline fun unloadModelKeepMeshes(model: Model) {
    UnloadModelKeepMeshes(model.readValue())
}

/**
 * Compute model bounding box limits (considers all meshes)
 */
inline fun getModelBoundingBox(model: Model) : BoundingBox {
    return GetModelBoundingBox(model.readValue()).getPointer(MemScope()).pointed
}

//=======================================================//
// 3D MODEL DRAWING FUNCTIONS
//=======================================================//

/**
 * Draw a model (with texture if set)
 */
inline fun drawModel(model: Model , position: Vector3, scale: Float, tint: Color) {
    DrawModel(model.readValue(), position.readValue(), scale, tint.readValue())
}

/**
 * Draw a model with extended parameters
 */
inline fun drawModelEx(model: Model, position: Vector3, rotationAxis: Vector3, rotationAngle: Float, scale: Vector3, tint: Color) {
    DrawModelEx(model.readValue(), position.readValue(), rotationAxis.readValue(), rotationAngle, scale.readValue(), tint.readValue())
}

/**
 * Draw a model wires (with texture if set)
 */
inline fun drawModelWires(model: Model, position: Vector3, scale: Float, tint: Color) {
    DrawModelWires(model.readValue(), position.readValue(), scale, tint.readValue())
}

/**
 * Draw a model wires (with texture if set) with extended parameters
 */
inline fun drawModelWiresEx(model: Model, position: Vector3, rotationAxis: Vector3, rotationAngle: Float, scale: Vector3, tint: Color) {
    DrawModelWiresEx(model.readValue(), position.readValue(), rotationAxis.readValue(), rotationAngle, scale.readValue(), tint.readValue())
}

/**
 * Draw bounding box (wires)
 */
inline fun drawBoundingBox(box: BoundingBox, color: Color) {
    DrawBoundingBox(box.readValue(), color.readValue())
}

/**
 * Draw a billboard texture
 */
inline fun drawBillboard(camera: Camera, texture: Texture2D, position: Vector3, size: Float, tint: Color) {
    DrawBillboard(camera.readValue(), texture.readValue(), position.readValue(), size, tint.readValue())
}

/**
 * Draw a billboard texture defined by source
 */
inline fun drawBillboardRec(camera: Camera, texture: Texture2D, source: Rectangle, position: Vector3, size: Vector2, tint: Color) {
    DrawBillboardRec(camera.readValue(), texture.readValue(), source.readValue(), position.readValue(), size.readValue(), tint.readValue())
}

/**
 * Draw a billboard texture defined by source and rotation
 */
inline fun drawBillboardPro(camera: Camera, texture: Texture2D, source: Rectangle, position: Vector3, up: Vector3, size: Vector2, origin: Vector2, rotation: Float, tint: Color) {
    DrawBillboardPro(camera.readValue(), texture.readValue(), source.readValue(), position.readValue(), up.readValue(), size.readValue(), origin.readValue(), rotation, tint.readValue())
}

//=======================================================//
// MESH MANAGEMENT FUNCTIONS
//=======================================================//

/**
 * Upload mesh vertex data in GPU and provide VAO/VBO ids
 */
inline fun uploadMesh(mesh: Mesh, dynamic: Boolean) {
    UploadMesh(mesh.ptr, dynamic)
}

/**
 * Update mesh vertex data in GPU for a specific buffer index
 */
inline fun updateMeshBuffer(mesh: Mesh, index: Int, data: COpaquePointer, dataSize: Int, offset: Int) {
    UpdateMeshBuffer(mesh.readValue(), index, data, dataSize, offset)
}

/**
 * Unload mesh data from CPU and GPU
 */
inline fun unloadMesh(mesh: Mesh) {
    UnloadMesh(mesh.readValue())
}

/**
 * Draw a 3d mesh with material and transform
 */
inline fun drawMesh(mesh: Mesh, material: Material, transform: Matrix) {
    DrawMesh(mesh.readValue(), material.readValue(), transform.readValue())
}

/**
 * Draw multiple mesh instances with material and different transforms
 */
inline fun drawMeshInstanced(mesh: Mesh, material: Material, transforms: Matrix, instances: Int) {
    DrawMeshInstanced(mesh.readValue(), material.readValue(), transforms.ptr, instances)
}

/**
 * Export mesh data to file, returns true on success
 */
inline fun exportMesh(mesh: Mesh, fileName: String) : Boolean {
    return ExportMesh(mesh.readValue(), fileName)
}

/**
 * Compute mesh bounding box limits
 */
inline fun  getMeshBoundingBox(mesh: Mesh) : BoundingBox {
    return GetMeshBoundingBox(mesh.readValue()).getPointer(MemScope()).pointed
}

/**
 * Compute mesh tangents
 */
inline fun genMeshTangents(mesh: Mesh) {
    GenMeshTangents(mesh.readValue())
}

//=======================================================//
// MESH GENERATION FUNCTIONS
//=======================================================//

/**
 * Generate polygonal mesh
 */
inline fun genMeshPoly(sides: Int, radius: Float) : Mesh {
    return GenMeshPoly(sides, radius).getPointer(MemScope()).pointed
}

/**
 * Generate plane mesh (with subdivisions)
 */
inline fun genMeshPlane(width: Float, length: Float, resX: Int, resZ: Int) : Mesh {
    return GenMeshPlane(width, length, resX, resZ).getPointer(MemScope()).pointed
}

/**
 * Generate cuboid mesh
 */
inline fun genMeshCube(width: Float, height: Float, length: Float) : Mesh {
    return GenMeshCube(width, height, length).getPointer(MemScope()).pointed
}

/**
 * Generate sphere mesh (standard sphere)
 */
inline fun genMeshSphere(radius: Float, rings: Int, slices: Int) : Mesh {
    return GenMeshSphere(radius, rings, slices).getPointer(MemScope()).pointed
}

/**
 * Generate half-sphere mesh (no bottom cap)
 */
inline fun genMeshHemiSphere(radius: Float, rings: Int, slices: Int) : Mesh {
    return GenMeshHemiSphere(radius, rings, slices).getPointer(MemScope()).pointed
}

/**
 * Generate cylinder mesh
 */
inline fun genMeshCylinder(radius: Float, height: Float, slices: Int) : Mesh {
    return GenMeshCylinder(radius, height, slices).getPointer(MemScope()).pointed
}

/**
 * Generate cone/pyramid mesh
 */
inline fun genMeshCone(radius: Float, height: Float, slices: Int) : Mesh {
    return GenMeshCone(radius, height, slices).getPointer(MemScope()).pointed
}

/**
 * Generate torus mesh
 */
inline fun genMeshTorus(radius: Float, size: Float, radSeg: Int, sides: Int) : Mesh {
    return GenMeshTorus(radius, size, radSeg, sides).getPointer(MemScope()).pointed
}

/**
 * Generate trefoil knot mesh
 */
inline fun genMeshKnot(radius: Float, size: Float, radSeg: Int, sides: Int) : Mesh {
    return GenMeshKnot(radius, size, radSeg, sides).getPointer(MemScope()).pointed
}

/**
 * Generate heightmap mesh from image data
 */
inline fun genMeshHeightmap(heightmap: Image, size: Vector3) : Mesh {
    return GenMeshHeightmap(heightmap.readValue(), size.readValue()).getPointer(MemScope()).pointed
}

/**
 * Generate cubes-based map mesh from image data
 */
inline fun genMeshCubicmap(cubicmap: Image, cubeSize: Vector3) : Mesh {
    return GenMeshCubicmap(cubicmap.readValue(), cubeSize.readValue()).getPointer(MemScope()).pointed
}

//=======================================================//
// MATERIAL LOADING/UNLOADING FUNCTIONS
//=======================================================//

/**
 * Load materials from model file
 */
inline fun loadMaterials(fileName: String, materialCount: IntVar){
    LoadMaterials(fileName, materialCount.ptr)
}

/**
 * Load default material (Supports: DIFFUSE, SPECULAR, NORMAL maps)
 */
inline fun loadMaterialDefault() : Material {
    return LoadMaterialDefault().getPointer(MemScope()).pointed
}

/**
 * Unload material from GPU memory (VRAM)
 */
inline fun unloadMaterial(material: Material) {
    UnloadMaterial(material.readValue())
}

/**
 * Set texture for a material map type (MATERIAL_MAP_DIFFUSE, MATERIAL_MAP_SPECULAR...)
 */
inline fun setMaterialTexture(material: Material, mapType: kaylib.kEnums.MaterialMapIndex, texture: Texture2D) {
    SetMaterialTexture(material.ptr, mapType.value, texture.readValue())
}

/**
 * Set material for a mesh
 */
inline fun setModelMeshMaterial(model: Model, meshId: Int, materialId: Int) {
    SetModelMeshMaterial(model.ptr, meshId, materialId)
}

//=======================================================//
// MODEL ANIMATION LOADING/UNLOADING FUNCTIONS
//=======================================================//

/**
 * Load model animations from file
 */
inline fun loadModelAnimations(fileName: String, animCount: UIntVar) {
    LoadModelAnimations(fileName, animCount.ptr)
}

/**
 * Update model animation pose
 */
inline fun updateModelAnimation(model: Model, anim: ModelAnimation, frame: Int) {
    UpdateModelAnimation(model.readValue(), anim.readValue(), frame)
}

/**
 * Unload animation data
 */
inline fun unloadModelAnimation(anim: ModelAnimation) {
    UnloadModelAnimation(anim.readValue())
}

/**
 * Unload animation array data
 */
inline fun unloadModelAnimations(animations: ModelAnimation) {
    UnloadModelAnimation(animations.readValue())
}

/**
 * Check model animation skeleton match
 */
inline fun isModelAnimationValid(model: Model, anim: ModelAnimation) : Boolean {
    return IsModelAnimationValid(model.readValue(), anim.readValue())
}

//=======================================================//
// COLLISION DETECTION FUNCTIONS
//=======================================================//

/**
 * Check collision between two spheres
 */
inline fun checkCollisionSpheres(center1: Vector3, radius1: Float, center2: Vector3, radius2: Float) : Boolean {
    return CheckCollisionSpheres(center1.readValue(), radius1, center2.readValue(), radius2)
}

/**
 * Check collision between two bounding boxes
 */
inline fun checkCollisionBoxes(box1: BoundingBox, box2: BoundingBox) : Boolean {
    return CheckCollisionBoxes(box1.readValue(), box2.readValue())
}

/**
 * Check collision between box and sphere
 */
inline fun checkCollisionBoxSphere(box: BoundingBox, center: Vector3, radius: Float) : Boolean {
    return CheckCollisionBoxSphere(box.readValue(), center.readValue(), radius)
}

/**
 * Get collision info between ray and sphere
 */
inline fun getRayCollisionSphere(ray: Ray, center: Vector3, radius: Float) : RayCollision {
    return GetRayCollisionSphere(ray.readValue(), center.readValue(), radius).getPointer(MemScope()).pointed
}

/**
 * Get collision info between ray and box
 */
inline fun getRayCollisionBox(ray: Ray, box: BoundingBox) : RayCollision {
    return GetRayCollisionBox(ray.readValue(), box.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get collision info between ray and mesh
 */
inline fun getRayCollisionMesh(ray: Ray, mesh: Mesh, transform: Matrix) : RayCollision {
    return GetRayCollisionMesh(ray.readValue(), mesh.readValue(), transform.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get collision info between ray and triangle
 */
inline fun getRayCollisionTriangle(ray: Ray, p1: Vector3, p2: Vector3, p3: Vector3) : RayCollision {
    return GetRayCollisionTriangle(ray.readValue(), p1.readValue(), p2.readValue(), p3.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get collision info between ray and quad
 */
inline fun getRayCollisionQuad(ray: Ray, p1: Vector3, p2: Vector3, p3: Vector3, p4: Vector3) : RayCollision {
    return GetRayCollisionQuad(ray.readValue(), p1.readValue(), p2.readValue(), p3.readValue(), p4.readValue()).getPointer(MemScope()).pointed
}