package kaylib.kAudio

import kaylibc.*
import kotlinx.cinterop.*

// -- Module: kAudio

//=======================================================//
// AUDIO DEVICE MANAGEMENT
//=======================================================//

/**
 * Initialize audio device and context
 */
inline fun initAudioDevice() {
    InitAudioDevice()
}

/**
 * Close the audio device and context
 */
inline fun closeAudioDevice() {
    CloseAudioDevice()
}

/**
 * Check if audio device has been initialized successfully
 */
inline val isAudioDeviceReady: Boolean
    get() { return IsAudioDeviceReady() }

/**
 * Set master volume (listener)
 */
inline fun setMasterVolume(volume: Float) {
    SetMasterVolume(volume)
}

//=======================================================//
// WAVE/SOUND LOADING & UNLOADING FUNCTIONS
//=======================================================//

/**
 * Load wave data from file
 */
inline fun loadWave(fileName: String) : Wave {
    return LoadWave(fileName).getPointer(MemScope()).pointed
}

/**
 * Load wave from memory buffer, fileType refers to extension: i.e. ".wav"
 */
inline fun loadWaveFromMemory(fileType: String, fileData: UByteVar, dataSize: Int) : Wave {
    return LoadWaveFromMemory(fileType, fileData.ptr, dataSize).getPointer(MemScope()).pointed
}

/**
 * Load sound from file
 */
inline fun loadSound(fileName: String) : Sound {
    return LoadSound(fileName).getPointer(MemScope()).pointed
}

/**
 * Load sound from wave data
 */
inline fun loadSoundFromWave(wave: Wave) : Sound {
    return LoadSoundFromWave(wave.readValue()).getPointer(MemScope()).pointed
}

/**
 * Update sound buffer with new data
 */
inline fun updateSound(sound: Sound, data: COpaquePointer, samplesCount: Int) {
    return UpdateSound(sound.readValue(), data, samplesCount)
}

/**
 * Unload wave data
 */
inline fun unloadWave(wave: Wave) {
    UnloadWave(wave.readValue())
}

/**
 * Unload sound
 */
inline fun unloadSound(sound: Sound) {
    UnloadSound(sound.readValue())
}

/**
 * Export wave data to file, returns true on success
 */
inline fun exportWave(wave: Wave, fileName: String) : Boolean {
    return ExportWave(wave.readValue(), fileName)
}

/**
 * Export wave sample data to code (.h), returns true on success
 */
inline fun exportWaveAsCode(wave: Wave, fileName: String) : Boolean {
    return ExportWaveAsCode(wave.readValue(), fileName)
}

//=======================================================//
// WAVE/SOUND MANAGEMENT FUNCTIONS
//=======================================================//

/**
 * Play a sound
 */
inline fun playSound(sound: Sound) {
    PlaySound(sound.readValue())
}

/**
 * Stop playing a sound
 */
inline fun stopSound(sound: Sound) {
    StopSound(sound.readValue())
}

/**
 * Pause a sound
 */
inline fun pauseSound(sound: Sound) {
    PauseSound(sound.readValue())
}

/**
 * Resume a paused sound
 */
inline fun resumeSound(sound: Sound) {
    ResumeSound(sound.readValue())
}

/**
 * Play a sound (using multichannel buffer pool)
 */
inline fun playSoundMulti(sound: Sound) {
    PlaySoundMulti(sound.readValue())
}

/**
 * Stop any sound playing (using multichannel buffer pool)
 */
inline fun stopSoundMulti() {
    StopSoundMulti()
}

/**
 * Get number of sounds playing in the multichannel
 */
inline fun getSoundsPlaying() : Int {
    return GetSoundsPlaying()
}

/**
 * Check if a sound is currently playing
 */
inline fun isSoundPlaying(sound: Sound) : Boolean {
    return IsSoundPlaying(sound.readValue())
}

/**
 * Set volume for a sound (1.0 is max level)
 */
inline fun setSoundVolume(sound: Sound, volume: Float) {
    SetSoundVolume(sound.readValue(), volume)
}


/**
 * Set pitch for a sound (1.0 is base level)
 */
inline fun setSoundPitch(sound: Sound, pitch: Float) {
    SetSoundPitch(sound.readValue(), pitch)
}


/**
 * Convert wave data to desired format
 */
inline fun waveFormat(wave: Wave, sampleRate: Int, sampleSize: Int, channels: Int) {
    WaveFormat(wave.readValue(), sampleRate, sampleSize, channels)
}

/**
 * Copy a wave to a new wave
 */
inline fun waveCopy(wave: Wave) : Wave {
    return WaveCopy(wave.readValue()).getPointer(MemScope()).pointed
}

/**
 * Crop a wave to defined samples range
 */
inline fun waveCrop(wave: Wave, initSample: Int, finalSample: Int) {
    WaveCrop(wave.readValue(), initSample, finalSample)
}

/**
 * Load samples data from wave as a floats array
 */
inline fun loadWaveSamples(wave: Wave) : CPointer<FloatVar>? {
    return LoadWaveSamples(wave.readValue())
}

