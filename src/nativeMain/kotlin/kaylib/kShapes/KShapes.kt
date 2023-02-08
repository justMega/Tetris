package kaylib.kShapes

import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kShapes

//=======================================================//
// BASIC SHAPES DRAWING FUNCTIONS
//=======================================================//

/**
 * Set texture and rectangle to be used on shapes drawing
 */
inline fun setShapesTexture(texture: Texture2D, source: Rectangle) {
    SetShapesTexture(texture.readValue(), source.readValue())
}

/**
 * Draw a pixel
 */
inline fun drawPixel(posX: Int, posY: Int, color: Color) {
    DrawPixel(posX, posY, color.readValue())
}

/**
 * Draw a pixel (Vector version)
 */
inline fun drawPixel(position: Vector2, color: Color) {
    DrawPixelV(position.readValue(), color.readValue())
}

/**
 * Draw a line
 */
inline fun drawLine(startPosX: Int, startPosY: Int, endPosX: Int, endPosY: Int, color: Color) {
    DrawLine(startPosX, startPosY, endPosX, endPosY, color.readValue())
}

/**
 * Draw a line (Vector version)
 */
inline fun drawLine(startPos: Vector2, endPos: Vector2, color: Color) {
    DrawLineV(startPos.readValue(), endPos.readValue(), color.readValue())
}

/**
 * Draw a line defining thickness
 */
inline fun drawLine(startPos: Vector2, endPos: Vector2, thick: Float, color: Color) {
    DrawLineEx(startPos.readValue(), endPos.readValue(), thick, color.readValue())
}

/**
 * Draw a line using cubic-bezier curves in-out
 */
inline fun drawLineBezier(startPos: Vector2, endPos: Vector2, thick: Float, color: Color) {
    DrawLineBezier(startPos.readValue(), endPos.readValue(), thick, color.readValue())
}

/**
 * Draw line using quadratic bezier curves with a control point
 */
inline fun drawLineBezierQuad(startPos: Vector2, endPos: Vector2, controlPos: Vector2, thick: Float, color: Color) {
    DrawLineBezierQuad(startPos.readValue(), endPos.readValue(), controlPos.readValue(), thick, color.readValue())
}

/**
 * Draw line using cubic bezier curves with 2 control points
 */
inline fun drawLineBezierCubic(startPos: Vector2, endPos: Vector2, startControlPos: Vector2, endControlPos: Vector2, thick: Float, color: Color) {
    DrawLineBezierCubic(startPos.readValue(), endPos.readValue(), startControlPos.readValue(), endControlPos.readValue(), thick, color.readValue())
}

/**
 * Draw lines sequence
 */
inline fun drawLine(points: Vector2, pointsCount: Int, color: Color) {
    DrawLineStrip(points.ptr, pointsCount, color.readValue())
}

/**
 * Draw a color-filled circle
 */
inline fun drawCircle(centerX: Int, centerY: Int, radius: Float, color: Color) {
    DrawCircle(centerX, centerY, radius, color.readValue())
}

/**
 * Draw a piece of a circle
 */
inline fun drawCircle(center: Vector2, radius: Float, startAngle: Float, endAngle: Float, segments: Int, color: Color) {
    DrawCircleSector(center.readValue(), radius, startAngle, endAngle, segments, color.readValue())
}

/**
 * Draw circle sector outline
 */
inline fun drawCircleSectorLines(center: Vector2, radius: Float, startAngle: Float, endAngle: Float, segments: Int, color: Color) {
    DrawCircleSectorLines(center.readValue(), radius, startAngle, endAngle, segments, color.readValue())
}

/**
 * Draw a gradient-filled circle
 */
inline fun drawCircle(centerX: Int, centerY: Int, radius: Float, color1: Color, color2: Color) {
    DrawCircleGradient(centerX, centerY, radius, color1.readValue(), color2.readValue())
}

/**
 * Draw a color-filled circle (Vector version)
 */
inline fun drawCircle(center: Vector2, radius: Float, color: Color) {
    DrawCircleV(center.readValue(), radius, color.readValue())
}

/**
 * Draw circle outline
 */
inline fun drawCircleLines(centerX: Int, centerY: Int, radius: Float, color: Color) {
    DrawCircleLines(centerX, centerY, radius, color.readValue())
}

