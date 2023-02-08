package kaylib.kImage

import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kImage

//=======================================================//
// IMAGE CONSTRUCTOR
//=======================================================//

/**
 * Constructor function for Image.
 * Important to note that this uses MemScope() by default.
 * @return Image
 */
inline fun kImage(data: COpaquePointer?, width: Int, height: Int, mipmaps: Int, format: Int, allocator: AutofreeScope = MemScope()) : Image {
    return allocator.alloc<Image> {
        this.data = data
        this.width = width
        this.height = height
        this.mipmaps = mipmaps
        this.format = format
    }
}

//=======================================================//
// IMAGE LOADING FUNCTIONS
//=======================================================//

/**
 * Load image from file into CPU memory (RAM)
 */
inline fun loadImage(fileName: String) : Image {
    return LoadImage(fileName).getPointer(MemScope()).pointed
}

/**
 * Load image from RAW file data
 */
inline fun loadImageRaw(fileName: String, width: Int, height: Int, format: PixelFormat, headerSize: Int) : Image {
    return LoadImageRaw(fileName, width, height, format.toInt(), headerSize).getPointer(MemScope()).pointed
}

/**
 * Load image sequence from file (frames appended to image.data)
 */
inline fun loadImageAnim(fileName: String, frames: IntVar) : Image {
    return LoadImageAnim(fileName, frames.ptr).getPointer(MemScope()).pointed
}

/**
 * Load image from memory buffer, fileType refers to extension: i.e. `.png`
 */
inline fun loadImageFromMemory(fileType: String, fileData: UByteVar, dataSize: Int) : Image {
    return LoadImageFromMemory(fileType, fileData.ptr, dataSize).getPointer(MemScope()).pointed
}

/**
 * Load image from GPU texture data
 */
inline fun loadImageFromTexture(texture: Texture2D) : Image {
    return LoadImageFromTexture(texture.readValue()).getPointer(MemScope()).pointed
}

/**
 * Load image from screen buffer and (screenshot)
 */
inline fun loadImageFromScreen() : Image {
    return LoadImageFromScreen().getPointer(MemScope()).pointed
}

/**
 * Unload image from CPU memory (RAM)
 */
inline fun unloadImage(image: Image) {
    UnloadImage(image.readValue())
}

/**
 * Export image data to file, returns true on success
 */
inline fun exportImage(image: Image, fileName: String) : Boolean {
    return ExportImage(image.readValue(), fileName)
}

/**
 * Export image as code file defining an array of bytes, returns true on success
 */
inline fun exportImageAsCode(image: Image, fileName: String) : Boolean {
    return ExportImageAsCode(image.readValue(), fileName)
}

//=======================================================//
// IMAGE GENERATION FUNCTIONS
//=======================================================//

/**
 * Generate image: plain color
 */
inline fun genImageColor(width: Int, height: Int, color: Color) : Image {
    return GenImageColor(width, height, color.readValue()).getPointer(MemScope()).pointed
}

/**
 * Generate image: vertical gradient
 */
inline fun genImageGradientV(width: Int, height: Int, top: Color, bottom: Color) : Image {
    return GenImageGradientV(width, height, top.readValue(), bottom.readValue()).getPointer(MemScope()).pointed
}

/**
 * Generate image: horizontal gradient
 */
inline fun genImageGradientH(width: Int, height: Int, left: Color, right: Color) : Image {
    return GenImageGradientH(width, height, left.readValue(), right.readValue()).getPointer(MemScope()).pointed
}

/**
 * Generate image: radial gradient
 */
inline fun genImageGradientRadial(width: Int, height: Int, density: Float, inner: Color, outer: Color) : Image {
    return GenImageGradientRadial(width, height, density, inner.readValue(), outer.readValue()).getPointer(MemScope()).pointed
}

/**
 * Generate image: checked
 */
