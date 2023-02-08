package kaylib.kMath

import kaylibc.Quaternion
import kotlinx.cinterop.AutofreeScope
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.alloc

// -- Module: kMath

//=======================================================//
// Quaternion DATA TYPE - Vector4 Alias
// NOTE: All functions are located in KVector4.kt
//=======================================================//

/**
 * Constructor function for Quaternion
 * @param allocator Uses `MemScope()` by default.
 * @return [Quaternion]
 */
inline fun kQuaternion(x: Float = 0F, y: Float = 0F, z: Float = 0F, w: Float = 0F, allocator: AutofreeScope = MemScope()): Quaternion {
    return allocator.alloc<Quaternion> {
        this.x = x
        this.y = y
        this.z = z
        this.w = w
    }
}