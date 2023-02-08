package kaylib.kText

import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kText

//=======================================================//
// FONT LOADING FUNCTIONS
//=======================================================//

/**
 * Get the default Font
 */
inline fun getFontDefault() : Font {
    return GetFontDefault().getPointer(MemScope()).pointed
}

/**
 * Load font from file into GPU memory (VRAM)
 */
inline fun loadFont(fileName: String) : Font {
    return LoadFont(fileName).getPointer(MemScope()).pointed
}

/**
 * Load font from file with extended parameters
 */
inline fun loadFont(fileName: String, fontSize: Int, fontChars: IntVar, glyphCount: Int) : Font {
    return LoadFontEx(fileName, fontSize, fontChars.ptr, glyphCount).getPointer(MemScope()).pointed
}

/**
 * Load font from Image (XNA style)
 */
inline fun loadFontFromImage(image: Image, key: Color, firstChar: Int) : Font {
    return LoadFontFromImage(image.readValue(), key.readValue(), firstChar).getPointer(MemScope()).pointed
}

/**
 * Load font from memory buffer, fileType refers to extension: i.e. ".ttf"
 */
inline fun loadFontFromMemory(fileType: String, fileData: UByteVar, dataSize: Int, fontSize: Int, fontChars: IntVar, glyphCount: Int) : Font {
    return LoadFontFromMemory(fileType, fileData.ptr, dataSize, fontSize, fontChars.ptr, glyphCount).getPointer(MemScope()).pointed
}

/**
 * Load font data for further use
 */
inline fun loadFontData(fileData: UByteVar, dataSize: Int, fontSize: Int, fontChars: IntVar, glyphCount: Int, type: kaylib.kEnums.FontType) : CPointer<GlyphInfo>? {
    return LoadFontData(fileData.ptr, dataSize, fontSize, fontChars.ptr, glyphCount, type.value)
}

/**
 * Generate image font atlas using chars info
 */
inline fun genImageFontAtlas(chars: GlyphInfo, recs: CPointerVar<Rectangle>, glyphCount: Int, fontSize: Int, padding: Int, packMethod: Int) : Image {
    return GenImageFontAtlas(chars.ptr, recs.ptr, glyphCount, fontSize, padding, packMethod).getPointer(MemScope()).pointed
}

/**
 * Unload font chars info data (RAM)
 */
inline fun unloadFontData(chars: GlyphInfo, glyphCount: Int) {
    UnloadFontData(chars.ptr, glyphCount)
}

/**
 * Unload Font from GPU memory (VRAM)
 */
inline fun unloadFont(font: Font) {
    UnloadFont(font.readValue())
}

/**
 * Export font as code file, returns true on success
 */
inline fun exportFontAsCode(font: Font, fileName: String) {
    ExportFontAsCode(font.readValue(), fileName)
}

//=======================================================//
// TEXT DRAWING FUNCTIONS
//=======================================================//

/**
 * Draw current FPS
 */
inline fun drawFPS(posX: Int, posY: Int) {
    DrawFPS(posX, posY)
}

/**
 * Draw current FPS
 */
inline fun drawText(text: String, posX: Int, posY: Int, fontSize: Int, color: Color) {
    DrawText(text, posX, posY, fontSize, color.readValue())
}

/**
 * Draw text using font and additional parameters
 */
inline fun drawText(font: Font, text: String, position: Vector2, fontSize: Float, spacing: Float, tint: Color) {
    DrawTextEx(font.readValue(), text, position.readValue(), fontSize, spacing, tint.readValue())
}

/**
 * Draw text using Font and pro parameters (rotation)
 */
inline fun drawText(font: Font, text: String, position: Vector2, origin: Vector2, rotation: Float, fontSize: Float, spacing: Float, tint: Color) {
    DrawTextPro(font.readValue(), text, position.readValue(), origin.readValue(), rotation, fontSize, spacing, tint.readValue())
}

/**
 * Draw one character (codepoint)
 */
inline fun drawText(font: Font, codepoint: Int, position: Vector2, fontSize: Float, tint: Color) {
    DrawTextCodepoint(font.readValue(), codepoint, position.readValue(), fontSize, tint.readValue())
}

/**
 * Draw one character (codepoint)
 */
inline fun drawText(font: Font, codepoint: IntVar, count: Int, position: Vector2, fontSize: Float, spacing: Float, tint: Color) {
    DrawTextCodepoints(font.readValue(), codepoint.ptr, count, position.readValue(), fontSize, spacing, tint.readValue())
}

//=======================================================//
// TEXT FONT INFO FUNCTIONS
//=======================================================//

/**
 * Measure string width for default font
 */
inline fun measureText(text: String, fontSize: Int) : Int {
    return MeasureText(text, fontSize)
}

/**
 * Measure string size for Font
 */
inline fun measureText(font: Font, text: String, fontSize: Float, spacing: Float) : Vector2 {
    return MeasureTextEx(font.readValue(), text, fontSize, spacing).getPointer(MemScope()).pointed
}

/**
 * Get index position for a unicode character on font
 */
inline fun getGlyphIndex(font: Font, codepoint: Int) : Int {
    return GetGlyphIndex(font.readValue(), codepoint)
}

/**
 * Get glyph font info data for a codepoint (unicode character), fallback to '?' if not found
 */
inline fun getGlyphInfo(font: Font, codepoint: Int) : GlyphInfo {
    return GetGlyphInfo(font.readValue(), codepoint).getPointer(MemScope()).pointed
}

/**
 * Get glyph rectangle in font atlas for a codepoint (unicode character), fallback to '?' if not found
 */
inline fun getGlyphAtlasRec(font: Font, codepoint: Int) : Rectangle {
    return GetGlyphAtlasRec(font.readValue(), codepoint).getPointer(MemScope()).pointed
}

//=======================================================//
// TEXT STRINGS MANAGEMENT FUNCTIONS
//=======================================================//

// Internal Note: There is no need to bind other related text strings management functions. Use existing Kotlin API.

/**
 * Get Pascal case notation version of provided string
 */
inline fun textToPascal(text: String) : String {
    return TextToPascal(text)?.toKString() ?: "WARNING: Unable to return requested string."

}
