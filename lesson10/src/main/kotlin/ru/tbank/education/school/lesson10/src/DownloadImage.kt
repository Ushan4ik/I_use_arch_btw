package ru.tbank.education.school.lesson10.src
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.Callable
import java.net.URL
import java.util.concurrent.TimeUnit
object ImageDownloader {
    suspend fun run(urls: List<String>, dir: String) = withContext(Dispatchers.IO) {
        File(dir).mkdirs()

        /* val results = urls.mapIndexed { i, url ->
            try {
                val file = File(dir, "img_${i+1}.jpg")
                URL(url).openStream().use { it.copyTo(file.outputStream()) }
                println("YES!!! Загружено ${i+1}/${urls.size}")
                Result.success(file)
            } catch (e: Exception) {
                println("NO!!!! Ошибка ${i+1}: ${e.message}")
                Result.failure(e)
            }
        }

        println("\nУспешно: ${results.count { it.isSuccess }} из ${urls.size}")
    }*/

        val executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

        val tasks = urls.mapIndexed { index, url ->
            Callable {
                try {
                    val file = File(dir, "img_${index + 1}.jpg")
                    URL(url).openStream().use { it.copyTo(file.outputStream()) }
                    println("YES!!! Загружено ${index + 1}/${urls.size}")
                    Result.success(file)
                } catch (e: Exception) {
                    println("NO!!!! Ошибка ${index + 1}: ${e.message}")
                    Result.failure(e)
                }
            }
        }

        val futures = executor.invokeAll(tasks)
        val results = futures.map { it.get() }

        executor.shutdown()
        executor.awaitTermination(1, TimeUnit.MINUTES)

        println("\nУспешно: ${results.count { it.isSuccess }} из ${urls.size}")

    }

    }

// Запуск:
fun main() = runBlocking {
    val urls = List(10) { "https://picsum.photos/200/300?random=$it" }
    ImageDownloader.run(urls, "downloads")
}