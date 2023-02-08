package kaylib.kTypes

import kaylib.kMath.kVector2
import kaylib.kMath.kVector3
import kaylib.kMath.set
import kaylib.kShapes.set
import kaylib.kImage.set
import kaylib.kTextures.set
import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kTypes

//=======================================================//
// Type Constructors
//=======================================================//


/**
 * Constructor function for Color.
 * Important to note that this uses `MemScope()` by default.
 * @return [Color]
 */
inline fun kColor(r: UByte = 0U, g: UByte = 0U, b: UByte = 0U, a: UByte = 0U, allocator: AutofreeScope = MemScope()) : Color {
    return allocator.alloc<Color> {
        this.r = r
        this.g = g
        this.b = b
        this.a = a
    }
}

/**
 * Set value of a Color with another provided value of same type.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
inline fun Color.set(other: Color) {
    this.r = other.r
    this.g = other.g
    this.b = other.b
    this.a = other.a
}

/**
 * Constructor function for Texture.
 * Important to note that this uses `MemScope()` by default.
 * @return [Texture]
 */
inline fun kTexture(id: UInt, width:Int, height: Int, mipmaps: Int, format: Int, allocator: AutofreeScope = MemScope()) : Texture {
    return allocator.alloc<Texture> {
        this.id = id
        this.width = width
        this.height = height
        this.mipmaps = mipmaps
        this.format = format
    }
}

/**
 * Constructor function for RenderTexture.
 * Important to note that this uses `MemScope()` by default.
 * @return [RenderTexture]
 */
inline fun kRenderTexture(id: UInt, texture: Texture, depth: Texture, allocator: AutofreeScope = MemScope()) : RenderTexture {
    return allocator.alloc<RenderTexture> {
        this.id = id
        this.texture.set(texture)
        this.depth.set(depth)
    }
}

/**
 * Constructor function for NPatchInfo.
 * Important to note that this uses `MemScope()` by default.
 * @return [NPatchInfo]
 */
inline fun kNPatchInfo(source: Rectangle, left: Int, top: Int, right: Int, bottom: Int, layout: Int, allocator: AutofreeScope = MemScope()) : NPatchInfo {
    return allocator.alloc<NPatchInfo> {
        this.source.set(source)
        this.left = left
        this.top = top
        this.right = right
        this.bottom = bottom
        this.layout = layout
    }
}

/**
 * Constructor function for GlyphInfo.
 * Important to note that this uses `MemScope()` by default.
 * @return [GlyphInfo]
 */
inline fun kGlyphInfo(value: Int, offsetX: Int, offsetY: Int, advanceX: Int, image: Image, allocator: AutofreeScope = MemScope()) : GlyphInfo {
    return allocator.alloc<GlyphInfo> {
        this.value = value
        this.offsetX = offsetX
        this.offsetY = offsetY
        this.advanceX = advanceX
        this.image.set(image)

    }
}

/**
 * Constructor function for Font.
 * Important to note that this uses `MemScope()` by default.
 * @return [Font]
 */
inline fun kFont(baseSize: Int, glyphCount: Int, glyphPadding: Int, texture: Texture, recs: Rectangle, glyphs: GlyphInfo, allocator: AutofreeScope = MemScope()) : Font {
    return allocator.alloc<Font> {
        this.baseSize = baseSize
        this.glyphCount = glyphCount
        this.glyphPadding = glyphPadding
        this.texture.set(texture)
        this.recs = recs.ptr
        this.glyphs = glyphs.ptr
    }
}

/**
 * Constructor function for Camera3D.
 * Important to note that this uses `MemScope()` by default.
 * @return [Camera3D]
 */
inline fun kCamera3D(position: Vector3 = kVector3(), target: Vector3 = kVector3(), up: Vector3 = kVector3(), fovy: Float = 0F, projection: kaylib.kEnums.CameraProjection, allocator: AutofreeScope = MemScope()) : Camera3D {
    return allocator.alloc<Camera3D> {
        this.position.set(position)
        this.target.set(target)
        this.up.set(up)
        this.fovy = fovy
        this.projection = projection.value
    }
}

/**
 * Constructor function for Camera2D.
 * Important to note that this uses `MemScope()` by default.
 * @return [Camera2D]
 */
inline fun kCamera2D(offset: Vector2 = kVector2(), target: Vector2 = kVector2(), rotation: Float = 0F, zoom: Float = 0F, allocator: AutofreeScope = MemScope()) : Camera2D {
    return allocator.alloc<Camera2D> {
        this.offset.set(offset)
        this.target.set(target)
        this.rotation = rotation
        this.zoom = zoom
    }
}

/**
 * Constructor function for Mesh.
 * Important to note that this uses `MemScope()` by default.
 * @return [Mesh]
 */
