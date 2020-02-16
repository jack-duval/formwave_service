package App

// Version 1.2.41

import java.util.EnumSet.range
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import kotlin.math.sin
import kotlin.math.PI

data class playInput(val sampleRate: Int, val freq: Double, val length: Int)
val notes = mapOf<String, Double>(
    "A0" to 27.5,
    "A#0" to 29.1352,
    "Bb0" to 29.1352,
    "B0" to 30.8677,
    "C1" to 32.7032,
    "C#1" to 34.6478,
    "Db1" to 34.6478,
    "D1" to 36.7081,
    "D#1" to 38.8909,
    "Eb1" to 38.8909,
    "E1" to 41.2034,
    "F1" to 43.6535,
    "F#1" to 46.2493,
    "Gb1" to 46.2493,
    "G1" to 48.9994,
    "G#1" to 51.9131,
    "Ab1" to 51.9131,

    "A1" to 55.00,
    "A#1" to 58.2705,
    "Bb1" to 58.2705,
    "B1" to 61.7354,
    "C2" to 65.4064,
    "C#2" to 69.2957,
    "Db2" to 69.2957,
    "D2" to 73.4162,
    "D#2" to 77.7817,
    "Eb2" to 77.7817,
    "E2" to 82.4069,
    "F2" to 87.3071,
    "F#2" to 92.4986,
    "Gb2" to 92.4986,
    "G2" to 97.9989,
    "G#2" to 103.826,
    "Ab2" to 103.826,

    "A2" to 110.0,
    "A#2" to 116.541,
    "Bb2" to 116.541,
    "B2" to 123.471,
    "C3" to 130.813,
    "C#3" to 138.591,
    "Db3" to 138.591,
    "D3" to 146.832,
    "D#3" to 155.563,
    "Eb3" to 155.563,
    "E3" to 164.814,
    "F3" to 174.614,
    "F#3" to 184.997,
    "Gb3" to 184.997,
    "G3" to 195.998,
    "G#3" to 207.652,
    "Ab3" to 207.652,

    "A3" to 220.0,
    "A#3" to 233.082,
    "Bb3" to 233.082,
    "B3" to 246.942,
    "C4" to 261.626,
    "C#4" to 277.183,
    "Db4" to 277.183,
    "D4" to 293.665,
    "D#4" to 311.127,
    "Eb4" to 311.127,
    "E4" to 329.628,
    "F4" to 349.228,
    "F#4" to 369.994,
    "Gb4" to 369.994,
    "G4" to 391.995,
    "G#4" to 415.305,
    "Ab4" to 415.305,

    "A4" to 440.0,
    "A#4" to 466.164,
    "Bb4" to 466.164,
    "B4" to 493.883,
    "C5" to 523.251,
    "C#5" to 554.365,
    "Db5" to 554.365,
    "D5" to 587.330,
    "D#5" to 622.254,
    "Eb5" to 622.254,
    "E5" to 659.255,
    "F5" to 698.456,
    "F#5" to 739.989,
    "Gb5" to 739.989,
    "G5" to 783.991,
    "G#5" to 830.609,
    "Ab5" to 830.609,

    "A5" to 880.0,
    "A#5" to 932.328,
    "Bb5" to 932.328,
    "B5" to 987.767,
    "C6" to 1046.50,
    "C#6" to 1108.73,
    "Db6" to 1108.73,
    "D6" to 1174.66,
    "D#6" to 1244.51,
    "Eb6" to 1244.51,
    "E6" to 1318.51,
    "F6" to 1396.91,
    "F#6" to 1479.98,
    "Gb6" to 1479.98,
    "G6" to 1567.98,
    "G#6" to 1661.22,
    "Ab6" to 1661.22,

    "A6" to 1760.0,
    "A#6" to 1864.66,
    "Bb6" to 1864.66,
    "B6" to 1975.53,
    "C7" to 2093.0,
    "C#7" to 2217.46,
    "Db7" to 2217.46,
    "D7" to 2349.32,
    "D#7" to 2489.02,
    "Eb7" to 2489.02,
    "E7" to 2637.02,
    "F7" to 2793.83,
    "F#7" to 2959.96,
    "Gb7" to 2959.96,
    "G7" to 3135.96,
    "G#7" to 3322.44,
    "Ab7" to 3322.44,

    "A7" to 3520.0,
    "A#7" to 3729.31,
    "Bb7" to 3729.31,
    "B7" to 3951.07,
    "C8" to 4186.01
)


fun sineWave(frequency: Double, seconds: Int, sampleRate: Int): ByteArray {
    val samples = seconds * sampleRate
    val result = ByteArray(samples)
    val interval = sampleRate / frequency
    for (i in 0 until samples) {
        val angle = 2.0 * PI * i / interval
        result[i] = (sin(angle) * 127).toByte()
    }
    return result
}

fun play(sampleRate: Int, freq: Double, length: Int) {
    val buffer = sineWave(freq, length, sampleRate)
    val format = AudioFormat(sampleRate.toFloat(), 8, 1, true, true)
    val line = AudioSystem.getSourceDataLine(format)
    with (line) {
        for (i in 0..10) {
            open(format)
            start()
            write(buffer, 0, buffer.size)
            drain()
            close()
        }
    }
}


fun main(args: Array<String>) {
    val wave = playInput(44000, notes.get("C4")!!, 10)
    play(wave.sampleRate, wave.freq, wave.length )
}