package kaylib.kMath

import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kMath

//=======================================================//
// Matrix DATA TYPE
//=======================================================//

/**
 * Constructor function for Matrix
 * @param allocator Uses `MemScope()` by default.
 * @return [Matrix]
 */
inline fun kMatrix(
    m0: Float, m4: Float, m8: Float, m12: Float, m1: Float, m5: Float, m9: Float, m13: Float, m2: Float, m6: Float, m10: Float, m14: Float, m3: Float, m7: Float, m11: Float, m15: Float, allocator: AutofreeScope = MemScope()): Matrix {
    return allocator.alloc<Matrix> {
        this.m0 = m0
        this.m4 = m4
        this.m8 = m8
        this.m12 = m12
        this.m1 = m1
        this.m5 = m5
        this.m9 = m9
        this.m13 = m13
        this.m2 = m2
        this.m6 = m6
        this.m10 = m10
        this.m14 = m14
        this.m3 = m3
        this.m7 = m7
        this.m11 = m11
        this.m15 = m15
    }
}

/**
 * Compute matrix determinant
 * @return [Float]
 */
inline fun Matrix.determinant(): Float {
    return MatrixDeterminant(this.readValue())
}

/**
 * Get the trace of the matrix (sum of the values along the diagonal)
 * @return [Float]
 */
inline fun Matrix.trace(): Float {
    return MatrixTrace(this.readValue())
}

/**
 * Get the trace of the matrix (sum of the values along the diagonal)
 * @return [Matrix]
 */
inline fun Matrix.transpose(): Matrix {
    return MatrixTranspose(this.readValue()).getPointer(MemScope()).pointed
}

/**
 * Invert provided matrix
 * @return [Matrix]
 */
