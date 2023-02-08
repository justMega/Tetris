package kaylib.kMath

import kaylibc.*
import kotlinx.cinterop.*
import kotlin.math.atan2
import kotlin.math.sqrt

// -- Module: kMath

//=======================================================//
// VECTOR2 DATA TYPE
//=======================================================//

/**
 * Constructor function for Vector2
 * Important to note that this uses MemScope() as an allocator when kVector2 is created. However, if needed you can pass your own allocator via the third patameter - allocator
 * @return Vector2
 */
inline fun kVector2(x: Float = 0F, y: Float = 0F, allocator: AutofreeScope = MemScope()): Vector2 {
    return allocator.alloc<Vector2> {
        this.x = x
        this.y = y
    }
}

/**
 * Vector with components value 0
 * @return zero values Y and X of passed Vector2
 */
inline fun Vector2.setZero() : Vector2 = this.apply { this.x = 0F; this.y = 0F }

/**
 * Vector with components value 1
 * @return values of 1 in Y and X of passed Vector2
 */
inline fun Vector2.setOne() : Vector2 = this.apply { this.x = 1F; this.y = 1F }

/**
 * Calculate vector length
 */
inline fun Vector2.length() : Float = sqrt((this.x*this.x) + (this.y*this.y))

/**
 * Calculate vector square length
 */
inline fun Vector2.lengthSqr() = (this.x*this.x) + (this.y*this.y)

/**
 * Calculate two vectors dot product
 */
inline fun Vector2.dot(v2: Vector2) : Float = (this.x*v2.x + this.y*v2.y)

/**
 * Calculate distance between two vectors
 */
inline fun Vector2.distance(v2: Vector2) : Float = sqrt((this.x - v2.x)*(this.x - v2.x) + (this.y - v2.y)*(this.y - v2.y))

/**
 * Calculate square distance between two vectors
 */
inline fun Vector2.distanceSqr(v2: Vector2) : Float = ((this.x - v2.x)*(this.x - v2.x) + (this.y - v2.y)*(this.y - v2.y))

/**
 * Calculate angle from two vectors
 */
inline fun Vector2.angle(v2: Vector2) : Float = atan2(v2.x, v2.y) - atan2(this.x, this.y)

/**
 * Scale vector (multiply by value)
 */
inline fun Vector2.times(scale: Float) {
    this.x *= scale
    this.y *= scale
}

/**
 * Add two vectors
 */
inline fun Vector2.add(v2: Vector2) {
    this.x += v2.x
    this.y += v2.y
}

/**
 * Add two vectors
 */
inline operator fun Vector2.plus(v2: Vector2) {
    this.x += v2.x
    this.y += v2.y
}

/**
 * Multiply vector by vector
 */
inline fun Vector2.multiply(v2: Vector2) {
    this.x *= v2.x
    this.y *= v2.y
}

/**
 * Add vector and float value
 */
inline fun Vector2.addValue(add: Float)  {
    this.x += add
    this.y += add
}

/**
 * Subtract two vectors
 */
inline fun Vector2.subtract(v2: Vector2) {
    this.x -= v2.x
    this.y -= v2.y
}

/**
 * Subtract vector by float value
 */
inline fun Vector2.subtractValue(sub: Float)  {
    this.x -= sub
    this.y -= sub
}

/**
 * Negate vector
 */
inline fun Vector2.negate() {
    this.x = this.x.unaryMinus()
    this.y = this.y.unaryMinus()
}

/**
 * Normalize provided vector
 */
inline fun Vector2.normalize() : Vector2 {
    val result = this
    val length = sqrt((this.x*this.x) + (this.y*this.y))

    if (length > 0) {
        val iLength = 1F/length
        result.x = this.x*iLength
        result.y = this.y*iLength
    }
    return result
}

/**
 * Transforms a Vector2 by a given Matrix
 */
inline fun Vector2.transform(mat: Matrix) : Vector2 {
    val result = this

    val x = this.x
    val y = this.y
    val z = 0

    result.x = mat.m0*x + mat.m4*y + mat.m8*z + mat.m12
    result.y = mat.m1*x + mat.m5*y + mat.m9*z + mat.m13

    return result
}

/**
 * Calculate linear interpolation between two vectors
 */
inline fun Vector2.lerp(v2: Vector2, amount: Float) : Vector2 {
    val result = this

    result.x = this.x + amount*(v2.x - this.x)
    result.y = this.y + amount*(v2.y - this.y)

    return result
}

/**
 * Calculate reflected vector to normal
 */
inline fun Vector2.reflect(normal: Vector2) : Vector2 {
    return Vector2Reflect(this.readValue(), normal.readValue()).getPointer(MemScope()).pointed
}

/**
 * Rotate vector by angle
 */
inline fun Vector2.rotate(angle: Float) : Vector2 {
    return Vector2Rotate(this.readValue(), angle).getPointer(MemScope()).pointed
}

