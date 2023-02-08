package kaylib.kCamera

import kaylibc.*
import kotlinx.cinterop.ptr
import kotlinx.cinterop.readValue

// -- Module: kCamera

//=======================================================//
// CAMERA SYSTEM FUNCTIONS
//=======================================================//

/**
 * Set camera mode (multiple camera modes available)
 */
inline fun setCameraMode(camera: Camera, mode: kaylib.kEnums.CameraMode) {
    SetCameraMode(camera.readValue(), mode.value)
}

/**
 * Update camera position for selected mode
 */
inline fun updateCamera(camera: Camera) {
    UpdateCamera(camera.ptr)
}

/**
 * Set camera pan key to combine with mouse movement (free camera)
 */
inline fun setCameraPanControl(keyPan: Int) {
    SetCameraPanControl(keyPan)
}

/**
 * Set camera alt key to combine with mouse movement (free camera)
 */
inline fun setCameraAltControl(keyAlt: Int) {
    SetCameraAltControl(keyAlt)
}

/**
 * Set camera smooth zoom key to combine with mouse (free camera)
 */
inline fun setCameraSmoothZoomControl(keySmoothZoom: Int) {
    SetCameraSmoothZoomControl(keySmoothZoom)
}

/**
 * Set camera move controls (1st person and 3rd person cameras)
 */
inline fun setCameraMoveControls(keyFront: kaylib.kEnums.KeyboardKey, keyBack: kaylib.kEnums.KeyboardKey, keyRight: kaylib.kEnums.KeyboardKey, keyLeft: kaylib.kEnums.KeyboardKey, keyUp: kaylib.kEnums.KeyboardKey, keyDown: kaylib.kEnums.KeyboardKey) {
    SetCameraMoveControls(keyFront.key, keyBack.key, keyRight.key, keyLeft.key, keyUp.key, keyDown.key)
}