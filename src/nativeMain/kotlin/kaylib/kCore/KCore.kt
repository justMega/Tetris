package kaylib.kCore

import kaylib.kEnums.ConfigFlag
import kaylib.kEnums.TraceLogLevel
import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kCore

//=======================================================//
// RAYLIB CORE FUNCTIONS
//=======================================================//

/**
* Initialize window and OpenGL context
*/
inline fun initWindow(width: Int, height: Int, title: String?) {
    return InitWindow(width, height, title)
}

/**
 * Check if KEY_ESCAPE pressed or Close icon pressed
 */
inline val windowShouldClose: Boolean
    get() { return WindowShouldClose() }

/**
 * Setup canvas (framebuffer) to start drawing, then calls [block].
 *
 * Drawing will end after [block] has finished.
 */
inline fun whileWindowIsOpen(block: () -> Unit) {
    beginDrawing()
    block()
    endDrawing()
}

/**
 * Close window and unload OpenGL context
 */
inline fun closeWindow() {
    return CloseWindow()
}

/**
 * Check if window has been initialized successfully
 */
inline val isWindowReady: Boolean
    get() { return IsWindowReady() }

/**
 * Check if window is currently fullscreen
 */
inline val isWindowFullscreen: Boolean
    get() { return IsWindowFullscreen() }

/**
 * Check if window is currently hidden (only PLATFORM_DESKTOP)
 */
inline val isWindowHidden: Boolean
    get() { return IsWindowHidden() }

/**
 * Check if window is currently minimized (only PLATFORM_DESKTOP)
 */
inline val isWindowMinimized: Boolean
    get() { return IsWindowMinimized() }

/**
 * Check if window is currently maximized (only PLATFORM_DESKTOP)
 */
inline val isWindowMaximized: Boolean
    get() { return IsWindowMaximized() }

/**
 * Check if window is currently focused (only PLATFORM_DESKTOP)
 */
inline val isWindowFocused: Boolean
    get() { return IsWindowFocused() }

/**
 * Check if window has been resized last frame
 */
inline val isWindowResized: Boolean
    get() { return IsWindowResized() }

/**
 * Check if one specific window flag is enabled
 */
inline fun isWindowState(flags: ConfigFlag) : Boolean {
    return IsWindowState(flags.value)
}

/**
 * Set window configuration state using flags
 */
inline fun setWindowState(flags: ConfigFlag) {
    SetWindowState(flags.value)
}

/**
 * Clear window configuration state flags
 */
inline fun clearWindowState(flags: ConfigFlag) {
    ClearWindowState(flags.value)
}

/**
 * Clear window configuration state flags
 */
inline fun toggleFullscreen() {
    ToggleFullscreen()
}

/**
 * Clear window configuration state flags
 */
inline fun maximizeWindow() {
    MaximizeWindow()
}

/**
 * Set window state: minimized, if resizable (only PLATFORM_DESKTOP)
 */
inline fun minimizeWindow() {
    MinimizeWindow()
}

/**
 * Set window state: not minimized/maximized (only PLATFORM_DESKTOP)
 */
inline fun restoreWindow() {
    RestoreWindow()
}

/**
 * Set icon for window (only PLATFORM_DESKTOP)
 */
inline fun setWindowIcon(image: Image) {
    SetWindowIcon(image.readValue())
}

/**
 * Set title for window (only PLATFORM_DESKTOP)
 */
inline fun setWindowTitle(title: String) {
    title.apply { SetWindowTitle(this) }
}

/**
 * Set window position on screen (only PLATFORM_DESKTOP)
 */
inline fun setWindowPosition(x: Int, y: Int) {
    SetWindowPosition(x, y)
}

/**
 * Set window position on screen (only PLATFORM_DESKTOP)
 */
inline fun setWindowMonitor(monitor: Int) {
    SetWindowMonitor(monitor)
}

/**
 * Set window position on screen (only PLATFORM_DESKTOP)
 */
inline fun setWindowMinSize(width: Int, height: Int) {
    SetWindowMinSize(width, height)
}

/**
 * Get native window handle
 */
inline fun getWindowHandle(): COpaquePointer? {
    return GetWindowHandle()
}

/**
 * Get current screen width
 */
inline fun getScreenWidth() : Int {
    return GetScreenWidth()
}

/**
 *Get current screen height
 */
inline fun getScreenHeight() : Int {
    return GetScreenHeight()
}