inline fun genImageChecked(width: Int, height: Int, checksX: Int, checksY: Int, col1: Color, col2: Color) : Image {
    return GenImageChecked(width, height, checksX, checksY, col1.readValue(), col2.readValue()).getPointer(MemScope()).pointed
}

/**
 * Generate image: white noise
 */
inline fun genImageWhiteNoise(width: Int, height: Int, factor: Float) : Image {
    return GenImageWhiteNoise(width, height, factor).getPointer(MemScope()).pointed
}

/**
 * Generate image: cellular algorithm, bigger tileSize means bigger cells
 */
inline fun genImageCellular(width: Int, height: Int, tileSize: Int) : Image {
    return GenImageCellular(width, height, tileSize).getPointer(MemScope()).pointed
}

//=======================================================//
// IMAGE MANIPULATION FUNCTIONS
//=======================================================//

/**
 * Create an image duplicate (useful for transformations)
 */
inline fun imageCopy(image: Image) : Image {
    return ImageCopy(image.readValue()).getPointer(MemScope()).pointed
}

/**
 * Create an image from another image piece
 */
inline fun imageFromImage(image: Image, rec: Rectangle) : Image {
    return ImageFromImage(image.readValue(), rec.readValue()).getPointer(MemScope()).pointed
}

/**
 *  Create an image from text (default font)
 */
inline fun imageText(text: String, fontSize: Int, color: Color) : Image {
    return ImageText(text, fontSize, color.readValue()).getPointer(MemScope()).pointed
}

/**
 * Create an image from text (custom sprite font)
 */
inline fun imageTextEx(font: Font, text: String, fontSize: Float, spacing: Float, tint: Color) : Image {
    return ImageTextEx(font.readValue(), text, fontSize, spacing, tint.readValue()).getPointer(MemScope()).pointed
}

/**
 * Convert image data to desired format
 */
inline fun imageFormat(image: Image, newFormat: kaylib.kEnums.PixelFormat) {
    ImageFormat(image.ptr, newFormat.value)
}

/**
 * Convert image to POT (power-of-two)
 */
inline fun imageToPOT(image: Image, fill: Color) {
    ImageToPOT(image.ptr, fill.readValue())
}

/**
 * Crop an image to a defined rectangle
 */
inline fun imageCrop(image: Image, crop: Rectangle) {
    ImageCrop(image.ptr, crop.readValue())
}

/**
 * Crop image depending on alpha value
 */
inline fun imageAlphaCrop(image: Image, threshold: Float) {
    ImageAlphaCrop(image.ptr, threshold)
}

/**
 * Clear alpha channel to desired color
 */
inline fun imageAlphaClear(image: Image, color: Color, threshold: Float) {
    ImageAlphaClear(image.ptr, color.readValue(), threshold)
}

/**
 * Apply alpha mask to image
 */
inline fun imageAlphaMask(image: Image, alphaMask: Image) {
    ImageAlphaMask(image.ptr, alphaMask.readValue())
}

/**
 * Premultiply alpha channel
 */
inline fun imageAlphaPremultiply(image: Image) {
    ImageAlphaPremultiply(image.ptr)
}

/**
 * Apply Gaussian blur using a box blur approximation
 */
inline fun imageBlurGaussian(image: Image, blurSize: Int) {
    ImageBlurGaussian(image.ptr, blurSize)
}

/**
 * Resize image (Bicubic scaling algorithm)
 */
inline fun imageResize(image: Image, newWidth: Int, newHeight: Int) {
    ImageResize(image.ptr, newWidth, newHeight)
}

/**
 * Resize image (Nearest-Neighbor scaling algorithm)
 */
inline fun imageResizeNN(image: Image, newWidth: Int, newHeight: Int) {
    ImageResizeNN(image.ptr, newWidth, newHeight)
}

/**
 * Resize canvas and fill with color
 */
inline fun imageResizeCanvas(image: Image, newWidth: Int, newHeight: Int, offsetX: Int, offsetY: Int, fill: Color) {
    ImageResizeCanvas(image.ptr, newWidth, newHeight, offsetX, offsetY, fill.readValue())
}

