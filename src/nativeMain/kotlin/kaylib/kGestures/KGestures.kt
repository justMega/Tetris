package kaylib.kGestures

import kaylibc.*
import kotlinx.cinterop.MemScope
import kotlinx.cinterop.pointed

// -- Module: kGestures

//=======================================================//
// GESTURES AND TOUCH HANDLING FUNCTIONS
//=======================================================//

/**
 * Enable a set of gestures using flags
 */
inline fun setGesturesEnabled(flags: Gesture) {
    SetGesturesEnabled(flags)
}

/**
 * Check if a gesture have been detected
 */
inline fun isGestureDetected(gesture: kaylib.kEnums.Gesture) : Boolean {
    return IsGestureDetected(gesture.value)
}

/**
 * Get latest detected gesture
 */
inline fun getGestureDetected() : Int {
    return GetGestureDetected()
}

/**
 * Get gesture hold time in milliseconds
 */
inline fun getGestureHoldDuration() : Float {
    return GetGestureHoldDuration()
}

/**
 * Get gesture drag vector
 */
inline fun getGestureDragVector() : Vector2 {
    return GetGestureDragVector().getPointer(MemScope()).pointed
}

/**
 * Get gesture drag angle
 */
inline fun getGestureDragAngle() : Float {
    return GetGestureDragAngle()
}

/**
 * Get gesture pinch delta
 */
inline fun getGesturePinchVector() : Vector2 {
    return GetGesturePinchVector().getPointer(MemScope()).pointed
}

/**
 * Get gesture pinch angle
 */
inline fun getGesturePinchAngle() : Float {
    return GetGesturePinchAngle()
}