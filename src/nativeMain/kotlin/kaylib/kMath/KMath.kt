package kaylib.kMath

import kaylibc.*
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.pointed
import kotlinx.cinterop.readValue

// -- Module: kMath

//=======================================================//
// MATH FUNCTIONS
//=======================================================//

/**
 * Clamp float value
 * @return Float
 */
inline fun clamp(value: Float, min: Float, max: Float) : Float = Clamp(value, min, max)

/**
 * Calculate linear interpolation between two floats
 * @return Float
 */
inline fun lerp(value: Float, start: Float, end: Float) : Float = Lerp(value, start, end)

/**
 * Normalize input value within input range
 * @return Float
 */
inline fun normalize(value: Float, start: Float, end: Float) : Float = Normalize(value, start, end)

/**
 * Remap input value within input range to output range
 * @return Float
 */
inline fun remap(value: Float, inputStart: Float, inputEnd: Float, outputStart: Float, outputEnd: Float) : Float {
    return Remap(value, inputStart, inputEnd, outputStart, outputEnd)
}

/**
 * Calculate quaternion based on the rotation from one vector to another
 * @return Quaternion
 */
inline fun quaternionFromVector3toVector3(from: Vector3, to: Vector3): Quaternion {
    return QuaternionFromVector3ToVector3(from.readValue(), to.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get a quaternion for a given rotation matrix
 * @return Quaternion
 */
inline fun quaternionFromMatrix(mat: Matrix): Quaternion {
    return QuaternionFromMatrix(mat.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get a matrix for a given quaternion
 * @return Matrix
 */
inline fun quaternionToMatrix(v: Vector4): Matrix {
    return QuaternionToMatrix(v.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get rotation quaternion for an angle and axis
 *
 * NOTE: [angle] must be provided in radians
 * @return Quaternion
 */
inline fun quaternionFromAxisAngle(axis: Vector3, angle: Float): Quaternion {
    return QuaternionFromAxisAngle(axis.readValue(), angle).getPointer(MemScope()).pointed
}

/**
 * Get the quaternion equivalent to Euler angles
 *
 * NOTE: Rotation order is ZYX
 * @return Quaternion
 */
inline fun quaternionFromEuler(pitch: Float, yaw: Float, roll: Float): Quaternion {
    return QuaternionFromEuler(pitch, yaw, roll).getPointer(MemScope()).pointed
}