/**
 * Unload samples data loaded with LoadWaveSamples()
 */
inline fun unloadWaveSamples(samples: CPointer<FloatVar>) {
    UnloadWaveSamples(samples)
}

//=======================================================//
// MUSIC MANAGEMENT FUNCTIONS
//=======================================================//


/**
 * Load music stream from file
 */
inline fun loadMusicStream(fileName: String) : Music {
    return LoadMusicStream(fileName).getPointer(MemScope()).pointed
}

/**
 *Load music stream from data
 */
inline fun loadMusicStreamFromMemory(fileType: String, data: CPointer<UByteVar>, dataSize: Int) : Music {
    return LoadMusicStreamFromMemory(fileType, data, dataSize).getPointer(MemScope()).pointed
}

/**
 * Unload music stream
 */
inline fun unloadMusicStream(music: Music) {
    UnloadMusicStream(music.readValue())
}

/**
 * Start music playing
 */
inline fun playMusicStream(music: Music) {
    PlayMusicStream(music.readValue())
}

/**
 * Check if music is playing
 */
inline fun isMusicStreamPlaying(music: Music) : Boolean {
    return IsMusicStreamPlaying(music.readValue())
}

/**
 * Updates buffers for music streaming
 */
inline fun updateMusicStream(music: Music) {
    UpdateMusicStream(music.readValue())
}

/**
 * Stop music playing
 */
inline fun stopMusicStream(music: Music) {
    StopMusicStream(music.readValue())
}

/**
 * Pause music playing
 */
inline fun pauseMusicStream(music: Music) {
    PauseMusicStream(music.readValue())
}

/**
 * Resume playing paused music
 */
inline fun resumeMusicStream(music: Music) {
    ResumeMusicStream(music.readValue())
}

/**
 * Seek music to a position (in seconds)
 */
inline fun seekMusicStream(music: Music, position: Float) {
    SeekMusicStream(music.readValue(), position)
}

/**
 * Set volume for music (1.0 is max level)
 */
inline fun setMusicVolume(music: Music, volume: Float) {
    SetMusicVolume(music.readValue(), volume)
}

/**
 * Set pitch for a music (1.0 is base level)
 */
inline fun setMusicPitch(music: Music, pitch: Float) {
    SetMusicPitch(music.readValue(), pitch)
}

/**
 * Get music time length (in seconds)
 */
inline fun getMusicTimeLength(music: Music) : Float {
    return GetMusicTimeLength(music.readValue())
}

/**
 * Get current music time played (in seconds)
 */
inline fun getMusicTimePlayed(music: Music) : Float {
    return GetMusicTimeLength(music.readValue())
}

//=======================================================//
// AUDIOSTREAM MANAGEMENT FUNCTIONS
//=======================================================//

/**
 * Init audio stream (to stream raw audio pcm data)
 */
inline fun loadAudioStream(sampleRate: UInt, sampleSize: UInt, channels: UInt) : AudioStream {
    return LoadAudioStream(sampleRate, sampleSize, channels).getPointer(MemScope()).pointed
}

/**
 * Unload audio stream and free memory
 */
inline fun unloadAudioStream(stream: AudioStream) {
    UnloadAudioStream(stream.readValue())
}

/**
 * Update audio stream buffers with data
 */
inline fun updateAudioStream(stream: AudioStream, data: COpaquePointer, samplesCount: Int) {
    UpdateAudioStream(stream.readValue(), data, samplesCount)
}

/**
 * Check if any audio stream buffers requires refill
 */
inline fun isAudioStreamProcessed(stream: AudioStream) : Boolean {
    return IsAudioStreamProcessed(stream.readValue())
}

/**
 * Play audio stream
 */
inline fun playAudioStream(stream: AudioStream) {
    PlayAudioStream(stream.readValue())
}

/**
 * Pause audio stream
 */
inline fun pauseAudioStream(stream: AudioStream) {
    PauseAudioStream(stream.readValue())
}

/**
 * Resume audio stream
 */
inline fun resumeAudioStream(stream: AudioStream) {
    ResumeAudioStream(stream.readValue())
}

/**
 * Check if audio stream is playing
 */
inline fun isAudioStreamPlaying(stream: AudioStream) : Boolean {
    return IsAudioStreamPlaying(stream.readValue())
}

/**
 * Stop audio stream
 */
inline fun stopAudioStream(stream: AudioStream) {
    StopAudioStream(stream.readValue())
}

/**
 * Set volume for audio stream (1.0 is max level)
 */
inline fun setAudioStreamVolume(stream: AudioStream, volume: Float) {
    SetAudioStreamVolume(stream.readValue(), volume)
}

/**
 * Set pitch for audio stream (1.0 is base level)
 */
inline fun setAudioStreamPitch(stream: AudioStream, pitch: Float) {
    SetAudioStreamPitch(stream.readValue(), pitch)
}

/**
 * Default size for new audio streams
 */
inline fun setAudioStreamBufferSizeDefault(size: Int) {
    SetAudioStreamBufferSizeDefault(size)
}