/**
 * Draw ellipse
 */
inline fun drawEllipse(centerX: Int, centerY: Int, radiusH: Float, radiusV: Float, color: Color) {
    DrawEllipse(centerX, centerY, radiusH, radiusV, color.readValue())
}

/**
 * Draw ellipse outline
 */
inline fun drawEllipseLines(centerX: Int, centerY: Int, radiusH: Float, radiusV: Float, color: Color) {
    DrawEllipseLines(centerX, centerY, radiusH, radiusV, color.readValue())
}

/**
 * Draw ring
 */
inline fun drawRing(center: Vector2, innerRadius: Float, outerRadius: Float, startAngle: Float, endAngle: Float, segments: Int, color: Color) {
    DrawRing(center.readValue(), innerRadius, outerRadius, startAngle, endAngle, segments, color.readValue())
}

/**
 * Draw ring outline
 */
inline fun drawRingLines(center: Vector2, innerRadius: Float, outerRadius: Float, startAngle: Float, endAngle: Float, segments: Int, color: Color) {
    DrawRingLines(center.readValue(), innerRadius, outerRadius, startAngle, endAngle, segments, color.readValue())
}

/**
 * Draw a color-filled rectangle
 */
inline fun drawRectangle(posX: Int, posY: Int, width: Int, height: Int, color: Color) {
    DrawRectangle(posX, posY, width, height, color.readValue())
}

/**
 * Draw a color-filled rectangle
 */
inline fun drawRectangle(rec: Rectangle, color: Color) {
    DrawRectangleRec(rec.readValue(), color.readValue())
}

/**
 * Draw a color-filled rectangle with pro parameters
 */
inline fun drawRectangle(rec: Rectangle, origin: Vector2, rotation: Float, color: Color) {
    DrawRectanglePro(rec.readValue(), origin.readValue(), -rotation, color.readValue())
}

/**
 * Draw a color-filled rectangle (Vector version)
 */
inline fun drawRectangle(position: Vector2, size: Vector2, color: Color) {
    DrawRectangleV(position.readValue(), size.readValue(), color.readValue())
}

/**
 * Draw a vertical-gradient-filled rectangle
 */
inline fun drawRectangleGradientV(posX: Int, posY: Int, width: Int, height: Int, color1: Color, color2: Color) {
    DrawRectangleGradientV(posX, posY, width, height, color1.readValue(), color2.readValue())
}

/**
 * Draw a horizontal-gradient-filled rectangle
 */
inline fun drawRectangleGradientH(posX: Int, posY: Int, width: Int, height: Int, color1: Color, color2: Color) {
    DrawRectangleGradientH(posX, posY, width, height, color1.readValue(), color2.readValue())
}

/**
 * Draw a gradient-filled rectangle with custom vertex colors
 */
inline fun drawRectangle(rec: Rectangle, col1: Color, col2: Color, col3: Color, col4: Color) {
    DrawRectangleGradientEx(rec.readValue(), col1.readValue(), col2.readValue(), col3.readValue(), col4.readValue())
}

/**
 * Draw rectangle outline
 */
inline fun drawRectangleLines(posX: Int, posY: Int, width: Int, height: Int, color: Color) {
    DrawRectangleLines(posX, posY, width, height, color.readValue())
}

/**
 * Draw rectangle outline with extended parameters
 */
inline fun drawRectangleLinesEx(rec: Rectangle, lineThick: Float, color: Color) {
    DrawRectangleLinesEx(rec.readValue(), lineThick, color.readValue())
}

/**
 * Draw rectangle with rounded edges
 */
inline fun drawRectangle(rec: Rectangle, roundness: Float, segments: Int, color: Color) {
    DrawRectangleRounded(rec.readValue(), roundness, segments, color.readValue())
}

/**
 * Draw rectangle with rounded edges outline
 */
inline fun drawRectangle(rec: Rectangle, roundness: Float, segments: Int, lineThick: Float, color: Color) {
    DrawRectangleRoundedLines(rec.readValue(), roundness, segments, lineThick, color.readValue())
}

/**
 * Draw a color-filled triangle (vertex in counter-clockwise order!)
 */
inline fun drawTriangle(v1: Vector2, v2: Vector2, v3: Vector2, color: Color) {
    DrawTriangle(v1.readValue(), v2.readValue(), v3.readValue(), color.readValue())
}

