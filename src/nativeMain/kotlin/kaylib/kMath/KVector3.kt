package kaylib.kMath

import kaylibc.*
import kotlinx.cinterop.*
import kotlin.math.atan2
import kotlin.math.sqrt

// -- Module: kMath

//=======================================================//
// VECTOR3 DATA TYPE
//=======================================================//

/**
 * Constructor function for Vector3
 * Important to note that this uses MemScope() as an allocator when kVector3 is created. However, if needed you can pass your own allocator via the third patameter - allocator
 * @return Vector3
 */
inline fun kVector3(x: Float = 0F, y: Float = 0F, z: Float = 0F, allocator: AutofreeScope = MemScope()): Vector3 {
    return allocator.alloc<Vector3> {
        this.x = x
        this.y = y
        this.z = z
    }
}

/**
 * Vector with components value 0
 * @return zero values Y, X and Z of passed Vector3
 */
inline fun Vector3.setZero() : Vector3 = this.apply { this.x = 0F; this.y = 0F; this.z = 0F }

/**
 * Vector with components value 1
 * @return values of 1 in Y and X of passed Vector3
 */
inline fun Vector3.setOne() : Vector3 = this.apply { this.x = 1F; this.y = 1F; this.z = 1F }

/**
 * Calculate vector length
 */
inline fun Vector3.length() : Float = sqrt((this.x*this.x + this.y*this.y + this.z*this.z))

/**
 * Calculate vector square length
 */
inline fun Vector3.lengthSqr() = (this.x*this.x + this.y*this.y + this.z*this.z)

/**
 * Calculate two vectors dot product
 */
inline fun Vector3.dot(v3: Vector3) : Float = (this.x*v3.x + this.y*v3.y + this.z*v3.z)

/**
 * Calculate distance between two vectors
 */
inline fun Vector3.distance(v3: Vector3) : Float = sqrt((this.x - v3.x)*(this.x - v3.x) + (this.y - v3.y)*(this.y - v3.y) + (this.z - v3.z)*(this.z - v3.z))

/**
 * Calculate square distance between two vectors
 */
inline fun Vector3.distanceSqr(v3: Vector3) : Float = ((this.x - v3.x)*(this.x - v3.x) + (this.y - v3.y)*(this.y - v3.y) + (this.z - v3.z)*(this.z - v3.z))

/**
 * Calculate angle from two vectors
 */
inline fun Vector3.angle(v3: Vector3) : Float {
    val result: Float

    val cross = kVector3(this.y*v3.z - this.z*v3.y, this.z*v3.x - this.x*v3.z, this.x*v3.y - this.y*v3.x )
    val len = sqrt(cross.x*cross.x + cross.y*cross.y + cross.z*cross.z)
    val dot = (this.x*v3.x + this.y*v3.y + this.z*v3.z)

    result = atan2(len, dot)
    return result
}

/**
 * Calculate two vectors cross product
 */
inline fun Vector3.crossProduct(v2: Vector3): Vector3 {
    return kVector3(this.y * v2.z - this.z * v2.y, this.z * v2.x - this.x * v2.z, this.x * v2.y - this.y * v2.x)
}

/**
 * Scale vector (multiply by value)
 */
inline fun Vector3.times(scale: Float) {
    this.x *= scale
    this.y *= scale
    this.z *= scale
}

/**
 * Add two vectors
 */
inline fun Vector3.add(v3: Vector3) {
    this.x += v3.x
    this.y += v3.y
    this.z += v3.z
}

/**
 * Multiply vector by vector
 */
inline fun Vector3.multiply(v3: Vector3) {
    this.x *= v3.x
    this.y *= v3.y
    this.z *= v3.z
}

/**
 * Add vector and float value
 */
inline fun Vector3.addValue(add: Float)  {
    this.x += add
    this.y += add
    this.z += add
}

/**
 * Subtract two vectors
 */
inline fun Vector3.subtract(v3: Vector3) {
    this.x -= v3.x
    this.y -= v3.y
    this.z -= v3.z
}

/**
 * Subtract vector by float value
 */
inline fun Vector3.subtractValue(sub: Float)  {
    this.x -= sub
    this.y -= sub
    this.z -= sub
}

/**
 * Get reminder of Vector3
 */
inline fun Vector3.rem(v3: Vector3)  {
    this.x %= v3.x
    this.y %= v3.y
    this.z %= v3.z
}

/**
 * Negate vector
 */
inline fun Vector3.negate() {
    this.x = this.x.unaryMinus()
    this.y = this.y.unaryMinus()
    this.z = this.z.unaryMinus()
}