/**
 * Get number of connected monitors
 */
inline fun getMonitorCount() : Int {
    return GetMonitorCount()
}

/**
 * Get current connected monitor
 */
inline fun getCurrentMonitor() : Int {
    return GetCurrentMonitor()
}

/**
 * Get specified monitor position
 */
inline fun getMonitorPosition(monitor: Int) : Vector2 {
    return GetMonitorPosition(monitor).getPointer(MemScope()).pointed
}

/**
 * Get specified monitor width (max available by monitor)
 */
inline fun getMonitorWidth(monitor: Int) : Int {
    return GetMonitorWidth(monitor)
}

/**
 * Get specified monitor height (max available by monitor)
 */
inline fun getMonitorHeight(monitor: Int) : Int {
    return GetMonitorHeight(monitor)
}

/**
 * Get specified monitor physical width in millimetres
 */
inline fun getMonitorPhysicalWidth(monitor: Int) : Int {
    return GetMonitorPhysicalWidth(monitor)
}

/**
 * Get specified monitor physical height in millimetres
 */
inline fun getMonitorPhysicalHeight(monitor: Int) : Int {
    return GetMonitorPhysicalHeight(monitor)
}

/**
 * Get specified monitor refresh rate
 */
inline fun getWindowPosition() : Vector2 {
    return GetWindowPosition().getPointer(MemScope()).pointed
}

/**
 * Get window scale DPI factor
 */
inline fun getWindowScaleDPI() : Vector2 {
    return  GetWindowScaleDPI().getPointer(MemScope()).pointed
}

/**
 * Get the human-readable, UTF-8 encoded name of the primary monitor
 */
inline fun getMonitorName(monitor: Int) : String {
    return GetMonitorName(monitor)?.toKStringFromUtf8() ?: "WARNING: GLFW: Failed to find selected monitor"
}

/**
 * Set clipboard text content
 */
inline fun setClipboardText(text: String) {
    SetClipboardText(text)
}

/**
 * Get clipboard text content
 */
inline fun getClipboardText() : String {
    return GetClipboardText()?.toKStringFromUtf8() ?: ""
}
//=======================================================//
// FRAME CONTROLS FUNCTIONS
//=======================================================//
/**
 * Swap back buffer with front buffer (screen drawing)
 */
inline fun swapScreenBuffer() {
    SwapScreenBuffer()
}

/**
 * Register all input events
 */
inline fun pollInputEvents() {
    PollInputEvents()
}

/**
 * Wait for some milliseconds (halt program execution)
 * NOTE: Sleep() granularity could be around 10 ms, it means, Sleep() could
 * take longer than expected... for that reason we use the busy wait loop
 */
inline fun waitTime(ms: Double) {
    WaitTime(ms)
}

//=======================================================//
// CURSOR FUNCTIONS
//=======================================================//

/**
 * Show cursor
 */
inline fun showCursor() {
    ShowCursor()
}

/**
 * Hide cursor
 */
inline fun hideCursor() {
    HideCursor()
}

/**
 * Check if cursor is not visible
 */
inline fun isCursorHidden() : Boolean {
    return IsCursorHidden()
}

/**
 * Enables cursor (unlock cursor)
 */
inline fun enableCursor() {
    EnableCursor()
}

/**
 * Disables cursor (lock cursor)
 */
inline fun disableCursor() {
    DisableCursor()
}

inline val isCursorOnScreen: Boolean
    get() { return IsCursorOnScreen() }

//=======================================================//
// DRAWING FUNCTIONS
//=======================================================//

/**
 * Set background color (framebuffer clear color)
 */
inline fun clearBackground(color: Color) {
   ClearBackground(color.readValue())
}

/**
 * Setup canvas (framebuffer) to start drawing
 */
inline fun beginDrawing() {
    BeginDrawing()
}

/**
 * End canvas drawing and swap buffers (double buffering)
 */
inline fun endDrawing() {
    EndDrawing()
}

/**
 * Begin 2D mode with custom camera (2D)
 */
inline fun beginMode2D(camera: Camera2D) {
    BeginMode2D(camera.readValue())
}

/**
 * Ends 2D mode with custom camera
 */
inline fun endMode2D() {
    EndMode2D()
}

/**
 * Begin 3D mode with custom camera (3D)
 */
inline fun beginMode3D(camera: Camera3D) {
    BeginMode3D(camera.readValue())
}


