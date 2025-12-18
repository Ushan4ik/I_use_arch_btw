open class TrainingSession(
    val title: String,
    val adress: String
) {
    open fun start() {
        println("Тренинг \"$title\" начался в комнате $adress")
    }

    open fun finish() {
        println("Тренинг \"$title\" завершился")
    }
}

class OnlineTrainingSession(
    title: String,
    adress: String
) : TrainingSession(title,adress ) {

    override fun start() {
            println("Тренинг \"$title\" начался по ссылке $adress")
    }
}

fun startAllSessions(sessions: List<TrainingSession>) {
    for (session in sessions) {
        session.start()
    }
}
