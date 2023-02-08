package kaylib.kTextures

import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kTextures

//=======================================================//
// TEXTURE LOADING FUNCTIONS
//=======================================================//

/**
 * Load texture from file into GPU memory (VRAM)
 */
inline fun loadTexture(fileName: String) : Texture2D {
    return LoadTexture(fileName).getPointer(MemScope()).pointed
}

/**
 * Load texture from image data
 */
inline fun loadTextureFromImage(image: Image) : Texture2D {
    return LoadTextureFromImage(image.readValue()).getPointer(MemScope()).pointed
}

/**
 * Load cubemap from image, multiple image cubemap layouts supported
 */
inline fun loadTextureCubemap(image: Image, layout: CubemapLayout) : TextureCubemap {
    return LoadTextureCubemap(image.readValue(), layout.toInt()).getPointer(MemScope()).pointed
}

/**
 * Load texture for rendering (framebuffer)
 */
inline fun loadRenderTexture(width: Int, height: Int) : RenderTexture2D {
    return LoadRenderTexture(width, height).getPointer(MemScope()).pointed
}

/**
 * Unload texture from GPU memory (VRAM)
 */
inline fun unloadTexture(texture: Texture2D) {
    return UnloadTexture(texture.readValue())
}

/**
 * Unload render texture from GPU memory (VRAM)
 */
inline fun unloadRenderTexture(target: RenderTexture2D) {
    return UnloadRenderTexture(target.readValue())
}

/**
 * Update GPU texture with new data
 */
inline fun updateTexture(texture: Texture2D, pixels: COpaquePointer) {
    UpdateTexture(texture.readValue(), pixels)
}

/**
 * Update GPU texture rectangle with new data
 */
inline fun updateTextureRec(texture: Texture2D, rec: Rectangle, pixels: COpaquePointer) {
    UpdateTextureRec(texture.readValue(), rec.readValue(), pixels)
}

//=======================================================//
// TEXTURE CONFIGURATION FUNCTIONS
//=======================================================//

/**
 * Generate GPU mipmaps for a texture
 */
inline fun genTextureMipmaps(texture: Texture2D) {
    GenTextureMipmaps(texture.ptr)
}

/**
 * Set texture scaling filter mode
 */
inline fun setTextureFilter(texture: Texture2D, filter: kaylib.kEnums.TextureFilter) {
    SetTextureFilter(texture.readValue(), filter.value)
}

/**
 * Set texture wrapping mode
 */
inline fun setTextureWrap(texture: Texture2D, wrap: kaylib.kEnums.TextureWrap) {
    SetTextureWrap(texture.readValue(), wrap.value)
}

//=======================================================//
// TEXTURE DRAWING FUNCTIONS
//=======================================================//

/**Draw a Texture2D
 *
 */
inline fun drawTexture(texture: Texture2D, posX: Int, posY: Int, tint: Color) {
    return DrawTexture(texture.readValue(), posX, posY, tint.readValue())
}

/**
 * Draw a Texture2D with position defined as Vector2
 */
inline fun drawTextureV(texture: Texture2D, position: Vector2, tint: Color) {
    DrawTextureV(texture.readValue(), position.readValue(), tint.readValue())
}

/**
 * Draw a Texture2D with extended parameters
 */
inline fun drawTextureEx(texture: Texture2D, position: Vector2, rotation: Float, scale: Float, tint: Color) {
    DrawTextureEx(texture.readValue(), position.readValue(), rotation, scale, tint.readValue())
}

/**
 * Draw a part of a texture defined by a rectangle
 */
inline fun drawTextureRec(texture: Texture2D, source: Rectangle, position: Vector2, tint: Color) {
    DrawTextureRec(texture.readValue(), source.readValue(), position.readValue(), tint.readValue())
}

/**
 * Draw a part of a texture defined by a rectangle with 'pro' parameters
 */
inline fun drawTexturePro(texture: Texture2D, source: Rectangle, dest: Rectangle, origin: Vector2, rotation: Float, tint: Color) {
    DrawTexturePro(texture.readValue(), source.readValue(), dest.readValue(), origin.readValue(), rotation, tint.readValue())
}

/**
 * Draws a texture (or part of it) that stretches or shrinks nicely
 */
inline fun drawTextureNPatch(texture: Texture2D, nPatchInfo: NPatchInfo, dest: Rectangle, origin: Vector2, rotation: Float, tint: Color) {
    DrawTextureNPatch(texture.readValue(), nPatchInfo.readValue(), dest.readValue(), origin.readValue(), rotation, tint.readValue())
}

/**
 * Set value of a Texture with another provided value of same type.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
inline fun Texture.set(other: Texture) {
    this.format = other.format
    this.height = other.height
    this.id = other.id
    this.mipmaps = other.mipmaps
    this.width = other.width
}