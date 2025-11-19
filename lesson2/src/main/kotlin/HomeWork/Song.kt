package HomeWork

import kotlin.time.Duration

open class Song(
    open val genre: String,
    open val name: String,
    open val DurationSec: Double,
) {
    open val DurationMin: Double
        get() = DurationSec / 60.0



    open fun getInfo(): String {
        return "Песня: $name | Жанр: $genre | Длительность: ${DurationSec}сек"
    }
}

// Класс-наследник с переопределением метода создания ремикса
class RemixSong(
     genre: String,
     name: String,
     DurationSec: Double,
    val remixStyle: String
) : Song(genre, name, DurationSec) {

    override val DurationSec = DurationSec /0.8
    override val DurationMin: Double
        get() = DurationSec / 60.0

    // Переопределяем метод получения информации
    override fun getInfo(): String {
        return super.getInfo() + " |Стиль: $remixStyle"
    }
}

