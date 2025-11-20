open class TrainingSession(
    val title: String,
    val room: String
) {
    open fun start() {
        println("Тренинг \"$title\" начался в комнате $room")
    }

    open fun finish() {
        println("Тренинг \"$title\" завершился")
    }
}

class OnlineTrainingSession(
    title: String,
    val url: String
) : TrainingSession(title, room = "NO_ROOM") {

    override fun start() {
        // Онлайн-тренинг не может начаться "в комнате"
        throw IllegalStateException(
            "Онлайн-тренинг \"$title\" не может начаться в комнате $room. Используйте ссылку $url"
        )
    }

    fun startOnline() {
        println("Онлайн-тренинг \"$title\" начался по ссылке $url")
    }
}

fun startAllSessions(sessions: List<TrainingSession>) {
    for (session in sessions) {
        session.start()
    }
}
