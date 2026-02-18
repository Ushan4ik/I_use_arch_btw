package ru.tbank.education.school.lesson10.src

import kotlinx.coroutines.runBlocking  // Add this import

data class DownloadStats(
    val url: String,
    val bytesDownloaded: Long,
    val timeMs: Long
)

object ImageDownloader {
    fun run(urls: List<String>, outputDir: String): DownloadStats = runBlocking {
        TODO() // You need to implement this function
    }
}