/**
 * Normalize provided vector
 */
inline fun Vector3.normalize() : Vector3 {
    val result = this
    var length = sqrt(this.x*this.x + this.y*this.y + this.z*this.z)

    if (length == 0F) { length = 1F }

    val iLength = 1F/length
    result.x = this.x*iLength
    result.y = this.y*iLength
    result.z = this.z*iLength
    return result
}

/**
 * Transforms a Vector3 by a given Matrix
 */
inline fun Vector3.transform(mat: Matrix) : Vector3 {
    val result = this

    val x = this.x
    val y = this.y
    val z = this.z

    result.x = mat.m0*x + mat.m4*y + mat.m8*z + mat.m12
    result.y = mat.m1*x + mat.m5*y + mat.m9*z + mat.m13
    result.z = mat.m2*x + mat.m6*y + mat.m10*z + mat.m14;

    return result
}

/**
 * Transform a vector by quaternion rotation
 */
inline fun Vector3.rotateByQuaternion(q: Quaternion): Vector3 {
    return Vector3RotateByQuaternion(this.readValue(), q.readValue()).getPointer(MemScope()).pointed
}

/**
 * Rotates a vector around an axis
 */
inline fun Vector3.rotateByAxisAngle(axis: Vector3, angle: Float): Vector3 {
    return Vector3RotateByAxisAngle(this.readValue(), axis.readValue(), angle).getPointer(MemScope()).pointed
}

/**
 * Calculate linear interpolation between two vectors
 */
inline fun Vector3.lerp(v3: Vector3, amount: Float) : Vector3 {
    val result = this

    result.x = this.x + amount*(v3.x - this.x)
    result.y = this.y + amount*(v3.y - this.y)
    result.z = this.z + amount*(v3.z - this.z)

    return result
}

/**
 * Orthonormalize provided vectors
 * Makes vectors normalized and orthogonal to each other
 * Gram-Schmidt function implementation
 */
inline fun Vector3.orthoNormalize(v2: CPointer<Vector3>) {
    return Vector3OrthoNormalize(this.ptr, v2)
}


/**
 * Calculate reflected vector to normal
 */
inline fun Vector3.reflect(normal: Vector3) : Vector3 {
    return Vector3Reflect(this.readValue(), normal.readValue()).getPointer(MemScope()).pointed
}

/**
 * Calculate one vector perpendicular vector
 */
inline fun Vector3.perpendicular() : Vector3 {
    return Vector3Perpendicular(this.readValue()).getPointer(MemScope()).pointed
}

/**
 * Invert the given vector
 */
inline fun Vector3.invert() : Vector3 {
    return Vector3Invert(this.readValue()).getPointer(MemScope()).pointed
}


/**
 * Get min value for each pair of components
 */
