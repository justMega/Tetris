package kaylib.kUtils

import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kUtils

//=======================================================//
// Raylib Utility Functions - Don't belong anywhere else
//=======================================================//

/**
 * Load UTF-8 text encoded from codepoints array
 */
inline fun loadUTF8(codepoints: IntVar, length: Int) {
    LoadUTF8(codepoints.ptr, length)
}

/**
 * Unload UTF-8 text encoded from codepoints array
 */
inline fun unloadUTF8(text: String) {
    UnloadUTF8(text.cstr)
}

/**
 * Unload codepoints data from memory
 */
inline fun unloadCodepoints(codepoints: IntVar) {
    UnloadCodepoints(codepoints.ptr)
}

/**
 * Get next codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
 */
inline fun getCodepoint(text: String, codepointSize: IntVar) {
    GetCodepoint(text, codepointSize.ptr)
}

/**
 * Get next codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
 */
inline fun getCodepointNext(text: String, codepointSize: IntVar) {
    GetCodepointNext(text, codepointSize.ptr)
}

/**
 * Get previous codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
 */
inline fun getCodepointPrevious(text: String, codepointSize: IntVar) {
    GetCodepointPrevious(text, codepointSize.ptr)
}

/**
 * Get previous codepoint in a UTF-8 encoded string, 0x3f('?') is returned on failure
 */
inline fun codepointToUTF8(codepoint: Int, utf8Size: IntVar) {
    CodepointToUTF8(codepoint, utf8Size.ptr)
}

//=======================================================//
// COLOR/PIXEL FUNCTIONS
//=======================================================//

/**
 * Get color with alpha applied, alpha goes from 0.0f to 1.0f
 */
inline fun fade(color: Color, alpha: Float) : Color {
    return Fade(color.readValue(), alpha).getPointer(MemScope()).pointed
}

/**
 * Get hexadecimal value for a Color
 */
inline fun colorToInt(color: Color) : Int {
    return ColorToInt(color.readValue())
}

/**
 * Get Color normalized as float [0..1]
 */
inline fun colorNormalize(color: Color) : Vector4 {
    return ColorNormalize(color.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get Color from normalized values [0..1]
 */
inline fun colorFromNormalized(normalized: Vector4) : Color {
    return ColorFromNormalized(normalized.readValue()) .getPointer(MemScope()).pointed
}

/**
 * Get HSV values for a Color, hue [0..360], saturation/value [0..1]
 */
inline fun colorToHSV(color: Color) : Vector3 {
    return ColorToHSV(color.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get a Color from HSV values, hue [0..360], saturation/value [0..1]
 */
inline fun colorFromHSV(hue: Float, saturation: Float, value: Float) : Color {
    return ColorFromHSV(hue, saturation, value).getPointer(MemScope()).pointed
}

/**
 * Get color with alpha applied, alpha goes from 0.0f to 1.0f
 */
inline fun colorAlpha(color: Color, alpha: Float) : Color {
    return ColorAlpha(color.readValue(), alpha).getPointer(MemScope()).pointed
}

/**
 * Get src alpha-blended into dst color with tint
 */
inline fun colorAlphaBlend(dst: Color, src: Color, tint: Color) : Color {
    return ColorAlphaBlend(dst.readValue(), src.readValue(), tint.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get Color structure from hexadecimal value
 */
inline fun getColor(hexValue: UInt) : Color {
    return GetColor(hexValue).getPointer(MemScope()).pointed
}

/**
 * Get Color from a source pixel pointer of certain format
 */
inline fun getPixelColor(srcPtr: COpaquePointer, format: kaylib.kEnums.PixelFormat) : Color {
    return GetPixelColor(srcPtr, format.value).getPointer(MemScope()).pointed
}

/**
 * Set color formatted into destination pixel pointer
 */
inline fun setPixelColor(dstPtr: COpaquePointer, color: Color, format: kaylib.kEnums.PixelFormat) {
    return SetPixelColor(dstPtr, color.readValue(), format.value)
}

/**
 * Get pixel data size in bytes for certain format
 */
inline fun getPixelDataSize(width: Int, height: Int, format: kaylib.kEnums.PixelFormat) : Int {
    return GetPixelDataSize(width, height, format.value)

}