inline fun kMesh(vertexCount: Int, triangleCount: Int, vertices: FloatVar, texcoords: FloatVar, texcoords2: FloatVar, normals: FloatVar, tangents: FloatVar, colors: UByteVar, indices: UShortVar, animVertices: FloatVar, animNormals: FloatVar, boneIds: UByteVar, boneWeights: FloatVar, vaoId: UInt, vboId: UIntVar , allocator: AutofreeScope = MemScope()) : Mesh {
    return allocator.alloc<Mesh> {
        this.vertexCount = vertexCount
        this.triangleCount = triangleCount
        this.vertices = vertices.ptr
        this.texcoords = texcoords.ptr
        this.texcoords2 = texcoords2.ptr
        this.normals = normals.ptr
        this.tangents = tangents.ptr
        this.colors = colors.ptr
        this.indices = indices.ptr
        this.animVertices = animVertices.ptr
        this.animNormals = animNormals.ptr
        this.boneIds = boneIds.ptr
        this.boneWeights = boneWeights.ptr
        this.vaoId = vaoId
        this.vboId = vboId.ptr
    }
}

/**
 * Set value of a Color with another provided value of same type.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
inline fun Mesh.set(other: Mesh) {
    this.vertexCount = other.vertexCount
    this.triangleCount = other.triangleCount
    this.vertices = other.vertices
    this.texcoords = other.texcoords
    this.texcoords2 = other.texcoords2
    this.normals = other.normals
    this.tangents = other.tangents
    this.colors = other.colors
    this.indices = other.indices
    this.animNormals = other.animNormals
    this.animVertices = other.animVertices
    this.boneIds = other.boneIds
    this.boneWeights = other.boneWeights
    this.vaoId = other.vaoId
    this.vboId = other.vboId
}

/**
 * Constructor function for Shader.
 * Important to note that this uses `MemScope(`) by default.
 * @return [Shader]
 */
inline fun kShader(id: UInt, locs: IntVar, allocator: AutofreeScope = MemScope()) : Shader {
    return allocator.alloc<Shader> {
        this.id = id
        this.locs = locs.ptr
    }
}

/**
 * Set value of a Shader with another provided value of same type.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
inline fun Shader.set(other: Shader) {
    this.id = other.id
    this.locs = other.locs
}

/**
 * Constructor function for MaterialMap.
 * Important to note that this uses `MemScope()` by default.
 * @return [MaterialMap]
 */
inline fun kMaterialMap(texture: Texture2D, color: Color, value: Float, allocator: AutofreeScope = MemScope()) : MaterialMap {
    return allocator.alloc<MaterialMap> {
        this.texture.set(texture)
        this.color.set(color)
        this.value = value
    }
}

/**
 * Constructor function for Material.
 * Important to note that this uses `MemScope()` by default.
 * @return [Material]
 */
inline fun kMaterial(shader: Shader, maps: MaterialMap, params: CArrayPointer<FloatVar>, allocator: AutofreeScope = MemScope()) : Material {
    return MaterialConstructor(shader.readValue(), maps.ptr, params).getPointer(allocator).pointed
}

/**
 * Constructor function for Transform.
 * Important to note that this uses `MemScope()` by default.
 * @return [Transform]
 */
inline fun kTransform(translation: Vector3, rotation: Quaternion, scale: Vector3, allocator: AutofreeScope = MemScope()) : Transform {
    return allocator.alloc<Transform> {
        this.translation.set(translation)
        this.rotation.set(rotation)
        this.scale.set(scale)
    }
}

/**
 * Constructor function for BoneInfo.
 * Important to note that this uses `MemScope()` by default.
 * @return [BoneInfo]
 */
inline fun kBoneInfo(name: CArrayPointer<ByteVar>, parent: Int, allocator: AutofreeScope = MemScope()) : BoneInfo {
    return BoneInfoConstructor(name, parent).getPointer(allocator).pointed
}

/**
 * Constructor function for Model.
 * Important to note that this uses `MemScope()` by default.
 * @return [Model]
 */
inline fun kModel(transform: Matrix, meshCount: Int, materialCount: Int, meshes: Mesh, materials: Material, meshMaterial: IntVar, boneCount: Int, bones: BoneInfo, bindPose: Transform, allocator: AutofreeScope = MemScope()) : Model {
    return allocator.alloc<Model> {
        this.transform.set(transform)
        this.meshCount = meshCount
        this.materialCount = materialCount
        this.meshes = meshes.ptr
        this.materials = materials.ptr
        this.meshMaterial = meshMaterial.ptr
        this.boneCount = boneCount
        this.bones = bones.ptr
        this.bindPose = bindPose.ptr
    }
}

/**
 * Constructor function for ModelAnimation.
 * Important to note that this uses `MemScope()` by default.
 * @return [ModelAnimation]
 */
inline fun kModelAnimation(boneCount: Int, frameCount: Int, bones: BoneInfo, framePoses: CPointer<CPointerVar<Transform>>, allocator: AutofreeScope = MemScope()) : ModelAnimation {
    return allocator.alloc<ModelAnimation> {
        this.boneCount = boneCount
        this.frameCount = frameCount
        this.bones = bones.ptr
        this.framePoses = framePoses
    }
}

/**
 * Constructor function for Ray.
 * Important to note that this uses `MemScope()` by default.
 * @return [Ray]
 */
inline fun kRay(position: Vector3 = kVector3(), direction: Vector3 = kVector3(), allocator: AutofreeScope = MemScope()) : Ray {
    return allocator.alloc<Ray> {
        this.position.set(position)
        this.direction.set(direction)
    }
}