/**
 * Compute all mipmap levels for a provided image
 */
inline fun imageMipmaps(image: Image) {
    ImageMipmaps(image.ptr)
}

/**
 * Dither image data to 16bpp or lower (Floyd-Steinberg dithering)
 */
inline fun imageDither(image: Image, rBpp: Int, gBpp: Int, bBpp: Int, aBpp: Int) {
    ImageDither(image.ptr, rBpp, gBpp, bBpp, aBpp)
}

/**
 * Flip image vertically
 */
inline fun imageFlipVertical(image: Image) {
    ImageFlipVertical(image.ptr)
}

/**
 * Flip image horizontally
 */
inline fun imageFlipHorizontal(image: Image) {
    ImageFlipHorizontal(image.ptr)
}

/**
 * Rotate image clockwise 90deg
 */
inline fun imageRotateCW(image: Image) {
    ImageRotateCW(image.ptr)
}

/**
 * Rotate image counter-clockwise 90deg
 */
inline fun imageRotateCCW(image: Image) {
    ImageRotateCCW(image.ptr)
}

/**
 * Modify image color: tint
 */
inline fun imageColorTint(image: Image, color: Color) {
    ImageColorTint(image.ptr, color.readValue())
}

/**
 * Modify image color: invert
 */
inline fun imageColorInvert(image: Image) {
    ImageColorInvert(image.ptr)
}

/**
 * Modify image color: grayscale
 */
inline fun imageColorGrayscale(image: Image) {
    ImageColorGrayscale(image.ptr)
}

/**
 * Modify image color: contrast (-100 to 100)
 */
inline fun imageColorContrast(image: Image, contrast: Float) {
    ImageColorContrast(image.ptr, contrast)
}

/**
 * Modify image color: brightness (-255 to 255)
 */
inline fun imageColorBrightness(image: Image, brightness: Int) {
    ImageColorBrightness(image.ptr, brightness)
}

/**
 * Modify image color: replace color
 */
inline fun imageColorReplace(image: Image, color: Color, replace: Color) {
    ImageColorReplace(image.ptr, color.readValue(), replace.readValue())
}

/**
 * Load color data from image as a Color array (RGBA - 32bit) Memory allocated should be freed
 */
inline fun loadImageColors(image: Image): Array<Color> {
    val colorPointer = LoadImageColors(image.readValue())
    val colorList = mutableListOf<Color>()
    colorPointer?.pointed?.let { colorList.add(it) }
    return colorList.toTypedArray()
}

/**
 * Load colors palette from image as a Color array (RGBA - 32bit)
 */
inline fun loadImagePalette(image: Image, maxPaletteSize: Int, colorCount: IntVar) : Array<Color> {
    val result = LoadImagePalette(image.readValue(), maxPaletteSize, colorCount.ptr)
    val colorList = mutableListOf<Color>()
    result?.pointed?.let { colorList.add(it) }
    return colorList.toTypedArray()
}

/**
 * Unload color data loaded with LoadImageColors() Read Deprecated note
 */
inline fun unloadImageColors(colors: Color) {
    UnloadImageColors(colors.ptr)
}


/**
 * Unload colors palette loaded with LoadImagePalette() Read Deprecated note
 */
inline fun unloadImagePalette(colors: Color) {
    UnloadImagePalette(colors.ptr)
}

/**
 * Get image alpha border rectangle
 */
inline fun getImageAlphaBorder(image: Image, threshold: Float) : Rectangle {
    return GetImageAlphaBorder(image.readValue(), threshold).getPointer(MemScope()).pointed
}

/**
 * Get image pixel color at (x, y) position
 */
inline fun getImageColor(image: Image, x: Int, y: Int) : Color {
    return GetImageColor(image.readValue(), x, y).getPointer(MemScope()).pointed
}

//=======================================================//
// IMAGE DRAWING FUNCTIONS
//=======================================================//

