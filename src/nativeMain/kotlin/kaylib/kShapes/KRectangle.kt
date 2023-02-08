package kaylib.kShapes

import kaylib.kMath.kVector2
import kaylibc.Rectangle
import kotlinx.cinterop.AutofreeScope
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc
import kaylibc.Vector2

/**
 * Constructor function for Rectangle using X and Y floats for position.
 * @param allocator Uses `MemScope()` by default.
 * @return [Rectangle]
 */
inline fun kRectangle(x: Float = 0F, y: Float = 0F, width: Float = 0F, height: Float = 0F, allocator: AutofreeScope = MemScope()) : Rectangle {
    return allocator.alloc<Rectangle> {
        this.x = x
        this.y = y
        this.width = width
        this.height = height
    }
}

/**
 * Constructor function for Rectangle that take a kVector2 for position.
 * @param allocator Uses `MemScope()` by default.
 * @return [Rectangle]
 */
inline fun kRectangle(vector2: Vector2 = kVector2(0F, 0F), width: Float = 0F, height: Float = 0F, allocator: AutofreeScope = MemScope()) : Rectangle {
    return allocator.alloc<Rectangle> {
        this.x = vector2.x
        this.y = vector2.y
        this.width = width
        this.height = height
    }
}

/**
 * Set value of a Rectangle with another provided value of same type.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
inline fun Rectangle.set(other: Rectangle) {
    this.x = other.x
    this.y = other.y
    this.width = other.width
    this.height = other.height
}