/**
 * Draw triangle outline (vertex in counter-clockwise order!)
 */
inline fun drawTriangleLines(v1: Vector2, v2: Vector2, v3: Vector2, color: Color) {
    DrawTriangleLines(v1.readValue(), v2.readValue(), v3.readValue(), color.readValue())
}

/**
 * Draw a triangle fan defined by points (first vertex is the center)
 */
inline fun drawTriangleFan(points: Vector2, pointsCount: Int ,color: Color) {
    DrawTriangleFan(points.ptr, pointsCount, color.readValue())
}

/**
 * Draw a triangle strip defined by points
 */
inline fun drawTriangleStrip(points: Vector2, pointCount: Int, color: Color) {
    DrawTriangleStrip(points.ptr, pointCount, color.readValue())
}

/**
 * Draw a regular polygon (Vector version)
 */
inline fun drawPoly(center: Vector2, sides: Int, radius: Float, rotation: Float, color: Color) {
    DrawPoly(center.readValue(), sides, radius, rotation, color.readValue())
}

/**
 * Draw a polygon outline of n sides
 */
inline fun drawPolyLines(center: Vector2, sides: Int, radius: Float, rotation: Float, color: Color) {
    DrawPolyLines(center.readValue(), sides, radius, rotation, color.readValue())
}

/**
 * Draw a polygon outline of n sides with extended parameters
 */
inline fun drawPolyLinesEx(center: Vector2, sides: Int, radius: Float, rotation: Float, lineThick: Float, color: Color) {
    DrawPolyLinesEx(center.readValue(), sides, radius, rotation, lineThick, color.readValue())
}

//=======================================================//
// BASIC SHAPES COLLISION DETECTION FUNCTIONS
//=======================================================//

/**
 * Check collision between two rectangles
 */
inline fun checkCollision(rec1: Rectangle, rec2: Rectangle) : Boolean {
    return CheckCollisionRecs(rec1.readValue(), rec2.readValue())
}

/**
 * Check collision between two circles
 */
inline fun checkCollision(center1: Vector2, radius1: Float, center2: Vector2, radius2: Float) : Boolean {
    return CheckCollisionCircles(center1.readValue(), radius1, center2.readValue(), radius2)
}

/**
 * Check collision between circle and rectangle
 */
inline fun checkCollision(center: Vector2, radius: Float, rec: Rectangle) : Boolean {
    return CheckCollisionCircleRec(center.readValue(), radius, rec.readValue())
}

/**
 * Check if point is inside rectangle
 */
inline fun checkCollision(point: Vector2, rec: Rectangle) : Boolean {
    return CheckCollisionPointRec(point.readValue(), rec.readValue())
}

/**
 * Check if point is inside circle
 */
inline fun checkCollision(point: Vector2, center: Vector2, radius: Float) : Boolean {
    return CheckCollisionPointCircle(point.readValue(), center.readValue(), radius)
}

/**
 * Check if point is inside a triangle
 */
inline fun checkCollision(point: Vector2, p1: Vector2, p2: Vector2, p3: Vector2) : Boolean {
    return CheckCollisionPointTriangle(point.readValue(), p1.readValue(), p2.readValue(), p3.readValue())
}

/**
 * Check the collision between two lines defined by two points each, returns collision point by reference
 */
inline fun checkCollision(startPos1: Vector2, endPos1: Vector2, startPos2: Vector2, endPos2: Vector2, collisionPoint: CValuesRef<Vector2>) : Boolean {
    return CheckCollisionLines(startPos1.readValue(), endPos1.readValue(), startPos2.readValue(), endPos2.readValue(), collisionPoint)
}

/**
 * Check if point belongs to line created between two points [p1] and [p2] with defined margin in pixels [threshold]
 */
inline fun checkCollision(point: Vector2, p1: Vector2, p2: Vector2, threshold: Int) : Boolean {
    return CheckCollisionPointLine(point.readValue(), p1.readValue(), p2.readValue(), threshold)
}

/**
 * Get collision rectangle for two rectangles collision
 */
inline fun getCollision(rec1: Rectangle, rec2: Rectangle) : Rectangle {
    return GetCollisionRec(rec1.readValue(), rec2.readValue()).getPointer(MemScope()).pointed
}