/**
 * Ends 3D mode and returns to default 2D orthographic mode
 */
inline fun endMode3D() {
    EndMode3D()
}

/**
 * Begin drawing to render texture
 */
inline fun beginTextureMode(target: RenderTexture2D) {
    BeginTextureMode(target.readValue())
}

/**
 * Ends drawing to render texture
 */
inline fun endTextureMode() {
    EndTextureMode()
}

/**
 * Begin custom shader drawing
 */
inline fun beginShaderMode(shader: Shader) {
    BeginShaderMode(shader.readValue())
}

/**
 * End custom shader drawing (use default shader)
 */
inline fun endShaderMode() {
    EndShaderMode()
}

/**
 * Begin blending mode (alpha, additive, multiplied), subtract, custom)
 */
inline fun beginBlendMode(mode: kaylib.kEnums.BlendMode) {
    BeginBlendMode(mode.value)
}

/**
 * End blending mode (reset to default: alpha blending)
 */
inline fun endBlendMode() {
    EndBlendMode()
}

/**
 * Begin scissor mode (define screen area for following drawing)
 */
inline fun beginScissorMode(x: Int, y: Int, width: Int, height: Int) {
    BeginScissorMode(x, y, width, height)
}

/**
 * End scissor mode
 */
inline fun endScissorMode() {
    EndScissorMode()
}

/**
 * Begin stereo rendering (requires VR simulator)
 */
inline fun beginVrStereoMode(config: VrStereoConfig) {
    BeginVrStereoMode(config.readValue())
}

/**
 * End stereo rendering (requires VR simulator)
 */
inline fun endVrStereoMode() {
    EndVrStereoMode()
}

//=======================================================//
// VR STEREO CONFIG FUNCTIONS
//=======================================================//

/**
 * Load VR stereo config for VR simulator device parameters
 */
inline fun loadVrStereoConfig(device: VrDeviceInfo) : VrStereoConfig {
    return LoadVrStereoConfig(device.readValue()).getPointer(MemScope()).pointed
}

/**
 * Unload VR stereo config
 */
inline fun unloadVrStereoConfig(config: VrStereoConfig) {
    UnloadVrStereoConfig(config.readValue())
}

//=======================================================//
// SHADER MANAGEMENT FUNCTIONS
//=======================================================//

/**
 * Load shader from files and bind default locations
 */
inline fun loadShader(vsFileName: String, fsFileName: String) : Shader {
    return LoadShader(vsFileName, fsFileName).getPointer(MemScope()).pointed
}

/**
 * Load shader from code strings and bind default locations
 */
inline fun loadShaderFromMemory(vsCode: String, fsCode: String) : Shader {
    return LoadShaderFromMemory(vsCode, fsCode).getPointer(MemScope()).pointed
}

/**
 * Get shader uniform location
 */
inline fun getShaderLocation(shader: Shader, uniformName: String) : Int {
    return GetShaderLocation(shader.readValue(), uniformName)
}

/**
 * Get shader attribute location
 */
inline fun getShaderLocationAttrib(shader: Shader, attribName: String) : Int {
    return GetShaderLocationAttrib(shader.readValue(), attribName)
}

/**
 * Set shader uniform value
 */
inline fun setShaderValue(shader: Shader, locIndex: kaylib.kEnums.ShaderLocationIndex, value: COpaquePointer, uniformType: kaylib.kEnums.ShaderUniformDataType) {
    return SetShaderValue(shader.readValue(), locIndex.value, value, uniformType.value)
}

/**
 * Set shader uniform value vector
 */
inline fun setShaderValueV(shader: Shader, locIndex: kaylib.kEnums.ShaderLocationIndex, value: COpaquePointer, uniformType: kaylib.kEnums.ShaderUniformDataType, count: Int) {
    SetShaderValueV(shader.readValue(), locIndex.value, value, uniformType.value, count)
}

/**
 * Set shader uniform value (matrix 4x4)
 */
inline fun setShaderValueMatrix(shader: Shader, locIndex: kaylib.kEnums.ShaderLocationIndex, mat: Matrix) {
    SetShaderValueMatrix(shader.readValue(), locIndex.value, mat.readValue())
}

/**
 * Set shader uniform value for texture (sampler2d)
 */