inline fun Matrix.invert(): Matrix {
    return MatrixInvert(this.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get identity matrix
 * @return [Matrix]
 */
inline fun Matrix.identity(): Matrix {
    return kMatrix(
        1.0F, 0.0F, 0.0F, 0.0F,
        0.0F, 1.0F, 0.0F, 0.0F,
        0.0F, 0.0F, 1.0F, 0.0F,
        0.0F, 0.0F, 0.0F, 1.0F
    )
}

/**
 * Add two matrices
 */
inline operator fun Matrix.plus(m: Matrix) {
    this.m0 + m.m0
    this.m1 + m.m1
    this.m2 + m.m2
    this.m3 + m.m3
    this.m4 + m.m4
    this.m5 + m.m5
    this.m6 + m.m6
    this.m7 + m.m7
    this.m8 + m.m8
    this.m9 + m.m9
    this.m10 + m.m10
    this.m11 + m.m11
    this.m12 + m.m12
    this.m13 + m.m13
    this.m14 + m.m14
    this.m15 + m.m15
}

/**
 * Subtract two matrices
 */
inline operator fun Matrix.minus(m: Matrix) {
    this.m0 - m.m0
    this.m1 - m.m1
    this.m2 - m.m2
    this.m3 - m.m3
    this.m4 - m.m4
    this.m5 - m.m5
    this.m6 - m.m6
    this.m7 - m.m7
    this.m8 - m.m8
    this.m9 - m.m9
    this.m10 - m.m10
    this.m11 - m.m11
    this.m12 - m.m12
    this.m13 - m.m13
    this.m14 - m.m14
    this.m15 - m.m15
}

/**
 * Multiply two matrices
 */
inline operator fun Matrix.times(m: Matrix) {
    this.m0 * m.m0
    this.m1 * m.m1
    this.m2 * m.m2
    this.m3 * m.m3
    this.m4 * m.m4
    this.m5 * m.m5
    this.m6 * m.m6
    this.m7 * m.m7
    this.m8 * m.m8
    this.m9 * m.m9
    this.m10 * m.m10
    this.m11 * m.m11
    this.m12 * m.m12
    this.m13 * m.m13
    this.m14 * m.m14
    this.m15 * m.m15
}

/**
 * Get translation matrix
 * @return [Matrix]
 */
inline fun Matrix.translate(x: Float, y: Float, z: Float): Matrix {
    return MatrixTranslate(x, y, z).getPointer(MemScope()).pointed
}

/**
 * Create rotation matrix from axis and angle
 * NOTE: [angle] should be provided in radians
 * @return [Matrix]
 */
inline fun Matrix.rotate(axis: Vector3, angle: Float): Matrix {
    return MatrixRotate(axis.readValue(), angle).getPointer(MemScope()).pointed
}

/**
 * Get x-rotation matrix
 * NOTE: [angle] must be provided in radians
 * @return [Matrix]
 */
inline fun Matrix.rotateX(angle: Float): Matrix {
    return MatrixRotateX(angle).getPointer(MemScope()).pointed
}

/**
 * Get y-rotation matrix
 * NOTE: [angle] must be provided in radians
 * @return [Matrix]
 */
inline fun Matrix.rotateY(angle: Float): Matrix {
    return MatrixRotateY(angle).getPointer(MemScope()).pointed
}

/**
 * Get z-rotation matrix
 * NOTE: [angle] must be provided in radians
 * @return [Matrix]
 */
inline fun Matrix.rotateZ(angle: Float): Matrix {
    return MatrixRotateZ(angle).getPointer(MemScope()).pointed
}

/**
 * Get XYZ-rotation matrix
 * NOTE: [angle] must be provided in radians
 * @return [Matrix]
 */
inline fun Matrix.rotateXYZ(angle: Vector3): Matrix {
    return MatrixRotateXYZ(angle.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get ZYX-rotation matrix
 * NOTE: [angle] must be provided in radians
 * @return [Matrix]
 */
inline fun Matrix.rotateZYX(angle: Vector3): Matrix {
    return MatrixRotateZYX(angle.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get scaling matrix
 * @return [Matrix]
 */
inline fun Matrix.scale(x: Float, y: Float, z: Float): Matrix {
    return MatrixScale(x, y, z).getPointer(MemScope()).pointed
}

/**
 * Get perspective projection matrix
 * @return [Matrix]
 */
inline fun Matrix.frustum(left: Double, right: Double,bottom: Double, top: Double, near: Double, far: Double): Matrix {
    return MatrixFrustum(left, right, bottom, top, near, far).getPointer(MemScope()).pointed
}

/**
 * Get perspective projection matrix
 * NOTE: [fovy] angle must be provided in radians
 */
inline fun Matrix.perspective(fovy: Double, aspect: Double, near: Double, far: Double): Matrix {
    return MatrixPerspective(fovy, aspect, near, far).getPointer(MemScope()).pointed
}

/**
 * Get orthographic projection matrix
 * @return [Matrix]
 */
inline fun Matrix.ortho(left: Double, right: Double,bottom: Double, top: Double, near: Double, far: Double): Matrix {
    return MatrixOrtho(left, right, bottom, top, near, far).getPointer(MemScope()).pointed
}

/**
 * Get camera look-at matrix (view matrix)
 * @return [Matrix]
 */
inline fun Matrix.lookAt(eye: Vector3, target: Vector3, up: Vector3): Matrix {
    return MatrixLookAt(eye.readValue(), target.readValue(), up.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get float array of matrix data
 * @return [float16]
 */
inline fun Matrix.toFloatV(): float16 {
    return MatrixToFloatV(this.readValue()).getPointer(MemScope()).pointed
}

/**
 * Set value of a Matrix with another provided value.
 * This is useful when dealing with cinterop CStruct that holds nested CStructs which are marked as immutable (val).
 * NOTE: While the CStruct is immutable itself, the inner members of that CStruct are mutable.
 */
inline fun Matrix.set(other: Matrix) {
    this.m0 - other.m0
    this.m1 - other.m1
    this.m2 - other.m2
    this.m3 - other.m3
    this.m4 - other.m4
    this.m5 - other.m5
    this.m6 - other.m6
    this.m7 - other.m7
    this.m8 - other.m8
    this.m9 - other.m9
    this.m10 - other.m10
    this.m11 - other.m11
    this.m12 - other.m12
    this.m13 - other.m13
    this.m14 - other.m14
    this.m15 - other.m15
}