/**
 * Constructor function for RayCollision.
 * Important to note that this uses `MemScope()` by default.
 * @return [RayCollision]
 */
inline fun kRayCollision(hit: Boolean = false, distance: Float = 0F, point: Vector3 = kVector3(), normal: Vector3 = kVector3(), allocator: AutofreeScope = MemScope()) : RayCollision {
    return allocator.alloc<RayCollision> {
        this.hit = hit
        this.distance = distance
        this.point.set(point)
        this.normal.set(normal)
    }
}

/**
 * Constructor function for BoundingBox.
 * Important to note that this uses `MemScope()` by default.
 * @return [BoundingBox]
 */
inline fun kBoundingBox(min: Vector3 = kVector3(), max: Vector3 = kVector3(), allocator: AutofreeScope = MemScope()) : BoundingBox {
    return allocator.alloc<BoundingBox> {
        this.min.set(min)
        this.max.set(max)
    }
}

/**
 * Constructor function for Wave.
 * Important to note that this uses `MemScope()` by default.
 * @return [Wave]
 */
inline fun kWave(frameCount: UInt, sampleRate: UInt, sampleSize: UInt, channels: UInt, data: COpaquePointer?, allocator: AutofreeScope = MemScope()) : Wave {
    return allocator.alloc<Wave> {
        this.frameCount = frameCount
        this.sampleRate = sampleRate
        this.sampleSize = sampleSize
        this.channels = channels
        this.data = data
    }
}

/**
 * Constructor function for AudioStream.
 * Important to note that this uses `MemScope()` by default.
 * @return [AudioStream]
 */
inline fun kAudioStream(buffer: rAudioBuffer, processor: rAudioProcessor, sampleRate: UInt, sampleSize: UInt, channels: UInt, allocator: AutofreeScope = MemScope()) : AudioStream {
    return allocator.alloc<AudioStream> {
        this.buffer = buffer.ptr
        this.processor = processor.ptr
        this.sampleRate = sampleRate
        this.sampleSize = sampleSize
        this.channels = channels
    }
}

/**
 * Constructor function for Sound.
 * Important to note that this uses `MemScope()` by default.
 * @return [Sound]
 */
inline fun kSound(stream: AudioStream, frameCount: UInt, allocator: AutofreeScope = MemScope()) : Sound {
    return SoundConstructor(stream.readValue(), frameCount).getPointer(allocator).pointed
}

/**
 * Constructor function for Music.
 * Important to note that this uses `MemScope()` by default.
 * @return [Music]
 */
inline fun kMusic(stream: AudioStream, frameCount: UInt, looping: Boolean, ctxType: Int, data: COpaquePointer?, allocator: AutofreeScope = MemScope()) : Music {
    return MusicConstructor(stream.readValue(), frameCount, looping, ctxType, data).getPointer(allocator).pointed
}

/**
 * Constructor function for VrDeviceInfo.
 * Important to note that this uses `MemScope()` by default.
 * @return [VrDeviceInfo]
 */
inline fun kVrDeviceInfo(hResolution: Int, vResolution: Int, hScreenSize: Float, vScreenSize: Float, vScreenCenter: Float, eyeToScreenDistance: Float, lensSeparationDistance: Float, interpupillaryDistance: Float, lensDistortionValues: CArrayPointer<FloatVar>, chromaAbCorrection: CArrayPointer<FloatVar>, allocator: AutofreeScope = MemScope()) : VrDeviceInfo {
    return VrDeviceInfoConstructor(hResolution, vResolution, hScreenSize, vScreenSize, vScreenCenter, eyeToScreenDistance, lensSeparationDistance, interpupillaryDistance, lensDistortionValues, chromaAbCorrection).getPointer(allocator).pointed
}

/**
 * Constructor function for VrStereoConfig.
 * Important to note that this uses `MemScope()` by default.
 * @return [VrStereoConfig]
 */
inline fun kVrStereoConfig(projection: CArrayPointer<Matrix>, viewOffset: CArrayPointer<Matrix>, leftLensCenter: CArrayPointer<FloatVar>, rightLensCenter: CArrayPointer<FloatVar>, leftScreenCenter: CArrayPointer<FloatVar>, rightScreenCenter: CArrayPointer<FloatVar>, scale: CArrayPointer<FloatVar>, scaleIn: CArrayPointer<FloatVar>, allocator: AutofreeScope = MemScope()) : VrStereoConfig {
    return VrStereoConfigConstructor(projection, viewOffset, leftLensCenter, rightLensCenter, leftScreenCenter, rightScreenCenter, scale, scaleIn).getPointer(allocator).pointed
}

/**
 * Constructor function for FilePathList.
 * Important to note that this uses `MemScope()` by default.
 * @return [FilePathList]
 */
inline fun kFilePathList(capacity: UInt, count: UInt, paths: CPointer<CPointerVar<ByteVar>>, allocator: AutofreeScope = MemScope()) : FilePathList {
    return allocator.alloc<FilePathList> {
        this.capacity = capacity
        this.count = count
        this.paths = paths
    }
}
