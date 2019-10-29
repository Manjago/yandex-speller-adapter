package com.github.manjago.yandexspelleradapter.backend

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.manjago.yandexspelleradapter.logic.TextFixer
import org.springframework.stereotype.Service
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublisher
import java.net.http.HttpRequest.BodyPublishers
import java.nio.charset.StandardCharsets


@Service
class YandexSpellerService(private val httpClient: HttpClient,
                           objectMapper: ObjectMapper) {

    private val tr = object : TypeReference<List<SpellerResponseItem>>() {}
    private val reader = objectMapper.readerFor(tr)

    fun check(text: String): List<SpellerResponseItem>? {

        val request = HttpRequest.newBuilder(
                URI.create("https://speller.yandex.net/services/spellservice.json/checkText"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(ofFormData(mapOf("text" to text)))
                .build()


        val response = httpClient.send(request, JsonResponseBodyHandler<List<SpellerResponseItem>>(reader))

        if (response.statusCode() != 200) {
            return null
        }

        return response.body()
    }

    private fun ofFormData(data: Map<String, String>): BodyPublisher {
        val builder = StringBuilder()
        data.forEach { (key, value) ->
            if (builder.isNotEmpty()) {
                builder.append("&")
            }
            builder.append(URLEncoder.encode(key, StandardCharsets.UTF_8))
            builder.append("=")
            builder.append(URLEncoder.encode(value, StandardCharsets.UTF_8))
        }
        return BodyPublishers.ofString(builder.toString())
    }
}