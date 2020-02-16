package App

// Version 1.2.41

import java.util.EnumSet.range
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import kotlin.math.sin
import kotlin.math.PI

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

    "A2" to 27.5,
    "A#2" to 29.1352,
    "Bb2" to 29.1352,
    "B2" to 30.8677,
    "C3" to 32.7032,
    "C#3" to 34.6478,
    "Db3" to 34.6478,
    "D3" to 36.7081,
    "D#3" to 38.8909,
    "Eb3" to 38.8909,
    "E3" to 41.2034,
    "F3" to 43.6535,
    "F#3" to 46.2493,
    "Gb3" to 46.2493,
    "G3" to 48.9994,
    "G#3" to 51.9131,
    "Ab3" to 51.9131,

    "A3" to 27.5,
    "A#3" to 29.1352,
    "Bb3" to 29.1352,
    "B3" to 30.8677,
    "C4" to 32.7032,
    "C#4" to 34.6478,
    "Db4" to 34.6478,
    "D4" to 36.7081,
    "D#4" to 38.8909,
    "Eb4" to 38.8909,
    "E4" to 41.2034,
    "F4" to 43.6535,
    "F#4" to 46.2493,
    "Gb4" to 46.2493,
    "G4" to 48.9994,
    "G#4" to 51.9131,
    "Ab4" to 51.9131,

    "A4" to 27.5,
    "A#4" to 29.1352,
    "Bb4" to 29.1352,
    "B4" to 30.8677,
    "C5" to 32.7032,
    "C#5" to 34.6478,
    "Db5" to 34.6478,
    "D5" to 36.7081,
    "D#5" to 38.8909,
    "Eb5" to 38.8909,
    "E5" to 41.2034,
    "F5" to 43.6535,
    "F#5" to 46.2493,
    "Gb5" to 46.2493,
    "G5" to 48.9994,
    "G#5" to 51.9131,
    "Ab5" to 51.9131,

    "A5" to 27.5,
    "A#5" to 29.1352,
    "Bb5" to 29.1352,
    "B5" to 30.8677,
    "C6" to 32.7032,
    "C#6" to 34.6478,
    "Db6" to 34.6478,
    "D6" to 36.7081,
    "D#6" to 38.8909,
    "Eb6" to 38.8909,
    "E6" to 41.2034,
    "F6" to 43.6535,
    "F#6" to 46.2493,
    "Gb6" to 46.2493,
    "G6" to 48.9994,
    "G#6" to 51.9131,
    "Ab6" to 51.9131,

    "A6" to 27.5,
    "A#6" to 29.1352,
    "Bb6" to 29.1352,
    "B6" to 30.8677,
    "C7" to 32.7032,
    "C#7" to 34.6478,
    "Db7" to 34.6478,
    "D7" to 36.7081,
    "D#7" to 38.8909,
    "Eb7" to 38.8909,
    "E7" to 41.2034,
    "F7" to 43.6535,
    "F#7" to 46.2493,
    "Gb7" to 46.2493,
    "G7" to 48.9994,
    "G#7" to 51.9131,
    "Ab7" to 51.9131,

    "A7" to 27.5,
    "A#7" to 29.1352,
    "Bb7" to 29.1352,
    "B7" to 30.8677,
    "C8" to 32.7032
)


fun sineWave(frequency: Int, seconds: Int, sampleRate: Int): ByteArray {
    val samples = seconds * sampleRate
    val result = ByteArray(samples)
    val interval = sampleRate.toDouble() / frequency
    for (i in 0 until samples) {
        val angle = 2.0 * PI * i / interval
        result[i] = (sin(angle) * 127).toByte()
    }
    return result
}

fun play(sampleRate: Int, freq: Int, length: Int) {
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
    play(44000, 440, 10 )
}