/**
 * Move Vector towards target
 */
inline fun Vector2.moveTowards(target: Vector2, maxDistance: Float) : Vector2 {
    return Vector2MoveTowards(this.readValue(), target.readValue(), maxDistance).getPointer(MemScope()).pointed
}

/**
 * Invert the given vector
 */
inline fun Vector2.invert() : Vector2 {
    return Vector2Invert(this.readValue()).getPointer(MemScope()).pointed
}

/**
 * Clamp the components of the vector between
 */
inline fun Vector2.clamp(min: Vector2, max: Vector2) : Vector2 {
    return Vector2Clamp(this.readValue(), min.readValue(), max.readValue()).getPointer(MemScope()).pointed
}

@Deprecated("Use Kotlin's internal coerceIn function", ReplaceWith("coerceIn()"))
/**
 * Clamp the magnitude of the vector between two values
 */
inline fun Vector2.clampValue(min: Float, max: Float) : Vector2 {
    return Vector2ClampValue(this.readValue(), min, max).getPointer(MemScope()).pointed
}

/**
 * Deconstruct this vector.
 */
inline operator fun Vector2.component1(): Float = x

/**
 * Deconstruct this vector.
 */
inline operator fun Vector2.component2(): Float = y

/**
 * Divide vector by vector
 */
inline operator fun Vector2.divAssign(v2: Vector2) {
    this.x /= v2.x
    this.y /= v2.y
}

/**
 * Divide vector by vector
 */
inline operator fun Vector2.div(v2: Vector2) {
    this.x /= v2.x
    this.y /= v2.y
}


/**
 * Divide both vector  values by scalar.
 */
inline operator fun Vector2.divAssign(scalar: Float) {
    this.x /= scalar
    this.y /= scalar
}

/**
 * Divide both vector  values by scalar.
 */
inline operator fun Vector2.divAssign(scalar: Int) {
    this.x /= scalar
    this.y /= scalar
}

/**
 * Multiply both vector values
 */
inline operator fun Vector2.timesAssign(scalar: Int) {
    this.x *= scalar.toFloat()
    this.y *= scalar.toFloat()
}

/**
 * Multiply both vector values
 */
inline operator fun Vector2.timesAssign(scalar: Float) {
    this.x *= scalar
    this.y *= scalar
}
/**
 * Multiply both vector values
 */
inline operator fun Vector2.timesAssign(v2: Vector2) {
    this.x *= v2.x
    this.y *= v2.y
}

/**
 * Add two vectors
 */
inline operator fun Vector2.plusAssign(v2: Vector2) {
    this.add(v2)
}

/**
 * Add two vectors to both x and y of the vector.
 */
inline operator fun Vector2.plusAssign(add: Float) {
    this.x += add
    this.y += add
}

/**
 * Add two vectors to both x and y of the vector.
 */
inline operator fun Vector2.plusAssign(addend: Int) {
    plusAssign(addend.toFloat())
}

/**
 * Subtract a Vector2 by another Vector2
 */
inline operator fun Vector2.minusAssign(v2: Vector2) {
    this.x -= v2.x
    this.y -= v2.y
}

/**
 * Value will be subtracted from both x and y of the vector2.
 */
inline operator fun Vector2.minusAssign(value: Float) {
    this.x -= value
    this.y -= value
}

/**
 * Value will be subtracted from both x and y of the vector2.
 */
inline operator fun Vector2.minusAssign(value: Int) {
    minusAssign(value.toFloat())
}

/**
 * Subtract a Vector2 by another Vector2
 */
inline operator fun Vector2.minus(v2: Vector2) {
    this.x -= v2.x
    this.y -= v2.y
}

/**
 * Value will be multiplied by a Vector2
 */
inline operator fun Vector2.times(v2: Vector2) {
    this.x *= v2.x
    this.y *= v2.y
}

/**
 * Get a reminder of Vector2
 */
inline operator fun Vector2.rem(v2: Vector2) {
    this.x %= v2.x
    this.y %= v2.y
}

/**
 * Value will be compared with another Vector2
 */
inline operator fun Vector2.compareTo(other: Vector2): Int = when {
    this.y != other.y -> (this.y - other.y).toInt()
    else -> (this.x - other.x).toInt()
}

/**
 * Due to inability to override toString function, this is the current workaround printing the values of a Vector2
 * @return String values of Vector2
 */
inline fun Vector2.asString(): String {
    return "X: ${x.toString()}\nY: ${y.toString()}"
}

/**
 * Due to inability to override equals operator, this is the current workaround checking for equality of the values of a Vector4
 * @return String values of Vector3
 */
inline fun Vector2.equalsTo(v: Vector2): Boolean {
    val result = Vector2Equals(this.readValue(), v.readValue())

    return result != 0
}

/**
 * Set value of a Vector2 with another provided value of same type.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
inline fun Vector2.set(other: Vector2) {
    this.x = other.x
    this.y = other.y
}