/**
 * Clear image background with given color
 */
inline fun imageClearBackground(dst: Image, color: Color) {
    ImageClearBackground(dst.ptr, color.readValue())
}

/**
 * Draw pixel within an image
 */
inline fun imageDrawPixel(dst: Image, posX: Int, posY: Int, color: Color) {
    ImageDrawPixel(dst.ptr, posX, posY, color.readValue())
}

/// Draw pixel within an image (Vector version)
inline fun imageDrawPixelV(dst: Image, position: Vector2, color: Color) {
    ImageDrawPixelV(dst.ptr, position.readValue(), color.readValue())
}

/**
 * Draw line within an image
 */
inline fun imageDrawLine(dst: Image, startPosX: Int, startPosY: Int, endPosX: Int, endPosY: Int, color: Color) {
    ImageDrawLine(dst.ptr, startPosX, startPosY, endPosX, endPosY, color.readValue())
}

/**
 * Draw line within an image (Vector version)
 */
inline fun imageDrawLineV(dst: Image, start: Vector2, end: Vector2, color: Color) {
    ImageDrawLineV(dst.ptr, start.readValue(), end.readValue(), color.readValue())
}

/**
 * Draw circle within an image
 */
inline fun imageDrawCircle(dst: Image, centerX: Int, centerY: Int, radius: Int, color: Color) {
    ImageDrawCircle(dst.ptr, centerX, centerY, radius, color.readValue())
}

/**
 * Draw circle within an image (Vector version)
 */
inline fun imageDrawCircleV(dst: Image, center: Vector2, radius: Int, color: Color) {
    ImageDrawCircleV(dst.ptr, center.readValue(), radius, color.readValue())
}

/**
 * Draw rectangle within an image
 */
inline fun imageDrawRectangle(dst: Image, posX: Int, posY: Int, width: Int, height: Int, color: Color) {
    ImageDrawRectangle(dst.ptr, posX, posY, width, height, color.readValue())
}

/**
 * Draw rectangle within an image (Vector version)
 */
inline fun imageDrawRectangleV(dst: Image, position: Vector2, size: Vector2, color: Color) {
    ImageDrawRectangleV(dst.ptr, position.readValue(), size.readValue(), color.readValue())
}

/**
 * Draw rectangle within an image
 */
inline fun imageDrawRectangleRec(dst: Image, rec: Rectangle, color: Color) {
    ImageDrawRectangleRec(dst.ptr, rec.readValue(), color.readValue())
}

/**
 * Draw rectangle lines within an image
 */
inline fun imageDrawRectangleLines(dst: Image, rec: Rectangle, thick: Int, color: Color) {
    ImageDrawRectangleLines(dst.ptr, rec.readValue(), thick, color.readValue())
}

/**
 * Draw a source image within a destination image (tint applied to source)
 */
inline fun imageDraw(dst: Image, src: Image, srcRec: Rectangle, dstRec: Rectangle, tint: Color) {
    ImageDraw(dst.ptr, src.readValue(), srcRec.readValue(), dstRec.readValue(), tint.readValue())
}

/**
 * Draw text (using default font) within an image (destination)
 */
inline fun imageDrawText(dst: Image, text: String, posX: Int, posY: Int, fontSize: Int, color: Color) {
    return ImageDrawText(dst.ptr, text, posX, posY, fontSize, color.readValue())
}

/**
 * Draw text (custom sprite font) within an image (destination)
 */
inline fun imageDrawText(dst: Image, font: Font, text: String, position: Vector2, fontSize: Float, spacing: Float, tint: Color) {
    return ImageDrawTextEx(dst.ptr, font.readValue(), text, position.readValue(), fontSize, spacing, tint.readValue())
}

/**
 * Set value of an Image with another provided value of same type.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
inline fun Image.set(other: Image) {
    this.format = other.format
    this.height = other.height
    this.data = other.data
    this.mipmaps = other.mipmaps
    this.width = other.width
}