inline fun setShaderValueTexture(shader: Shader, locIndex: kaylib.kEnums.ShaderLocationIndex, texture: Texture2D) {
    SetShaderValueTexture(shader.readValue(), locIndex.value, texture.readValue())
}

/**
 * Unload shader from GPU memory (VRAM)
 */
inline fun unloadShader(shader: Shader) {
    UnloadShader(shader.readValue())
}

//=======================================================//
// SCREEN-SPACE MANAGEMENT FUNCTIONS
//=======================================================//

/**
 * Get a ray trace from mouse position
 */
inline fun getMouseRay(mousePosition: Vector2, camera: Camera) : Ray {
    return GetMouseRay(mousePosition.readValue(), camera.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get camera transform matrix (view matrix)
 */
inline fun getCameraMatrix(camera: Camera) : Matrix {
    return GetCameraMatrix(camera.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get camera 2d transform matrix
 */
inline fun getCameraMatrix2D(camera: Camera2D) : Matrix {
    return GetCameraMatrix2D(camera.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get the screen space position for a 3d world space position
 */
inline fun getWorldToScreen(position: Vector3, camera: Camera) : Vector2 {
    return GetWorldToScreen(position.readValue(), camera.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get size position for a 3d world space position
 */
inline fun getWorldToScreenEx(position: Vector3, camera: Camera, width: Int, height: Int) : Vector2 {
    return GetWorldToScreenEx(position.readValue(), camera.readValue(), width, height).getPointer(MemScope()).pointed
}

/**
 * Get the screen space position for a 2d camera world space position
 */
inline fun getWorldToScreen2D(position: Vector2, camera: Camera2D) : Vector2 {
    return GetWorldToScreen2D(position.readValue(), camera.readValue()).getPointer(MemScope()).pointed
}

/**
 * Get the world space position for a 2d camera screen space position
 */
inline fun getScreenToWorld2D(position: Vector2, camera: Camera2D) : Vector2 {
    return GetScreenToWorld2D(position.readValue(), camera.readValue()).getPointer(MemScope()).pointed
}

//=======================================================//
// TIMING FUNCTIONS
//=======================================================//

/**
 * Set target FPS (maximum)
 */
inline fun setTargetFPS(fps: Int) {
    SetTargetFPS(fps)
}

/**
 * Get current FPS
 */
inline fun getFPS() : Int {
    return GetFPS()
}

/**
 * Get time in seconds for last frame drawn (delta time)
 */
inline fun getFrameTime() : Float {
    return GetFrameTime()
}

/**
 * Get elapsed time in seconds since InitWindow()
 */
inline fun getTime() : Double {
    return GetTime()
}

//=======================================================//
// MISC FUNCTIONS
//=======================================================//

/**
 * Returns a random value between min and max (both included)
 */
inline fun getRandomValue(min: Int, max: Int) : Int {
    return GetRandomValue(min, max)
}

/**
 * Set the seed for the random number generator
 */
inline fun setRandomSeed(seed: UInt) {
    SetRandomSeed(seed)
}

/**
 * Takes a screenshot of current screen (filename extension defines format)
 */
inline fun takeScreenshot(fileName: String) {
    TakeScreenshot(fileName)
}

/**
 * Setup init configuration flags (view FLAGS)
 */
inline fun setConfigFlags(flags: kaylib.kEnums.ConfigFlag) {
    SetConfigFlags(flags.value)
}

/**
 * Show trace log messages (LOG_DEBUG, LOG_INFO, LOG_WARNING, LOG_ERROR...)
 * Vararg parameter of type Any does not translate to any C type. You can only use traceLog with log level and text(String)
 */
inline fun traceLog(logLevel: TraceLogLevel, text: String) {
    return TraceLog(logLevel.level, text)
}

/**
 * Set the current threshold (minimum) log level
 */
inline fun setTraceLogLevel(logLevel: TraceLogLevel) {
    SetTraceLogLevel(logLevel.level)
}

//=======================================================//
// CUSTOM CALLBACKS FUNCTIONS
//=======================================================//

/**
 * Set custom trace log
 * @param callback takes in a pointer to a C Function.
 */
inline fun setTraceLogCallback(callback: TraceLogCallback) {
   SetTraceLogCallback(callback)
}

/**
 * Set custom file binary data loader
 * @param callback takes in a pointer to a C Function.
 */
inline fun setLoadFileDataCallback(callback: LoadFileDataCallback) {
    SetLoadFileDataCallback(callback)
}

/**
 * Set custom file binary data saver
 * @param callback takes in a pointer to a C Function.
 */
inline fun setSaveFileDataCallback(callback: SaveFileDataCallback) {
    SetSaveFileDataCallback(callback)
}

/**
 * Set custom file text data loader
 * @param callback takes in a pointer to a C Function.
 */
inline fun setLoadFileTextCallback(callback: LoadFileTextCallback) {
    SetLoadFileTextCallback(callback)
}

/**
 * Set custom file text data saver
 * @param callback takes in a pointer to a C Function.
 */
inline fun setSaveFileTextCallback(callback: SaveFileTextCallback) {
    SetSaveFileTextCallback(callback)
}

//=======================================================//
// FILE MANAGEMENT FUNCTIONS
//=======================================================//

/**
 * Load file data as byte array (read)
 */
inline fun loadFileData(fileName: String, bytesRead: UIntVar) {
    LoadFileData(fileName, bytesRead.ptr)
}

/**
 * Unload file data allocated by LoadFileData()
 */
inline fun unloadFileData(data: UByteVar) {
    UnloadFileData(data.ptr)
}

/**
 * Save data to file from byte array (write), returns true on success
 */
inline fun saveFileData(fileName: String, data: COpaquePointer, bytesToWrite: UInt) {
    SaveFileData(fileName, data, bytesToWrite)
}

/**
 * Load text data from file (read), returns a '\0' terminated string
 */
inline fun loadFileText(fileName: String) {
    LoadFileText(fileName)
}

/**
 * Unload file text data allocated by LoadFileText()
 */
inline fun unloadFileText(text: ByteVar) {
    UnloadFileText(text.ptr)
}

/**
 * Save text data to file (write), string must be '\0' terminated, returns true on success
 */
inline fun saveFileText(fileName: String, text: String) {
    SaveFileText(fileName, text.cstr)
}

/**
 * Check if file exists
 */
inline fun fileExists(fileName: String) : Boolean {
    return FileExists(fileName)
}

/**
 * Check if a directory path exists
 */
inline fun directoryExists(dirPath: String) : Boolean {
    return DirectoryExists(dirPath)
}

/**
 * Check file extension (including point: .png, .wav)
 */
inline fun isFileExtension(fileName: String, ext: String) : Boolean {
    return IsFileExtension(fileName, ext)
}

/**
 * Get pointer to extension for a filename string (includes dot: `.png`)
 */
inline fun getFileExtension(fileName: String) : String {
    return GetFileExtension(fileName)?.toKString() ?: ""
}

/**
 * Get pointer to filename for a path string
 */
inline fun getFileName(filePath: String) : String {
    return GetFileName(filePath)?.toKString() ?: ""
}

/**
 * Get filename string without extension (uses inline string)
 */
inline fun getFileNameWithoutExt(filePath: String) : String {
    return GetFileNameWithoutExt(filePath)?.toKString() ?: ""
}

/**
 * Get full path for a given fileName with path (uses inline string)
 */
inline fun getDirectoryPath(filePath: String) : String {
    return GetDirectoryPath(filePath)?.toKString() ?: ""
}

/**
 * Get previous directory path for a given path (uses inline string)
 */
inline fun getPrevDirectoryPath(dirPath: String) : String {
    return GetPrevDirectoryPath(dirPath)?.toKString() ?: ""
}

/**
 * Get current working directory (uses inline string)
 */
inline fun getWorkingDirectory() : String {
    return GetWorkingDirectory()?.toKString() ?: ""
}

/**
 * Get filenames in a directory path (memory should be freed)
 */
inline fun loadDirectoryFiles(dirPath: String) : FilePathList {
    return LoadDirectoryFiles(dirPath).getPointer(MemScope()).pointed
}

/**
 * Clear directory files paths buffers (free memory)
 */
inline fun unloadDirectoryFiles(files: FilePathList) {
    return UnloadDirectoryFiles(files.readValue())
}

/**
 * Change working directory, return true on success
 */
inline fun changeDirectory(dir: String) : Boolean {
    return ChangeDirectory(dir)
}

/**
 * Check if a file has been dropped into window
 */
inline val isFileDropped: Boolean
    get() { return IsFileDropped() }

/**
 * Get dropped files names (memory should be freed)
 */
inline fun loadDroppedFiles() : FilePathList {
    return LoadDroppedFiles().getPointer(MemScope()).pointed
}

/**
 * Clear dropped files paths buffer (free memory)
 */
inline fun unloadDroppedFiles(files: FilePathList) {
    UnloadDroppedFiles(files.readValue())
}

/**
 * Get file modification time (last write time)
 */
inline fun getFileModTime(fileName: String): Int {
    return GetFileModTime(fileName).convert()
}

//=======================================================//
// COMPRESSION/ENCODING FUNCTIONS
//=======================================================//

/**
 * Compress data (DEFLATE algorithm)
 */
inline fun compressData(data: UByteVar, dataLength: Int, compDataLength: IntVar) : CPointer<UByteVar>? {
    return CompressData(data.ptr, dataLength, compDataLength.ptr)
}

/**
 * Decompress data (DEFLATE algorithm)
 */
inline fun decompressData(compData: UByteVar, compDataLength: Int, dataLength: IntVar) : CPointer<UByteVar>? {
    return DecompressData(compData.ptr, compDataLength, dataLength.ptr)
}

/**
 * Encode data to Base64 string
 */
inline fun encodeDataBase64(data: UByteVar, dataLength: Int, outputLength: IntVar) : CPointer<ByteVar>? {
    return EncodeDataBase64(data.ptr, dataLength, outputLength.ptr)
}

/**
 * Encode data to Base64 string
 */
inline fun decodeDataBase64(data: UByteVar, outputLength: IntVar) : CPointer<UByteVar>? {
    return DecodeDataBase64(data.ptr, outputLength.ptr)
}

//=======================================================//
// WEB RELATED FUNCTIONS
//=======================================================//

/**
 * Open URL with default system browser (if available)
 */
inline fun openURL(url: String) {
    OpenURL(url)
}

//=======================================================//
// INPUT HANDLING FUNCTIONS - KEYBOARDS
//=======================================================//

/**
 * Check if a key has been pressed once
 */ 
inline fun isKeyPressed(keyboardKey: kaylib.kEnums.KeyboardKey) : Boolean {
    return IsKeyPressed(keyboardKey.key)
}

/**
 * Check if a key is being pressed
 */
inline fun isKeyDown(keyboardKey: kaylib.kEnums.KeyboardKey) : Boolean {
    return IsKeyDown(keyboardKey.key)
}

/**
 * Check if a key has been released once
 */
inline fun isKeyReleased(keyboardKey: kaylib.kEnums.KeyboardKey) : Boolean {
    return IsKeyReleased(keyboardKey.key)
}

/**
 * Check if a key is NOT being pressed
 */
inline fun isKeyUp(keyboardKey: kaylib.kEnums.KeyboardKey) : Boolean {
    return IsKeyUp(keyboardKey.key)
}

/**
 * Set a custom key to exit program (default is ESC)
 */
inline fun setExitKey(keyboardKey: kaylib.kEnums.KeyboardKey) {
        SetExitKey(keyboardKey.key)
}

/**
 * Get key pressed (keycode), call it multiple times for keys queued, returns 0 when the queue is empty
 */
inline fun getKeyPressed(): Int {
    return GetKeyPressed()
}

/**
 * Get char pressed (unicode), call it multiple times for chars queued, returns 0 when the queue is empty
 */
inline fun getCharPressed() : Char {
    return GetCharPressed().toChar()
}

//=======================================================//
// INPUT HANDLING FUNCTIONS - GAMEPADS
//=======================================================//

/**
 * Check if a gamepad is available
 */
inline fun isGamepadAvailable(gamepad: Int) : Boolean {
    return IsGamepadAvailable(gamepad)
}

/**
 * Get gamepad internal name id
 */
inline fun getGamepadName(gamepad: Int) : String {
    return GetGamepadName(gamepad)?.toKString() ?: "WARNING: GamePad not found!"
}

/**
 * Check if a gamepad button has been pressed once
 */
inline fun isGamepadButtonPressed(gamepad: Int, button: kaylib.kEnums.GamepadButton) : Boolean {
    return IsGamepadButtonPressed(gamepad, button.value)
}

/**
 * Check if a gamepad button is being pressed
 */
inline fun isGamepadButtonDown(gamepad: Int, button: kaylib.kEnums.GamepadButton) : Boolean {
    return IsGamepadButtonDown(gamepad, button.value)
}

/**
 * Check if a gamepad button has been released once
 */
inline fun isGamepadButtonReleased(gamepad: Int, button: kaylib.kEnums.GamepadButton) : Boolean {
    return IsGamepadButtonReleased(gamepad, button.value)
}

/**
 * Check if a gamepad button is NOT being pressed
 */
inline fun isGamepadButtonUp(gamepad: Int, button: kaylib.kEnums.GamepadButton) : Boolean {
    return IsGamepadButtonUp(gamepad, button.value)
}

/**
 * Get the last gamepad button pressed - Will always return
 */
inline fun getGamepadButtonPressed() : Int {
    return GetGamepadButtonPressed()
}

/**
 * Get gamepad axis count for a gamepad axis
 */
inline fun getGamepadAxisCount(gamepad: Int) : Int {
    return GetGamepadAxisCount(gamepad)
}

/**
 * Get axis movement value for a gamepad axis
 */
inline fun getGamepadAxisMovement(gamepad: Int, axis: kaylib.kEnums.GamepadAxis) : Float {
    return GetGamepadAxisMovement(gamepad, axis.value)
}

/**
 * Set internal gamepad mappings (SDL_GameControllerDB)
 */
inline fun setGamepadMappings(mappings: String) : Int {
    return SetGamepadMappings(mappings)
}

//=======================================================//
// INPUT HANDLING FUNCTIONS - MOUSE
//=======================================================//

/**
 * Check if a mouse button has been pressed once
 */
inline fun isMouseButtonPressed(button: kaylib.kEnums.MouseButton) : Boolean {
    return IsMouseButtonPressed(button.value)
}

/**
 * Check if a mouse button is being pressed
 */
inline fun isMouseButtonDown(button: kaylib.kEnums.MouseButton) : Boolean {
    return IsMouseButtonDown(button.value)
}

/**
 * Check if a mouse button has been released once
 */
inline fun isMouseButtonReleased(button: kaylib.kEnums.MouseButton) : Boolean {
    return IsMouseButtonReleased(button.value)
}

/**
 * Check if a mouse button is NOT being pressed
 */
inline fun isMouseButtonUp(button: kaylib.kEnums.MouseButton) : Boolean {
    return IsMouseButtonUp(button.value)
}

/**
 * Get mouse position X
 */
inline fun getMouseX() : Int {
    return GetMouseX()
}

/**
 * Get mouse position Y
 */
inline fun getMouseY() : Int {
    return GetMouseY()
}

/**
 * Get mouse position XY - This function uses getPointer with passed MemScope() as scope! You can also allocate your arena in the parameter
 */
inline fun getMousePosition() : Vector2 {
    return GetMousePosition().getPointer(MemScope()).pointed

}

/**
 * Set mouse position XY
 */
inline fun setMousePosition(x: Int, y : Int) {
    SetMousePosition(x, y)
}

/**
 * Set mouse offset
 */
inline fun setMouseOffset(offsetX: Int, offsetY: Int) {
    SetMouseOffset(offsetX, offsetY)
}

/**
 * Set mouse scaling
 */
inline fun setMouseScale(scaleX: Float, scaleY: Float) {
    SetMouseScale(scaleX, scaleY)
}

/**
 * Get mouse wheel movement Y
 */
inline fun getMouseWheelMove() : Float {
    return GetMouseWheelMove()
}

/**
 * Set mouse cursor
 */
inline fun setMouseCursor(cursor: kaylib.kEnums.MouseCursor) {
    SetMouseCursor(cursor.value)
}

//=======================================================//
// INPUT HANDLING FUNCTIONS - TOUCH
//=======================================================//

/**
 * Get touch position X for touch point 0 (relative to screen size)
 */
inline fun getTouchX() : Int {
    return GetTouchX()
}

/**
 * Get touch position Y for touch point 0 (relative to screen size)
 */
inline fun getTouchY() : Int {
    return GetTouchY()
}

/**
 * Get touch position XY for a touch point index (relative to screen size)
 */
inline fun getTouchPosition(index: Int) : Vector2 {
    return GetTouchPosition(index).getPointer(MemScope()).pointed
}

/**
 * Get touch point identifier for given index
 */
inline fun getTouchPointId(index: Int) : Int{
    return GetTouchPointId(index)
}

/**
 * Get number of touch points
 */
inline fun getTouchPointCount() : Int {
    return GetTouchPointCount()
}