inline fun Vector3.min(v2: Vector3) : Vector3 {
    return Vector3Min(this.readValue(), v2.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get max value for each pair of components
 */
inline fun Vector3.max(v2: Vector3) : Vector3 {
    return Vector3Max(this.readValue(), v2.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get max value for each pair of components
 */
inline fun Vector3.barycenter(a: Vector3, b: Vector3, c: Vector3) : Vector3 {
    return Vector3Barycenter(this.readValue(), a.readValue(), b.readValue(), c.readValue()).getPointer(MemScope()).pointed
}

/**
 * Projects a Vector3 from screen space into object space
 * NOTE: We are avoiding calling other raymath functions despite available
 */
inline fun Vector3.barycenter(projection: Matrix, view: Matrix) : Vector3 {
    return Vector3Unproject(this.readValue(), projection.readValue(), view.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get Vector3 as float array
 */
inline fun Vector3.toFloatV() : float3 {
    return Vector3ToFloatV(this.readValue()).getPointer(MemScope()).pointed
}

/**
 * Clamp the components of the vector between
 */
inline fun Vector3.clamp(min: Vector3, max: Vector3) : Vector3 {
    return Vector3Clamp(this.readValue(), min.readValue(), max.readValue()).getPointer(MemScope()).pointed
}

@Deprecated("Use Kotlin's internal coerceIn function", ReplaceWith("coerceIn()"))
/**
 * Clamp the magnitude of the vector between two values
 */
inline fun Vector3.clampValue(min: Float, max: Float) : Vector3 {
    return Vector3ClampValue(this.readValue(), min, max).getPointer(MemScope()).pointed
}

/**
 * Due to inability to override equals operator, this is the current workaround checking for equality of the values of a Vector4
 * @return String values of Vector3
 */
inline fun Vector3.equalsTo(v: Vector3): Boolean {
    val result = Vector3Equals(this.readValue(), v.readValue())

    return result != 0
}

/**
 * Compute the direction of a refracted ray where v specifies the
 * normalized direction of the incoming ray, n specifies the
 * normalized normal vector of the interface of two optical media,
 * and r specifies the ratio of the refractive index of the medium
 * from where the ray comes to the refractive index of the medium
 * on the other side of the surface
 */
inline fun Vector3.refract(n: Vector3, r: Float) : Vector3 {
    return Vector3Refract(this.readValue(), n.readValue(), r).getPointer(MemScope()).pointed
}

/**
 * Divide vector by vector
 */
inline operator fun Vector3.div(v3: Vector3) {
    this.x /= v3.x
    this.y /= v3.y
    this.z /= v3.z
}

/**
 * Deconstruct this vector.
 */
inline operator fun Vector3.component1(): Float = x

/**
 * Deconstruct this vector.
 */
inline operator fun Vector3.component2(): Float = y

/**
 * Deconstruct this vector.
 */
inline operator fun Vector3.component3(): Float = z

/**
 * Divide vector by vector
 */
inline operator fun Vector3.divAssign(v3: Vector3) {
    this.x /= v3.x
    this.y /= v3.y
    this.z /= v3.z
}

/**
 * Divide both vector  values by scalar.
 */
inline operator fun Vector3.divAssign(scalar: Float) {
    this.x /= scalar
    this.y /= scalar
    this.z /= scalar
}

/**
 * Divide both vector  values by scalar.
 */
inline operator fun Vector3.divAssign(scalar: Int) {
    this.x /= scalar
    this.y /= scalar
    this.z /= scalar
}

/**
 * Multiply both vector values
 */
inline operator fun Vector3.timesAssign(scalar: Int) {
    this.x *= scalar.toFloat()
    this.y *= scalar.toFloat()
    this.z *= scalar.toFloat()
}

/**
 * Multiply both vector values
 */
inline operator fun Vector3.timesAssign(scalar: Float) {
    this.x *= scalar
    this.y *= scalar
    this.z *= scalar
}
/**
 * Multiply both vector values
 */
inline operator fun Vector3.timesAssign(v3: Vector3) {
    this.x *= v3.x
    this.y *= v3.y
    this.z *= v3.z
}

/**
 * Add two vectors
 */
inline operator fun Vector3.plusAssign(v3: Vector3) {
    this.add(v3)
}

/**
 * Add two vectors
 */
inline operator fun Vector3.plus(v3: Vector3) {
    this.add(v3)
}

/**
 * Add two vectors to both x, y and z of the vector.
 */
inline operator fun Vector3.plusAssign(add: Float) {
    this.x += add
    this.y += add
    this.z += add
}

/**
 * Add two vectors to both x, y and z of the vector.
 */
inline operator fun Vector3.plusAssign(addend: Int) {
    plusAssign(addend.toFloat())
}

/**
 * Subtract a Vector3 by another Vector3
 */
inline operator fun Vector3.minusAssign(v3: Vector3) {
    this.x -= v3.x
    this.y -= v3.y
    this.z -= v3.z
}

/**
 * Subtract a Vector3 by another Vector3
 */
inline operator fun Vector3.minus(v3: Vector3) {
    this.x -= v3.x
    this.y -= v3.y
    this.z -= v3.z
}

/**
 * Value will be subtracted from both x, y and z of the vector3.
 */
inline operator fun Vector3.minusAssign(value: Float) {
    this.x -= value
    this.y -= value
    this.z -= value
}

/**
 * Value will be subtracted from both x, y and z of the vector3.
 */
inline operator fun Vector3.minusAssign(value: Int) {
    minusAssign(value.toFloat())
}

/**
 * Value will be multiplied by a Vector3
 */
inline operator fun Vector3.times(v3: Vector3) {
    this.x *= v3.x
    this.y *= v3.y
    this.z *= v3.z
}

/**
 * Value will be compared with another Vector3
 */
inline operator fun Vector3.compareTo(other: Vector3): Int = when {
    this.y != other.y -> (this.y - other.y).toInt()
    this.z != other.z -> (this.z - other.z).toInt()
    else -> (this.x - other.x).toInt()
}

/**
 * Due to inability to override toString function, this is the current workaround printing the values of a Vector3
 * @return Vector2
 */
inline fun Vector3.asString(): String {
    return "X: ${x.toString()}\nY: ${y.toString()}\nZ: ${z.toString()}"
}

/**
 * Set value of a Vector3 with another provided value.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
inline fun Vector3.set(other: Vector3) {
    this.x = other.x
    this.y = other.y
    this.z = other.z
}